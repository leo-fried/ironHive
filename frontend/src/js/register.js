const register = document.getElementById("registerButton");
const back = document.getElementById("backButton");
const logo = document.getElementById("logo");

let emailInput = document.getElementById("email");
let usernameInput = document.getElementById("username");
let passwordInput = document.getElementById("password");

const border = emailInput.style.borderColor;

const errors = document.getElementsByClassName("text-error");
let errs = Array.from(errors);

logo.addEventListener("click", () => {
    window.location.href = "../index.html";
});

back.addEventListener("click", () => {
    window.location.href = "../index.html";
});

register.addEventListener("click", () => {
    // Reset state
    emailInput.style.borderColor = border;
    usernameInput.style.borderColor = border;
    passwordInput.style.borderColor = border;
    errs.forEach(e => { e.style.display = "none"; });

    const email = emailInput.value;
    const username = usernameInput.value;
    const password = passwordInput.value; 
    // Send HTTP request to backend to create user
    fetch("http://localhost:8080/api/users", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, username, password })
    })
    .then(response => {
        if (response.ok) {
            alert("Account created successfully! Please verify your email.");
            window.location.href = "../index.html";
        }
        else return response.json();
    })
    .then(errors => {
   
        if(errors.email)
        {
            emailInput.style.borderColor = "red";
            errs[0].style.display= "block";
            errs[0].textContent=errors.email;
        }
        if(errors.username)
        {
            usernameInput.style.borderColor = "red";
            errs[1].style.display = "block";
            errs[1].textContent=errors.username;
        }
        if(errors.password)
        {
            passwordInput.style.borderColor = "red";
            errs[2].style.display = "block";
            errs[2].textContent=errors.password;
        }
    })    
});
