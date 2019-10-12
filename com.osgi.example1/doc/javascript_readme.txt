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
8. setInterval() and clearInterval()
(GOOD) https://www.elated.com/javascript-timers-with-settimeout-and-setinterval/
(GOOD) https://www.w3schools.com/jsref/met_win_clearinterval.asp
https://www.kirupa.com/snippets/move_element_to_click_position.htm
https://javascript.info/settimeout-setinterval
https://hacks.mozilla.org/2011/08/animating-with-javascript-from-setinterval-to-requestanimationframe/

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
