const back = document.getElementById("loginButton");
const logo = document.getElementById("logo");

back.addEventListener("click", () => window.location.href = "../index.html");
logo.addEventListener("click", () => window.location.href = "../index.html");

const urlParams = new URLSearchParams(window.location.search);
const uuid = urlParams.get('uuid');

if(uuid)
{
    fetch(`http://localhost:8080/api/users/verify/${uuid}`,{
        method: 'PATCH',
        headers: {
            'Content-type': 'application/json',
        },
        body: JSON.stringify({ verified: true })
    })
    .then(response => {
        if(!response.ok) 
            {
                alert("Unable to verify account. Redirecting to login.");
                window.location.href = "../index.html";
            }

    })
}
else
{
    alert("Invalid Link. Redirecting to login.");
    window.location.href = "../index.html";
}