const hamburger = document.getElementById("hamburger");
let popUp = document.getElementById("options-pop-up");

window.addEventListener("click", () =>{
    popUp.style.display = "none";
});

hamburger.addEventListener("click", (event) => {
    event.stopPropagation();
    popUp.style.display = popUp.style.display === "block" ? "none": "block"
});

popUp.addEventListener("click", (event)=>{
    event.stopPropagation();
});


