const auth = (function() {

    const apiBase = 'http://localhost:8080';

    // Cookie helpers
    function setCookie(name, value, days) {
        const expires = days ? "; expires=" + new Date(Date.now() + days*864e5).toUTCString() : "";
        document.cookie = name + "=" + encodeURIComponent(value) + expires + "; path=/";
    }

    function getCookie(name) {
        return document.cookie.split('; ').reduce((r, v) => {
            const parts = v.split('=');
            return parts[0] === name ? decodeURIComponent(parts[1]) : r
        }, '');
    }

    function deleteCookie(name) {
        document.cookie = name + '=; Max-Age=0; path=/';
    }

    // Parse JWT token to get payload
    function parseJwt (token) {
        try {
            const base64Url = token.split('.')[1];
            const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
            return JSON.parse(jsonPayload);
        } catch(e) {
            return null;
        }
    };

    // Save token with expiration (in ms)
    function saveToken(token) {
        const payload = parseJwt(token);
        if (!payload || !payload.exp) return false;
        // JWT exp is in seconds, convert to days for cookie
        const expiryDate = new Date(payload.exp * 1000);
        const days = (expiryDate - new Date()) / 864e5;
        setCookie('jwtToken', token, days);
        return true;
    }

    // AJAX helper with token
    async function ajax(url, method = 'GET', data = null) {
        const token = getCookie('jwtToken');
        const headers = {
            'Content-Type': 'application/json'
        };
        if(token) {
            headers['Authorization'] = 'Bearer ' + token;
        }
        const resp = await fetch(apiBase + url, {
            method,
            headers,
            body: data ? JSON.stringify(data) : null
        });
        if (resp.status === 401 || resp.status === 403) {
            logout(); // Token expired or unauthorized
            throw new Error('Session expired. Please login again.');
        }
        return resp.json();
    }

    // Handle login form submit
    async function login(event) {
        event.preventDefault();
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const errorMsg = document.getElementById('errorMsg');
        errorMsg.textContent = '';

        try {
            const data = await ajax('/auth/login', 'POST', { username, password });
            console.log('Login response:', data);  // response inspect කිරීම සදහා

            if (data && data.data && data.data.accessToken) {
                const saved = saveToken(data.data.accessToken);
                if(!saved) throw new Error('Invalid token from server.');

                // Store username
                localStorage.setItem('username', username);

                const role = data.data.role || 'user';  // fallback 'user'
                localStorage.setItem('role', role);

                Swal.fire({
                    icon: 'success',
                    title: `Welcome ${role === 'ADMIN' ? 'Admin' : 'User'}`,
                    text: `Hello, ${username}`,
                    timer: 2000,
                    showConfirmButton: false
                }).then(() => {

                    window.location.href = "../12_Spring_Security_Front_End/jobdashboard.html";
                });

            } else {
                errorMsg.textContent = 'Login failed. Please check your credentials.';
            }
        } catch (err) {
            errorMsg.textContent = err.message;
        }
    }

    // Load dashboard page content
    async function loadDashboard() {
        const token = getCookie('jwtToken');
        if (!token) {
            logout();
            return;
        }
        try {
            // Try ADMIN endpoint first
            const res = await ajax('/hello', 'GET');
            const username = localStorage.getItem('username') || 'User';
            document.getElementById('welcomeMsg').textContent = `Hello Admin ${username}`;
        } catch (e) {
            try {
                // Try USER endpoint
                const res = await ajax('/hello/user', 'GET');
                const username = localStorage.getItem('username') || 'User';
                document.getElementById('welcomeMsg').textContent = `Hello User ${username}`;
            } catch (e2) {
                // If both fail, logout
                logout();
            }
        }
    }

    // Logout function
    function logout() {
        deleteCookie('jwtToken');
        localStorage.removeItem('username');
        window.location.href = 'index.html';
    }

    return {
        login,
        loadDashboard,
        logout
    };

})();

// Attach login form listener only on login page
if (document.getElementById('loginForm')) {
    document.getElementById('loginForm').addEventListener('submit', auth.login);
}
