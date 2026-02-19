const register = document.getElementById("registerButton");
const login = document.getElementById("loginButton");

let usernameInput = document.getElementById("username");
let passwordInput = document.getElementById("password");
let errorMsg = document.getElementById("error");

function incorrectCredentials()
{
    usernameInput.style.borderColor = "red";
    passwordInput.style.borderColor = "red";
    errorMsg.style.display = "block"
}
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
            alert("Login failed.");
        }
    })
    // If login successful, go to dashboard, else display error
    .then(data => data ? window.location.href = "./src/dashboard.html": incorrectCredentials());
    
});