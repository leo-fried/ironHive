const register = document.getElementById("registerButton");
const login = document.getElementById("loginButton");

register.addEventListener("click", () => {
    window.location.href = "./src/register.html";
});

login.addEventListener("click", () => {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value; 
    // Send HTTP request to backend to authenticate user
    fetch("http://localhost:8080/api/users/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ username, password })
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            alert("Login failed");
        }
    })
    // If login successful, go to dashboard, else display error
    .then(data => data ? window.location.href = "./src/dashboard.html": alert("Invalid username or password entered."));
    
});