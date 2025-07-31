// Constants
const BASE_URL = "http://localhost:8080/api/v1/job";
const JOB_STATUS = {
    ACTIVE: { class: "bg-success", text: "Active", action: "Deactivate" },
    INACTIVE: { class: "bg-warning", text: "Inactive", action: "Activate" }
};

// State Management
const appState = {
    currentPage: 0,
    itemsPerPage: 5,
    sortField: "id",
    sortDirection: "asc",
    keyword: "",
    totalPages: 0
};

// Initialize when DOM is ready
$(document).ready(function() {
    initializeModals();
    loadJobs();
    setupEventListeners();
});

// Modal Initialization
function initializeModals() {
    // Reset forms when modal hides
    $('#addJobModal, #editJobModal').on('hidden.bs.modal', function() {
        $(this).find('form')[0].reset();
        $(this).find('.is-invalid').removeClass('is-invalid');
        $(this).find('.invalid-feedback').remove();
    });
}

// Core Functions ==============================================

function loadJobs() {
    showLoading(true);

    const params = new URLSearchParams({
        page: appState.currentPage,
        per_page: appState.itemsPerPage,
        sort: appState.sortField,
        direction: appState.sortDirection,
        keyword: appState.keyword
    });

    $.ajax({
        url: `${BASE_URL}/paging?${params}`,
        method: "GET",
        dataType: "json"
    })
        .done(function(response) {
            if (!response || !response.content) {
                throw new Error("Invalid response format");
            }
            // if (response === 200) {
            //     showToast('success', 'Jobs loaded successfully!');
            // }
            renderJobs(response.content);
            updatePagination(response.totalPages);
        })
        .fail(handleAjaxError)
        .always(function() {
            showLoading(false);
        });
}

// Job Operations ==============================================

function createJob(jobData) {
    return $.ajax({
        url: `${BASE_URL}/create`,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(jobData),
        dataType: "json"
    })
        .then(function(response) {
            // Handle success response structure
            if (response && response.status === 200) {
                return response;
            }
            // Handle unexpected success response
            throw new Error(response?.message || "Job creation failed");
        });
}

function updateJob(jobData) {
    return $.ajax({
        url: `${BASE_URL}/update`,
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(jobData),
        dataType: "json"
    })
        .then(function(response) {
            if (response && response.status === 200) {
                return response;
            }
            throw new Error(response?.message || "Job update failed");
        });
}

function changeJobStatus(id) {
    return $.ajax({
        url: `${BASE_URL}/status/${id}`,
        method: "PATCH",
        dataType: "json"
    })
        .then(function(response) {
            if (response && response.status === 200) {
                return response;
            }
            throw new Error(response?.message || "Status change failed");
        });
}

// UI Rendering ==============================================

function renderJobs(jobs) {
    const $tbody = $("#jobsTableBody");

    if (!jobs.length) {
        $tbody.html('<tr><td colspan="7" class="text-center text-muted">No jobs found</td></tr>');
        return;
    }

    const rows = jobs.map((job, index) => `
        <tr data-job-id="${job.id}">
            <td>${index + 1 + (appState.currentPage * appState.itemsPerPage)}</td>
            <td>${escapeHtml(job.jobTitle)}</td>
            <td>${escapeHtml(job.company)}</td>
            <td>${escapeHtml(job.location)}</td>
            <td>${escapeHtml(job.type)}</td>
            <td>
                <span class="badge ${JOB_STATUS[job.status]?.class || 'bg-secondary'}">
                    ${JOB_STATUS[job.status]?.text || job.status}
                </span>
            </td>
            <td>
                <button class="btn btn-sm btn-primary me-2 edit-btn" data-job='${JSON.stringify(job)}'>Edit</button>
                <button class="btn btn-sm ${job.status === 'ACTIVE' ? 'btn-warning' : 'btn-success'} status-btn" 
                        data-id="${job.id}" data-status="${job.status}">
                    ${job.status === 'ACTIVE' ? 'Deactivate' : 'Activate'}
                </button>
            </td>
        </tr>
    `);

    $tbody.html(rows.join(''));
}

