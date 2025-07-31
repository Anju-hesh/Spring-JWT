$("#sign-up-btn").click(function () {
    const email = $("#email").val();
    const password = $("#password").val();

    $.ajax({
        url: "http://localhost:8080/auth/login",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({ email, password }),
        success: function (response) {
            const token = response.data;

            // Store JWT in cookies
            document.cookie = `jwt=${token}; path=/;`;

            // Decode token to get role
            const payload = JSON.parse(atob(token.split('.')[1]));
            const role = payload.role;

            if (role === "ADMIN") {
                window.location.href = "admin-dashboard.html";
            } else if (role === "USER") {
                window.location.href = "user-dashboard.html";
            }
        },
        error: function () {
            alert("Login failed");
        }
    });
});
