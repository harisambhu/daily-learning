const colorButton = document.getElementById("colorButton");

colorButton.addEventListener("click", function() {
    // Generate a random hex color
    const randomColor = Math.floor(Math.random()*16777215).toString(16).padStart(6, '0');
    document.body.style.background = "#" + randomColor;
    console.log("Background color changed to: #" + randomColor);
});