function updatePagination(totalPages) {
    appState.totalPages = totalPages;
    const $pagination = $("#pagination").empty();

    if (totalPages <= 1) return;

    // Previous Button
    $pagination.append(`
        <li class="page-item ${appState.currentPage === 0 ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${appState.currentPage - 1}" aria-label="Previous">
                &laquo;
            </a>
        </li>
    `);

    // Page Numbers
    const startPage = Math.max(0, appState.currentPage - 2);
    const endPage = Math.min(totalPages - 1, appState.currentPage + 2);

    for (let i = startPage; i <= endPage; i++) {
        $pagination.append(`
            <li class="page-item ${i === appState.currentPage ? 'active' : ''}">
                <a class="page-link" href="#" data-page="${i}">${i + 1}</a>
            </li>
        `);
    }

    // Next Button
    $pagination.append(`
        <li class="page-item ${appState.currentPage >= totalPages - 1 ? 'disabled' : ''}">
            <a class="page-link" href="#" data-page="${appState.currentPage + 1}" aria-label="Next">
                &raquo;
            </a>
        </li>
    `);
}

// Event Handling ==============================================

function setupEventListeners() {
    // Search with debounce
    let searchTimer;
    $("#searchInput").on('input', function() {
        clearTimeout(searchTimer);
        searchTimer = setTimeout(() => {
            appState.keyword = $(this).val().trim();
            appState.currentPage = 0;
            loadJobs();
        }, 500);
    });

    // Add Job Form
    $("#addJobForm").on('submit', function(e) {
        e.preventDefault();
        const jobData = collectFormData(this);

        if (!validateForm(this, jobData)) return;

        setButtonLoading('#savebtn', true);

        createJob(jobData)
            .done(function(response) {
                if (response && response.status === 200) {
                    $('#addJobModal').modal('hide');
                    showToast('success', 'Job created successfully!');
                    loadJobs();
                    $('#addJobForm')[0].reset();
                } else {
                    handleAjaxError({ responseJSON: response });
                }
            })
            .fail(handleAjaxError)
            .always(function() {
                setButtonLoading('#savebtn', false);
            });
    });

    // Edit Job Form
    $("#editJobForm").on('submit', function(e) {
        e.preventDefault();
        const jobData = collectFormData(this);
        jobData.id = $("#editJobId").val();

        if (!validateForm(this, jobData)) return;

        setButtonLoading('#update', true);

        updateJob(jobData)
            .done(function(response) {
                if (response && response.status === 200) {
                    $('#editJobModal').modal('hide');
                    showToast('success', 'Job updated successfully!');
                    loadJobs();
                } else {
                    handleAjaxError({ responseJSON: response });
                }
            })
            .fail(handleAjaxError)
            .always(function() {
                setButtonLoading('#update', false);
            });
    });

    // Edit Button Click
    // $(document).on('click', '.edit-btn', function() {
    //     try {
    //         const job = JSON.parse($(this).data('job'));
    //         openEditModal(job);
    //     } catch (e) {
    //         showToast('error', 'Failed to parse job data');
    //     }
    // });

    $(document).on('click', '.edit-btn', function() {
        try {
            const raw = $(this).attr('data-job');  // Use attr, not .data()
            const job = JSON.parse(raw);
            openEditModal(job);
        } catch (e) {
            showToast('error', 'Failed to parse job data');
            console.error("Job parse error:", e);
        }
    });


    // Status Change Button
    $(document).on('click', '.status-btn', function() {
        const jobId = $(this).data('id');
        const currentStatus = $(this).data('status');
        confirmStatusChange(jobId, currentStatus);
    });

    // Pagination Click
    $(document).on('click', '.page-link', function(e) {
        e.preventDefault();
        const page = $(this).data('page');
        if (page !== undefined && !$(this).parent().hasClass('disabled')) {
            appState.currentPage = page;
            loadJobs();
        }
    });
}

// Form Handling ==============================================

