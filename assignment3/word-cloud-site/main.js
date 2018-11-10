// define simplebar settings
const simpleConfig = {
  autoHide: false,
  scrollbarMinSize: 100
};

// create custom scrollbar for all elements with simple-bar class
Array.from(document.querySelectorAll('.simple-bar')).forEach(el => new SimpleBar(el, simpleConfig));

// toggle about-overlay when it itself is clicked
var aboutOverlay = document.getElementById('about-overlay');
aboutOverlay.addEventListener('mousedown', toggleOverlay);

// toggle about-overlay when about-btn clicked
var aboutBtn = document.getElementById('about-btn');
aboutBtn.addEventListener('mousedown', toggleOverlay);

// toggles the display property of the about-overlay between none and block
function toggleOverlay() {
  if (window.getComputedStyle(aboutOverlay, null).getPropertyValue('display') == 'none') {
    aboutOverlay.style.display = 'block';
  }
  else {
    aboutOverlay.style.display = 'none';
  }
}
