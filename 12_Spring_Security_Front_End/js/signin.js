$(document).ready(function () {
    $('#sign-in-btn').click(function () {
        const email = $('#email').val();
        const password = $('#password').val();

        $.ajax({
            url: 'http://localhost:8080/auth/login',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                username: email,
                password: password
            }),
            success: function (res) {
                const token = res.data;

                // Store token in cookie (HttpOnly disabled for JS access)
                document.cookie = "token=" + token + "; path=/";

                // Decode to check role (optional)
                const payload = JSON.parse(atob(token.split('.')[1]));
                const roles = payload.roles;

                // Redirect based on role
                if (roles.includes("ROLE_ADMIN")) {
                    window.location.href = "admin-dashboard.html";
                } else if (roles.includes("ROLE_USER")) {
                    window.location.href = "user-dashboard.html";
                }
            },
            error: function () {
                alert("Invalid credentials!");
            }
        });
    });
});

