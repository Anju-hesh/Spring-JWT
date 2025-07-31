$(document).ready(function() {
    // Initial sample jobs data
    let jobs = [
        {
            id: 1,
            title: "Frontend Developer",
            company: "Tech Solutions Inc.",
            location: "Colombo, Sri Lanka",
            type: "Full-time",
            status: "Active",
            description: "We are looking for a skilled Frontend Developer to join our team."
        },
        {
            id: 2,
            title: "Backend Engineer",
            company: "Data Systems",
            location: "Remote",
            type: "Full-time",
            status: "Active",
            description: "Join our backend team to build scalable APIs and services."
        },
        {
            id: 3,
            title: "UI/UX Designer",
            company: "Creative Minds",
            location: "Kandy, Sri Lanka",
            type: "Part-time",
            status: "Inactive",
            description: "Looking for a creative designer to revamp our user interfaces."
        }
    ];

    // Function to render the jobs table based on an array of job objects
    function renderJobs(jobsToRender) {
        const tbody = $('#jobsTableBody');
        tbody.empty();

        jobsToRender.forEach((job, index) => {
            const statusClass = job.status === "Active" ? "badge-active" : "badge-inactive";
            const row = `
        <tr>
          <td>${index + 1}</td>
          <td>${job.title}</td>
          <td>${job.company}</td>
          <td>${job.location}</td>
          <td>${job.type}</td>
          <td><span class="badge badge-status ${statusClass}">${job.status}</span></td>
          <td>
            <button class="btn btn-sm btn-action btn-edit" data-id="${job.id}" data-bs-toggle="modal" data-bs-target="#editJobModal">
              <i class="fas fa-edit"></i>
            </button>
            <button class="btn btn-sm btn-action btn-toggle" data-id="${job.id}">
              <i class="fas fa-power-off"></i>
            </button>
            <button class="btn btn-sm btn-action btn-delete" data-id="${job.id}">
              <i class="fas fa-trash"></i>
            </button>
          </td>
        </tr>
      `;
            tbody.append(row);
        });
    }

    // Initial rendering of the table
    renderJobs(jobs);

    // Search/filter jobs by any matching field
    $('#searchInput').on('keyup', function() {
        const searchTerm = $(this).val().toLowerCase();

        const filteredJobs = jobs.filter(job =>
            job.title.toLowerCase().includes(searchTerm) ||
            job.company.toLowerCase().includes(searchTerm) ||
            job.location.toLowerCase().includes(searchTerm) ||
            job.type.toLowerCase().includes(searchTerm)
        );

        renderJobs(filteredJobs);
    });

    // Add new job form submission
    $('#addJobForm').on('submit', function(e) {
        e.preventDefault();

        // Create new job object
        const newJob = {
            id: jobs.length > 0 ? Math.max(...jobs.map(j => j.id)) + 1 : 1,
            title: $('#jobTitle').val(),
            company: $('#companyName').val(),
            location: $('#jobLocation').val(),
            type: $('#jobType').val(),
            status: "Active",
            description: $('#jobDescription').val()
        };

        jobs.push(newJob);      // Add to jobs array
        renderJobs(jobs);       // Re-render table

        $(this).trigger('reset');  // Reset form
        $('#addJobModal').modal('hide'); // Hide modal

        Swal.fire({
            icon: 'success',
            title: 'Job Added!',
            text: 'The new job posting has been added successfully.',
            timer: 2000,
            showConfirmButton: false
        });
    });

    // Edit job - populate modal with existing data on edit button click
    $(document).on('click', '.btn-edit', function() {
        const jobId = $(this).data('id');
        const job = jobs.find(j => j.id === jobId);

        if (job) {
            $('#editJobId').val(job.id);
            $('#editJobTitle').val(job.title);
            $('#editCompanyName').val(job.company);
            $('#editJobLocation').val(job.location);
            $('#editJobType').val(job.type);
            $('#editJobDescription').val(job.description);
        }
    });

    // Update job on edit form submit
    $('#editJobForm').on('submit', function(e) {
        e.preventDefault();

        const jobId = parseInt($('#editJobId').val());
        const jobIndex = jobs.findIndex(j => j.id === jobId);

        if (jobIndex !== -1) {
            jobs[jobIndex] = {
                ...jobs[jobIndex],
                title: $('#editJobTitle').val(),
                company: $('#editCompanyName').val(),
                location: $('#editJobLocation').val(),
                type: $('#editJobType').val(),
                description: $('#editJobDescription').val()
            };

            renderJobs(jobs);
            $('#editJobModal').modal('hide');

            Swal.fire({
                icon: 'success',
                title: 'Job Updated!',
                text: 'The job posting has been updated successfully.',
                timer: 2000,
                showConfirmButton: false
            });
        }
    });

    // Toggle job status Active/Inactive
    $(document).on('click', '.btn-toggle', function() {
        const jobId = $(this).data('id');
        const jobIndex = jobs.findIndex(j => j.id === jobId);

        if (jobIndex !== -1) {
            jobs[jobIndex].status = jobs[jobIndex].status === "Active" ? "Inactive" : "Active";
            renderJobs(jobs);

            Swal.fire({
                icon: 'success',
                title: 'Status Updated!',
                text: 'The job status has been changed.',
                timer: 1500,
                showConfirmButton: false
            });
        }
    });

    // Delete job confirmation and deletion
    $(document).on('click', '.btn-delete', function() {
        const jobId = $(this).data('id');

        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                jobs = jobs.filter(j => j.id !== jobId);
                renderJobs(jobs);

                Swal.fire(
                    'Deleted!',
                    'The job posting has been deleted.',
                    'success'
                );
            }
        });
    });

});