function collectFormData(form) {
    const $form = $(form);
    const getValue = (selector) => {
        const val = $form.find(selector).val();
        return typeof val === 'string' ? val.trim() : '';
    };

    return {
        jobTitle: getValue('#jobTitle') || getValue('#editJobTitle'),
        company: getValue('#companyName') || getValue('#editCompanyName'),
        location: getValue('#jobLocation') || getValue('#editJobLocation'),
        type: getValue('#jobType') || getValue('#editJobType'),
        jobDescription: getValue('#jobDescription') || getValue('#editJobDescription'),
        status: "ACTIVE"
    };
}

function validateForm(form, data) {
    let isValid = true;

    // Clear previous errors
    $(form).find('.is-invalid').removeClass('is-invalid');
    $(form).find('.invalid-feedback').remove();

    // Validate required fields
    const requiredFields = [
        { id: '#jobTitle' || '#editJobTitle', name: 'Job Title', value: data.jobTitle },
        { id: '#companyName' || '#editCompanyName', name: 'Company', value: data.company },
        { id: '#jobLocation' || '#editJobLocation', name: 'Location', value: data.location },
        { id: '#jobType' || '#editJobType', name: 'Job Type', value: data.type }
    ];

    requiredFields.forEach(field => {
        if (!field.value) {
            $(form).find(field.id).addClass('is-invalid')
                .after(`<div class="invalid-feedback">${field.name} is required</div>`);
            isValid = false;
        }
    });

    return isValid;
}

function openEditModal(job) {
    if (!job) return;

    $("#editJobId").val(job.id);
    $("#editJobTitle").val(job.jobTitle);
    $("#editCompanyName").val(job.company);
    $("#editJobLocation").val(job.location);
    $("#editJobType").val(job.type);
    $("#editJobDescription").val(job.jobDescription);

    $("#editJobModal").modal('show');
}

function confirmStatusChange(id, currentStatus) {
    const action = currentStatus === "ACTIVE" ? "Deactivate" : "Activate";
    const $btn = $(`button[data-id="${id}"]`);

    Swal.fire({
        title: `Confirm ${action}`,
        text: `Are you sure you want to ${action.toLowerCase()} this job?`,
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: `Yes, ${action}`,
        cancelButtonText: "Cancel"
    }).then((result) => {
        if (result.isConfirmed) {
            setButtonLoading($btn, true);
            changeJobStatus(id)
                .done(function(response) {
                    if (response && response.status === 200) {
                        showToast('success', `Job ${action.toLowerCase()}d successfully!`);
                        loadJobs();
                    } else {
                        handleAjaxError({ responseJSON: response });
                    }
                })
                .fail(handleAjaxError)
                .always(function() {
                    setButtonLoading($btn, false);
                });
        }
    });
}

// Utility Functions ==============================================

function handleAjaxError(xhr) {
    let errorMsg = "Operation failed";
    if (xhr.responseJSON && xhr.responseJSON.message) {
        errorMsg = xhr.responseJSON.message;
    } else if (xhr.statusText) {
        errorMsg = xhr.statusText;
    }
    showToast('error', errorMsg);
    console.error("AJAX Error:", xhr);
}

function setButtonLoading(selector, isLoading) {
    const $btn = $(selector);
    if (isLoading) {
        $btn.prop('disabled', true)
            .html(`<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> ${$btn.text()}`);
    } else {
        $btn.prop('disabled', false);
        if ($btn.hasClass('status-btn')) {
            const status = $btn.data('status');
            $btn.text(status === 'ACTIVE' ? 'Deactivate' : 'Activate');
        } else {
            $btn.text($btn.is('#savebtn') ? 'Save Job' : 'Save Changes');
        }
    }
}

function showLoading(show) {
    if (show) {
        $("#jobsTableBody").html(`
            <tr>
                <td colspan="7" class="text-center py-4">
                    <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Loading...</span>
                    </div>
                </td>
            </tr>
        `);
    }
}

function showToast(icon, message) {
    const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 3000,
        timerProgressBar: true
    });

    Toast.fire({
        icon: icon,
        title: message
    });
}

function escapeHtml(unsafe) {
    if (!unsafe) return '';
    return unsafe.toString()
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;")
        .replace(/"/g, "&quot;")
        .replace(/'/g, "&#039;");
}