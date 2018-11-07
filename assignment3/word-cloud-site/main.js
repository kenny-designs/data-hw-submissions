// create custom scrollbar for lyrics with SimpleBar
new SimpleBar(document.getElementById('lyrics'));

// toggle about-overlay when it is clicked
var aboutOverlay = document.getElementById('about-overlay');
aboutOverlay.addEventListener('click', toggleOverlay);

// toggle about-overlay when about-btn clicked
var aboutBtn = document.getElementById('about-btn');
aboutBtn.addEventListener('click', toggleOverlay);

// toggles the display property of the about-overlay between none and table
function toggleOverlay() {
  if (window.getComputedStyle(aboutOverlay, null).getPropertyValue('display') == 'none') {
    aboutOverlay.style.display = 'table';
  }
  else {
    aboutOverlay.style.display = 'none';
  }
}
