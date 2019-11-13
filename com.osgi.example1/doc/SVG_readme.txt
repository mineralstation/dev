///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
0. Specs
http://xahlee.info/js/svg_path_spec.html
http://xahlee.info/js/svg_path_ellipse_arc.html
http://wphooper.com/svg/examples/text.php
http://wphooper.com/svg/examples/line.php
https://vanseodesign.com/web-design/svg-text-layout-and-alignment/

https://www.w3.org/TR/filter-effects-1/#FilterPrimitivesOverview (for filter)

https://developer.mozilla.org/en-US/docs/Web/SVG/Element/image
https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/transform
https://developer.mozilla.org/en-US/docs/Web/API/Pointer_events/Pinch_zoom_gestures

SVG tutorials
http://tutorials.jenkov.com/svg/index.html


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
1. ViewPort and ViewBox
https://webdesign.tutsplus.com/tutorials/svg-viewport-and-viewbox-for-beginners--cms-30844
https://developer.mozilla.org/en-US/docs/Web/SVG/Attribute/viewBox

SVG Image and ViewPort example:

https://codepen.io/pen/?&editable=true
----------------------------------------------------------------------------------------------------------------
<!-- Learn about this code on MDN: https://developer.mozilla.org/en-US/docs/Web/SVG/Element/image -->

<svg width="200" height="200"
  xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="100 100 200 200">       
  <image xlink:href="https://mdn.mozillademos.org/files/6457/mdn_logo_only_color.png" height="200" width="200"/>
</svg>
----------------------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
2. 3D Box
http://www.petercollingridge.co.uk/blog/3d-svg/rotating-3d-svg-cube/


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
3. SVG Flower
https://developer.mozilla.org/en-US/docs/Web/SVG/Tutorial/SVG_and_CSS


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
4. SVG context menu
http://bl.ocks.org/jakosz/ce1e63d5149f64ac7ee9
https://stackoverflow.com/questions/40172226/right-click-menu-on-svg

------------------------------------------------------------------------------------------------
<!DOCTYPE HTML>
<meta charset='utf-8'>
<style>

body {
    background: rgb(200,200,200);
}

svg {
    margin: auto;
    display: block;
    background: white;
}

</style>
<body>

<script src="https://d3js.org/d3.v3.min.js"></script>
<script>

var mainw = 960,
    mainh = 500;

function contextMenu() {
    var height,
        width, 
        margin = 0.1, // fraction of width
        items = [], 
        rescale = false, 
        style = {
            'rect': {
                'mouseout': {
                    'fill': 'rgb(244,244,244)', 
                    'stroke': 'white', 
                    'stroke-width': '1px'
                }, 
                'mouseover': {
                    'fill': 'rgb(200,200,200)'
                }
            }, 
            'text': {
                'fill': 'steelblue', 
                'font-size': '13'
            }
        }; 
    
    function menu(x, y) {
        d3.select('.context-menu').remove();
        scaleItems();

        // Draw the menu
        d3.select('svg')
            .append('g').attr('class', 'context-menu')
            .selectAll('tmp')
            .data(items).enter()
            .append('g').attr('class', 'menu-entry')
            .style({'cursor': 'pointer'})
            .on('mouseover', function(){ 
                d3.select(this).select('rect').style(style.rect.mouseover) })
            .on('mouseout', function(){ 
                d3.select(this).select('rect').style(style.rect.mouseout) });
        
        d3.selectAll('.menu-entry')
            .append('rect')
            .attr('x', x)
            .attr('y', function(d, i){ return y + (i * height); })
            .attr('width', width)
            .attr('height', height)
            .style(style.rect.mouseout);
        
        d3.selectAll('.menu-entry')
            .append('text')
            .text(function(d){ return d; })
            .attr('x', x)
            .attr('y', function(d, i){ return y + (i * height); })
            .attr('dy', height - margin / 2)
            .attr('dx', margin)
            .style(style.text);

        // Other interactions
        d3.select('body')
            .on('click', function() {
                d3.select('.context-menu').remove();
            });

    }
    
    menu.items = function(e) {
        if (!arguments.length) return items;
        for (i in arguments) items.push(arguments[i]);
        rescale = true;
        return menu;
    }

    // Automatically set width, height, and margin;
    function scaleItems() {
        if (rescale) {
            d3.select('svg').selectAll('tmp')
                .data(items).enter()
                .append('text')
                .text(function(d){ return d; })
                .style(style.text)
                .attr('x', -1000)
                .attr('y', -1000)
                .attr('class', 'tmp');
            var z = d3.selectAll('.tmp')[0]
                      .map(function(x){ return x.getBBox(); });
            width = d3.max(z.map(function(x){ return x.width; }));
            margin = margin * width;
            width =  width + 2 * margin;
            height = d3.max(z.map(function(x){ return x.height + margin / 2; }));
            
            // cleanup
            d3.selectAll('.tmp').remove();
            rescale = false;
        }
    }

    return menu;
}

d3.select('body').append('svg').attr('id', 'svg-main')
    .attr('width', mainw)
    .attr('height', mainh);

var menu = contextMenu().items('first item', 'second option', 'whatever, man');

d3.select('svg').append('rect')
    .attr('x', mainw/2 - 30)
    .attr('y', mainh/2 - 30)
    .attr('width', 60)
    .attr('height', 60)
    .on('contextmenu', function(){ 
        d3.event.preventDefault();
        menu(d3.mouse(this)[0], d3.mouse(this)[1]);
    });

</script>
</body>
------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
5. z-index
https://stackoverflow.com/questions/17786618/how-to-use-z-index-in-svg-elements


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
6. D3 Text
https://stackoverflow.com/questions/38130781/how-to-change-text-elements-in-d3


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
7. shadow glow, back glow and colored glow 

https://stackoverflow.com/questions/9630008/how-can-i-create-a-glow-around-a-rectangle-with-svg
http://jsfiddle.net/drewnoakes/gc8Bt/

https://yoksel.github.io/svg-filters/#/ (tools)

------------------------------------------------------------------------------------------------
<!-- a transparent grey drop-shadow that blends with the background colour -->
<filter id="shadow" width="1.5" height="1.5" x="-.25" y="-.25">
    <feGaussianBlur in="SourceAlpha" stdDeviation="2.5" result="blur"/>
    <feColorMatrix result="bluralpha" type="matrix" values=
            "1 0 0 0   0
             0 1 0 0   0
             0 0 1 0   0
             0 0 0 0.4 0 "/>
    <feOffset in="bluralpha" dx="3" dy="3" result="offsetBlur"/>
    <feMerge>
        <feMergeNode in="offsetBlur"/>
        <feMergeNode in="SourceGraphic"/>
    </feMerge>
</filter>

<!-- a transparent grey glow with no offset -->
<filter id="black-glow">
    <feColorMatrix type="matrix" values=
                "0 0 0 0   0
                 0 0 0 0   0
                 0 0 0 0   0
                 0 0 0 0.7 0"/>
    <feGaussianBlur stdDeviation="2.5" result="coloredBlur"/>
    <feMerge>
        <feMergeNode in="coloredBlur"/>
        <feMergeNode in="SourceGraphic"/>
    </feMerge>
</filter>

<!-- a transparent glow that takes on the colour of the object it's applied to -->
<filter id="glow">
    <feGaussianBlur stdDeviation="2.5" result="coloredBlur"/>
    <feMerge>
        <feMergeNode in="coloredBlur"/>
        <feMergeNode in="SourceGraphic"/>
    </feMerge>
</filter>
------------------------------------------------------------------------------------------------











