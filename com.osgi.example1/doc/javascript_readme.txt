///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
1. https://stackoverflow.com/questions/12781205/live-detect-browser-size-jquery-javascript

JavaScript
------------------------------------------------------------------------------------------------
function jsUpdateSize(){
    // Get the dimensions of the viewport
    var width = window.innerWidth ||
                document.documentElement.clientWidth ||
                document.body.clientWidth;
    var height = window.innerHeight ||
                 document.documentElement.clientHeight ||
                 document.body.clientHeight;

    document.getElementById('jsWidth').innerHTML = width;  // Display the width
    document.getElementById('jsHeight').innerHTML = height;// Display the height
};
window.onload = jsUpdateSize;       // When the page first loads
window.onresize = jsUpdateSize;     // When the browser changes size
------------------------------------------------------------------------------------------------

jQuery
------------------------------------------------------------------------------------------------
function jqUpdateSize(){
    // Get the dimensions of the viewport
    var width = $(window).width();
    var height = $(window).height();

    $('#jqWidth').html(width);      // Display the width
    $('#jqHeight').html(height);    // Display the height
};
$(document).ready(jqUpdateSize);    // When the page first loads
$(window).resize(jqUpdateSize);     // When the browser changes size
------------------------------------------------------------------------------------------------

HTML
------------------------------------------------------------------------------------------------
<!-- JavaScript -->
<p>
    <strong>JavaScript resize:</strong>
    <span id="jsWidth">0</span>,
    <span id="jsHeight">0</span>
</p>
<br/>

<!-- jQuery -->
<p>
    <strong>jQuery resize:</strong>
    <span id="jqWidth">0</span>,
    <span id="jqHeight">0</span>
</p>
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
2. https://stackoverflow.com/questions/322378/javascript-check-if-mouse-button-down
(1) mouse down
------------------------------------------------------------------------------------------------
var mouseDown = 0;
document.body.onmousedown = function() { 
  ++mouseDown;
}
document.body.onmouseup = function() {
  --mouseDown;
}
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
if(mouseDown){
  // crikey! isn't she a beauty?
}
------------------------------------------------------------------------------------------------

(2) mouse down detect button
------------------------------------------------------------------------------------------------
// let's pretend that a mouse doesn't have more than 9 buttons
var mouseDown = [0, 0, 0, 0, 0, 0, 0, 0, 0],
    mouseDownCount = 0;
document.body.onmousedown = function(evt) { 
  ++mouseDown[evt.button];
  ++mouseDownCount;
}
document.body.onmouseup = function(evt) {
  --mouseDown[evt.button];
  --mouseDownCount;
}
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
if(mouseDownCount){
  // alright, let's lift the little bugger up!
  for(var i = 0; i < mouseDown.length; ++i){
    if(mouseDown[i]){
      // we found it right there!
    }
  }
}
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
0 for "nothing is pressed"
1 for left
2 for right
4 for middle
and any combination of above, e.g., 5 for left + middle
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
3. Cursor 
(1) http://www.javascripter.net/faq/stylesc.htm (see 24_cursor_styles.gif)
------------------------------------------------------------------------------------------------
auto        move           no-drop      col-resize
all-scroll  pointer        not-allowed  row-resize
crosshair   progress       e-resize     ne-resize
default     text           n-resize     nw-resize
help        vertical-text  s-resize     se-resize
inherit     wait           w-resize     sw-resize
------------------------------------------------------------------------------------------------

(2) https://stackoverflow.com/questions/8809909/change-cursor-to-finger-pointer
------------------------------------------------------------------------------------------------
inline css:
<a class="menu_links" onclick="displayData(11,1,0,'A')" onmouseover="" style="cursor: pointer;"> A </a>

onMouseOver="this.style.cursor='pointer'"

css:
a.menu_links { cursor: pointer; }

a.menu_links {
    cursor: pointer;
}
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
4. Detect mouse out of screen 
https://stackoverflow.com/questions/923299/how-can-i-detect-when-the-mouse-leaves-the-window

