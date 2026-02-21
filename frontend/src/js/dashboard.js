const token = sessionStorage.getItem("jwt");
if(!token) 
{
    window.location.href="../index.html"; // Log out user if their token has expired
    alert("You have been logged out for security reasons. Please log back in.");
}

const hamburger = document.getElementById("hamburger");
const logo = document.getElementById("logo");
const settings = document.getElementById("settings");
const logout = document.getElementById("logout");
const newEntry = document.getElementById("new-entry");
const entryExit = document.getElementById("entry-exit");
const entrySave = document.getElementById("save-entry");

let main = document.getElementById("main");
let optionPopUp = document.getElementById("options-pop-up");
let entryPopUp = document.getElementById("entry-pop-up");

let titleInput = document.getElementById("entry-title");
let descriptionInput = document.getElementById("entry-description");
let dateInput = document.getElementById("entry-date");

logo.addEventListener("click", () => {
    window.location.href = "dashboard.html";
});

settings.addEventListener("click", () => {
    alert("Account settings coming soon!");
});

logout.addEventListener("click", () => {
    sessionStorage.removeItem("jwt"); // Remove token
    window.location.href="../index.html"; // Log out user
});

newEntry.addEventListener("click", () => {
    main.style.display = "none";
    entryPopUp.style.display = "block";
});
entryExit.addEventListener("click", () => {
    titleInput.value = "";
    descriptionInput.value = "";
    dateInput.value = "";
    entryPopUp.style.display = "none";
    main.style.display = "block";
});

window.addEventListener("click", () =>{
    optionPopUp.style.display = "none";
});

hamburger.addEventListener("click", (event) => {
    event.stopPropagation();
    optionPopUp.style.display = optionPopUp.style.display === "block" ? "none": "block"
});

optionPopUp.addEventListener("click", (event)=>{
    event.stopPropagation();
});


entrySave.addEventListener("click", () => {
    const title = titleInput.value;
    const description = descriptionInput.value;
    const date = dateInput.value;
    // Send HTTP request to backend to create journal entry
    fetch("http://localhost:8080/api/lifts", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authentication": `Bearer ${token}`
        },
        body: JSON.stringify({ title, description, date })
    })
    .then(response => {
        if (response.ok) {
            alert("Journal entry created successfully!");
            entryPopUp.style.display = "none";
            main.style.display = "block";
            // Optionally, refresh the list of journal entries here
        } else {
            alert("Failed to create journal entry.");
        }
        titleInput.value = "";
        descriptionInput.value = "";
        dateInput.value = "";

    });
});