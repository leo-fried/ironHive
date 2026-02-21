const register = document.getElementById("registerButton");
const login = document.getElementById("loginButton");
const logo = document.getElementById("logo");

let usernameInput = document.getElementById("username");
let passwordInput = document.getElementById("password");
let errorMsg = document.getElementById("error");

function incorrectCredentials()
{
    usernameInput.style.borderColor = "red";
    passwordInput.style.borderColor = "red";
    errorMsg.style.display = "block"
}
logo.addEventListener("click", () => {
    window.location.href = "./index.html";
});
register.addEventListener("click", () => {
    window.location.href = "./src/register.html";
});

login.addEventListener("click", () => {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value; 

    const basicAuth =btoa(`${username}:${password}`);
    // Send HTTP request to backend to authenticate user
    fetch("http://localhost:8080/token", {
        method: "POST",
        headers: {
            "Authorization": `Basic ${basicAuth}`
        }
    })
    .then(response => {
        if (response.ok) {
            return response.text();
        } else {
            throw new Error("Login Failed");
        }
    })
    // If login successful, go to dashboard, else display error
    .then(token => {
        sessionStorage.setItem("jwt", token);
        window.location.href = "./src/dashboard.html";
    })
    .catch(error => {
        console.error(error);
        incorrectCredentials();
    });
});