------------------------------------------------------------------------------------------------
<html>
<head>
<script type="text/javascript">
function addEvent(obj, evt, fn) {
    if (obj.addEventListener) {
        obj.addEventListener(evt, fn, false);
    }
    else if (obj.attachEvent) {
        obj.attachEvent("on" + evt, fn);
    }
}
addEvent(window,"load",function(e) {
    addEvent(document, "mouseout", function(e) {
        e = e ? e : window.event;
        var from = e.relatedTarget || e.toElement;
        if (!from || from.nodeName == "HTML") {
            // stop your drag event here
            // for now we can just use an alert
            alert("left window");
        }
    });
});
</script>
</head>
<body></body>
</html>
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
5. Javascript tree view
https://www.w3schools.com/howto/howto_js_treeview.asp


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
6. Date and time
https://www.toptal.com/software/definitive-guide-to-datetime-manipulation
https://tecadmin.net/get-current-date-time-javascript/


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
7. Math
https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/pow
https://www.w3schools.com/jsref/jsref_sqrt.asp


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
8. setInterval() and clearInterval() and setTimeout()
(GOOD) https://www.elated.com/javascript-timers-with-settimeout-and-setinterval/
(GOOD) https://www.w3schools.com/jsref/met_win_clearinterval.asp
https://www.kirupa.com/snippets/move_element_to_click_position.htm
https://javascript.info/settimeout-setinterval
https://hacks.mozilla.org/2011/08/animating-with-javascript-from-setinterval-to-requestanimationframe/

https://stackoverflow.com/questions/14507718/javascript-run-setinterval-only-once

------------------------------------------------------------------------------------------------
<!DOCTYPE html>
<html>
<body>

<p>A script on this page starts this clock:</p>

<p id="demo"></p>

<button onclick="myStopFunction()">Stop time</button>

<script>
var myVar = setInterval(myTimer, 1000);

function myTimer() {
  var d = new Date();
  var t = d.toLocaleTimeString();
  document.getElementById("demo").innerHTML = t;
}

function myStopFunction() {
  clearInterval(myVar);
}
</script>

</body>
</html>
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
7. window events

window.onload
https://www.htmlgoodies.com/beyond/javascript/article.php/3724571/Using-Multiple-JavaScript-Onload-Functions.htm

window resize
https://www.tutorialrepublic.com/faq/how-to-capture-browser-window-resize-event-in-javascript.php

------------------------------------------------------------------------------------------------
function start() {
  func1();
  func2();
}
window.onload = start;
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    }
  }
}
addLoadEvent(nameOfSomeFunctionToRunOnPageLoad);
addLoadEvent(function() {
  /* more code to run on page load */
});

------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
function func1() {
  alert("This is the first.");
}
function func2() {
  alert("This is the second.");
}

function addLoadEvent(func) {
  var oldonload = window.onload;
  if (typeof window.onload != 'function') {
    window.onload = func;
  } else {
    window.onload = function() {
      if (oldonload) {
        oldonload();
      }
      func();
    }
  }
}
addLoadEvent(func1);
addLoadEvent(func2);
addLoadEvent(function() {
    document.body.style.backgroundColor = '#EFDF95';
})
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>JavaScript Window Resize Event</title>
</head>
<body>
    <div id="result"></div>
 
    <script>        
    // Defining event listener function
    function displayWindowSize(){
        // Get width and height of the window excluding scrollbars
        var w = document.documentElement.clientWidth;
        var h = document.documentElement.clientHeight;
        
        // Display result inside a div element
        document.getElementById("result").innerHTML = "Width: " + w + ", " + "Height: " + h;
    }
     
    // Attaching the event listener function to window's resize event
    window.addEventListener("resize", displayWindowSize);
    
    // Calling the function for the first time
    displayWindowSize();
    </script>
    <p><strong>Note:</strong> Please resize the browser window to see how it works.</p>
</body>
</html> 
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
8. game and annimation

http://nokarma.org/2011/02/02/javascript-game-development-the-game-loop/
https://developer.mozilla.org/en-US/docs/Web/API/window/requestAnimationFrame

------------------------------------------------------------------------------------------------
var start = null;
var element = document.getElementById('SomeElementYouWantToAnimate');

function step(timestamp) {
  if (!start) start = timestamp;
  var progress = timestamp - start;
  element.style.transform = 'translateX(' + Math.min(progress / 10, 200) + 'px)';
  if (progress < 2000) {
    window.requestAnimationFrame(step);
  }
}

window.requestAnimationFrame(step);
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
9. min/max screen fullscreen

https://developer.mozilla.org/en-US/docs/Web/API/Fullscreen_API
https://www.w3schools.com/howto/howto_js_fullscreen.asp
https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_fullscreen
https://stackoverflow.com/questions/1125084/how-to-make-the-window-full-screen-with-javascript-stretching-all-over-the-scre
https://davidwalsh.name/fullscreen

------------------------------------------------------------------------------------------------
function toggleFullScreen() {
  if (!document.fullscreenElement) {
      document.documentElement.requestFullscreen();
  } else {
    if (document.exitFullscreen) {
      document.exitFullscreen(); 
    }
  }
}
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
<script>
/* Get the element you want displayed in fullscreen mode (a video in this example): */
var elem = document.getElementById("myvideo");

/* When the openFullscreen() function is executed, open the video in fullscreen.
Note that we must include prefixes for different browsers, as they don't support the requestFullscreen method yet */
function openFullscreen() {
  if (elem.requestFullscreen) {
    elem.requestFullscreen();
  } else if (elem.mozRequestFullScreen) { /* Firefox */
    elem.mozRequestFullScreen();
  } else if (elem.webkitRequestFullscreen) { /* Chrome, Safari and Opera */
    elem.webkitRequestFullscreen();
  } else if (elem.msRequestFullscreen) { /* IE/Edge */
    elem.msRequestFullscreen();
  }
}
</script>
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
<script>
/* Get the documentElement (<html>) to display the page in fullscreen */
var elem = document.documentElement;

/* View in fullscreen */
function openFullscreen() {
  if (elem.requestFullscreen) {
    elem.requestFullscreen();
  } else if (elem.mozRequestFullScreen) { /* Firefox */
    elem.mozRequestFullScreen();
  } else if (elem.webkitRequestFullscreen) { /* Chrome, Safari and Opera */
    elem.webkitRequestFullscreen();
  } else if (elem.msRequestFullscreen) { /* IE/Edge */
    elem.msRequestFullscreen();
  }
}

/* Close fullscreen */
function closeFullscreen() {
  if (document.exitFullscreen) {
    document.exitFullscreen();
  } else if (document.mozCancelFullScreen) { /* Firefox */
    document.mozCancelFullScreen();
  } else if (document.webkitExitFullscreen) { /* Chrome, Safari and Opera */
    document.webkitExitFullscreen();
  } else if (document.msExitFullscreen) { /* IE/Edge */
    document.msExitFullscreen();
  }
}
</script>
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
function requestFullScreen(element) {
    // Supports most browsers and their versions.
    var requestMethod = element.requestFullScreen || element.webkitRequestFullScreen || element.mozRequestFullScreen || element.msRequestFullScreen;

    if (requestMethod) { // Native full screen.
        requestMethod.call(element);
    } else if (typeof window.ActiveXObject !== "undefined") { // Older IE.
        var wscript = new ActiveXObject("WScript.Shell");
        if (wscript !== null) {
            wscript.SendKeys("{F11}");
        }
    }
}

var elem = document.body; // Make the body go full screen.
requestFullScreen(elem);
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
// Find the right method, call on correct element
function launchIntoFullscreen(element) {
  if(element.requestFullscreen) {
    element.requestFullscreen();
  } else if(element.mozRequestFullScreen) {
    element.mozRequestFullScreen();
  } else if(element.webkitRequestFullscreen) {
    element.webkitRequestFullscreen();
  } else if(element.msRequestFullscreen) {
    element.msRequestFullscreen();
  }
}

// Launch fullscreen for browsers that support it!
launchIntoFullscreen(document.documentElement); // the whole page
launchIntoFullscreen(document.getElementById("videoElement")); // any individual element
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
10. iframe and positioning

https://www.w3schools.com/css/css_positioning.asp


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
11. pinch and gesture and touchpad

https://stackoverflow.com/questions/11183174/simplest-way-to-detect-a-pinch

https://stackoverflow.com/questions/9977876/two-fingered-scroll-js-on-touchpad
https://jsfiddle.net/64p5r459/2/
https://stackoverflow.com/questions/3515446/jquery-mousewheel-detecting-when-the-wheel-stops (detect wheel stops)

https://github.com/inuyaksa/jquery.nicescroll/issues/799 (allow prevent default)
------------------------------------------------------------------------------------------------
with this
https://github.com/Tolc/jquery.nicescroll/blob/6876279cb895bc6ad892c71f8a327dce85e18e9b/jquery.nicescroll.js#L2555
(passiveSupported && (active || el == window.document || el == window.document.body || el == window)) ? el.addEventListener(name, fn, { passive: false, capture: bubble }) : el.addEventListener(name, fn, bubble || false);
------------------------------------------------------------------------------------------------

https://stackoverflow.com/questions/11183174/simplest-way-to-detect-a-pinch/
https://developer.apple.com/documentation/webkitjs/gestureevent
------------------------------------------------------------------------------------------------
node.addEventListener('gestureend', function(e) {
    if (e.scale < 1.0) {
        // User moved fingers closer together
    } else if (e.scale > 1.0) {
        // User moved fingers further apart
    }
}, false);
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
var doScroll = function(e) {
  const deltaX = Math.max(-1, Math.min(1, e.deltaX));
  const deltaY = Math.max(-1, Math.min(1, e.deltaY));

  // Do something with `delta`
  const elmId = `#x${deltaX}y${deltaY}`;
  document.querySelector("#info").innerHTML = `x:${e.deltaX} y:${e.deltaY}`;
  document.querySelector(elmId).className = "scrolled";

  window.setTimeout(id => document.querySelector(id).className = "", 0, elmId);

  e.preventDefault();
};

if (window.addEventListener) {
  window.addEventListener("wheel", doScroll, false);
}
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
12. context menu

https://dev.to/iamafro/how-to-create-a-custom-context-menu--5d7p
http://help.dottoro.com/ljhwjsss.php (oncontextmenu)
https://developer.mozilla.org/en-US/docs/Web/API/Element/contextmenu_event


------------------------------------------------------------------------------------------------
<div class="menu">
  <ul class="menu-options">
    <li class="menu-option">Back</li>
    <li class="menu-option">Reload</li>
    <li class="menu-option">Save</li>
    <li class="menu-option">Save As</li>
    <li class="menu-option">Inspect</li>
  </ul>
</div>
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
.menu {
  width: 120px;
  box-shadow: 0 4px 5px 3px rgba(0, 0, 0, 0.2);
  position: relative;
  display: none;

  .menu-options {
    list-style: none;
    padding: 10px 0;

    .menu-option {
      font-weight: 500;
      font-size: 14px;
      padding: 10px 40px 10px 20px;
      cursor: pointer;

      &:hover {
        background: rgba(0, 0, 0, 0.2);
      }
    }
  }
}
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
const toggleMenu = command => {
  menu.style.display = command === "show" ? "block" : "none";
};

const setPosition = ({ top, left }) => {
  menu.style.left = `${left}px`;
  menu.style.top = `${top}px`;
  toggleMenu('show');
};


window.addEventListener("contextmenu", e => {
  e.preventDefault();
  const origin = {
    left: e.pageX,
    top: e.pageY
  };
  setPosition(origin);
  return false;
});
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
const menu = document.querySelector(".menu");
let menuVisible = false;

const toggleMenu = command => {
  menu.style.display = command === "show" ? "block" : "none";
  menuVisible = !menuVisible;
};

const setPosition = ({ top, left }) => {
  menu.style.left = `${left}px`;
  menu.style.top = `${top}px`;
  toggleMenu("show");
};

window.addEventListener("click", e => {
  if(menuVisible)toggleMenu("hide");
});

window.addEventListener("contextmenu", e => {
  e.preventDefault();
  const origin = {
    left: e.pageX,
    top: e.pageY
  };
  setPosition(origin);
  return false;
});
------------------------------------------------------------------------------------------------





