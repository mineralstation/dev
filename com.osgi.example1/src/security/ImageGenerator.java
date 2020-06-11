package security;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageGenerator {

	/**
	 * 
	 * @param content
	 * @param format
	 * @param file
	 */
	public void generate(String content, String format, File file) {
		try {
			BufferedImage image = ImageUtil.encodeV4(content);
			if (file.exists()) {
				file.delete();
			}
			ImageIO.write(image, format, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String userDir = System.getProperty("user.dir");

		ImageGenerator gen = new ImageGenerator();

		// ------------------------------------------------------
		// Examples javascript
		// ------------------------------------------------------
		// 1. global variables
		// (1) constant variables
		// var NORTH = 1;
		// var EAST = 2;
		// var SOUTH = 3;
		// var WEST = 4;
		// var NORTH_EAST = 5;
		// var SOUTH_EAST = 6;
		// var SOUTH_WEST = 7;
		// var NORTH_WEST = 8;
		// var MOVE = 10;
		// var tagName1 = "script";
		// var tagType1 = "text/javascript";

		// (2) global variables
		// main div
		// var mainDiv;
		// var mainDivElement;
		// main svg
		// var svg;
		// var svgElement;
		// layers
		// var depthLayerElement;
		// var bgLayerElement;

		// (3) top tools
		// top left tool D3 and DOM
		// var top_left_svg;
		// var topLeftToolD3;
		// var topLeftToolElement;
		// var topLeftTool_text1Element;
		// var topLeftTool_text2Element;
		// var topLeftTool_text3Element;

		// top right tool D3 and DOM
		// var top_right_svg;
		// var topRightToolColor = "#dedede";
		// var topRightToolColorHighlight = "#ffffff";

		// tool buttons D3
		// var screenDepthPartD3;
		// var screenGridPartD3;
		// var screenPerspectivePartD3;
		// var screenAnchorPart1D3;
		// var screenAnchorPart2D3;
		// var toggleFullScreenButtonD3;

		// full screen button SVG path
		// max to full screen icon
		// var fullScreenPath1 = 'M4 7 L4 4 L7 4 M10 4 L16 4 M11 9 L15 5 M16 4 L16 10 M16 13 L16 16 L13 16 M10 16 L4 16 M9 11 L5 15 M4 16 L4 10';
		// exit full screen icon
		// var fullScreenPath2 = 'M4 8 L4 4 L8 4 M11 3 L11 9 M12 8 L16 4 M11 9 L17 9 M16 12 L16 16 L12 16 M9 17 L9 11 M8 12 L4 16 M9 11 L3 11';

		// (4) screen options
		// var isScreenDepthEnabled = true;
		// var isScreenGridEnabled = true;
		// var isScreenPerspectiveEnabled = false;
		// var isScreenAnchored = false;
		// var isFullScreen = false;

		// 2. init functions
		// window.addEventListener("load", onLoad);
		// window.addEventListener("resize", onResize);
		// window.addEventListener("wheel", onWheel, {passive: false});

		// 3. init global variables (including the function)
		/*-
			function initGlobalVariables() {
				// main div
				mainDiv = d3.select("#main");
				mainDivElement = mainDiv.node();
				svg = d3.select("#main").select("svg");
				svgElement = d3.select("#main").select("svg").node();
			
				// layers
				depthLayerElement = svg.select("#layer_0").node();
				bgLayerElement = svg.select("#layer_1").node();
			
				// perspective
				viewShiftX = getViewShiftX();
				viewShiftY = getViewShiftY();
			
				// degree_y is rotate along Y axis
				// degree_x is rotate along X axis
				rotateY = -degree_y / divider * viewShiftX;
				rotateX = degree_x / divider * viewShiftY;
			
				var screen_width = window.screen.width;
				var screen_height = window.screen.height;
				var curr_width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
				var curr_height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;
				if (screen_width == curr_width && screen_height == curr_height) {
					isFullScreen = true;
				} else {
					isFullScreen = false;
				}
			
				top_left_svg = d3.select("#top_left_tools").select("svg");
				if (top_left_svg) {
					topLeftToolD3 = top_left_svg.select("#TopLeftTool");
					topLeftToolElement = topLeftToolD3.node();
					topLeftTool_text1Element = top_left_svg.select("#TopLeftTool_text1").node();
					topLeftTool_text2Element = top_left_svg.select("#TopLeftTool_text2").node();
					topLeftTool_text3Element = top_left_svg.select("#TopLeftTool_text3").node();
				}
			
				top_right_svg = d3.select("#top_right_tools").select("svg");
				if (top_right_svg) {
					screenDepthPartD3 = top_right_svg.select("#screenDepth_part");
					screenGridPartD3 = top_right_svg.select("#screenGrid_part");
					screenPerspectivePartD3 = top_right_svg.select("#screenPerspective_part");
					screenAnchorPart1D3 = top_right_svg.select("#screenAnchor_part1");
					screenAnchorPart2D3 = top_right_svg.select("#screenAnchor_part2");
			
					toggleFullScreenButtonD3 = top_right_svg.select("#toggleFullScreen1");
					if (isFullScreen) {
						toggleFullScreenButtonD3.attr("d", fullScreenPath2);
					} else {
						toggleFullScreenButtonD3.attr("d", fullScreenPath1);
					}
				}
			
				isScreenDepthEnabled = getScreenDepth();
				isScreenGridEnabled = getScreenGrid();
				isScreenPerspectiveEnabled = getScreenPerspective();
				isScreenAnchored = getScreenAnchored();
			
				refreshScreenDepth();
				refreshScreenGrid();
				refreshScreenPerspective();
				refreshScreenAnchor();
			}
			initGlobalVariables();
		*/

		// ------------------------------------------------------
		// javascript string to write
		// ------------------------------------------------------
		String content = "";
		// 1. declare global variables
		// (1) constant variables
		content += "window['NORTH'] = 1;";
		content += "window['EAST'] = 2;";
		content += "window['SOUTH'] = 3;";
		content += "window['WEST'] = 4;";
		content += "window['NORTH_EAST'] = 5;";
		content += "window['SOUTH_EAST'] = 6;";
		content += "window['SOUTH_WEST'] = 7;";
		content += "window['NORTH_WEST'] = 8;";
		content += "window['MOVE'] = 10;";
		content += "window['H_SCROLL'] = 11;";
		content += "window['V_SCROLL'] = 12;";
		content += "window['DND'] = 15;";
		content += "window['tagName1'] = 'script';";
		content += "window['tagType1'] = 'text/javascript';";

		// (2) global variables
		// main div
		content += "var mainDiv;";
		content += "var mainDivElement;";
		// main svg
		content += "var svg;";
		content += "var svgElement;";
		// layers
		content += "var depthLayerElement;";
		content += "var bgLayerElement;";

		// (3) top tools
		// top left tool D3 and DOM
		content += "var top_left_svg;";
		content += "var topLeftToolD3;";
		content += "var topLeftToolElement;";
		content += "var topLeftTool_text1Element;";
		content += "var topLeftTool_text2Element;";
		content += "var topLeftTool_text3Element;";
		content += "var topLeftTool_text4Element;";

		// top right tool D3 and DOM
		content += "var top_right_svg;";
		content += "window['topRightToolColor'] = '#dedede';";
		content += "window['topRightToolColorHighlight'] = '#ffffff';";

		// tool buttons D3
		content += "var screenDepthPartD3;";
		content += "var screenGridPartD3;";
		content += "var screenPerspectivePartD3;";
		content += "var screenAnchorPart1D3;";
		content += "var screenAnchorPart2D3;";
		content += "var toggleFullScreenButtonD3;";
		content += "var exitButtonD3;";

		// max to full screen icon
		content += "window['fullScreenPath1'] = 'M4 7 L4 4 L7 4  M10 4 L16 4 M11 9 L15 5 M16 4 L16 10  M16 13 L16 16 L13 16  M10 16 L4 16 M9 11 L5 15 M4 16 L4 10';";
		// exit full screen icon
		content += "window['fullScreenPath2'] = 'M4 8 L4 4 L8 4  M11 3 L11 9 M12 8 L16 4 M11 9 L17 9  M16 12 L16 16 L12 16  M9 17 L9 11 M8 12 L4 16 M9 11 L3 11';";

		// (4) screen options
		content += "var isScreenDepthEnabled = true;";
		content += "var isScreenGridEnabled = true;";
		content += "var isScreenPerspectiveEnabled = false;";
		content += "var isScreenAnchored = false;";
		content += "var backgroundImageURL = null;";
		content += "var isFullScreen = false;";

		// 2. execute initial functions
		content += "window.addEventListener(\"load\", onLoad);"; // capsule_03.js
		content += "window.addEventListener(\"resize\", onResize);"; // capsule_03.js
		// content += "window.addEventListener(\"wheel\", onWheel, {passive: false});"; // capsule.jsp

		// 3. init global variables
		content += "initGlobalVariables();"; // capsule_04.js
		content += "onUpdateViewBox();";
		content += "updateScreenSize();";

		gen.generate(content, "png", new File(userDir + "/doc/bg01.png"));
	}

}

// content += "var initGlobalVariablesFunction = initGlobalVariables;";
// content += "initGlobalVariablesFunction();";

/*-
content += "function initGlobalVariables() {";		
content += "var d3 = window['d3'];";
content += "console.log('initGlobalVariables function is called! d3 is ' + d3);";

// main div
content += "mainDiv = d3.select(\"#main\");";
content += "mainDivElement = mainDiv.node();";
content += "svg = d3.select(\"#main\").select(\"svg\");";
content += "svgElement = d3.select(\"#main\").select(\"svg\").node();";
// layers
content += "depthLayerElement = svg.select(\"#layer_0\").node();";
content += "bgLayerElement = svg.select(\"#layer_1\").node();";
// perspective
content += "viewShiftX = getViewShiftX();";
content += "viewShiftY = getViewShiftY();";
// degree_y is rotate along Y axis
// degree_x is rotate along X axis
content += "rotateY = -degree_y / divider * viewShiftX;";
content += "rotateX = degree_x / divider * viewShiftY;";

content += "var screen_width = window.screen.width;";
content += "var screen_height = window.screen.height;";
content += "var curr_width = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;";
content += "var curr_height = window.innerHeight || document.documentElement.clientHeight || document.body.clientHeight;";
content += "if (screen_width == curr_width && screen_height == curr_height) {";
content += "isFullScreen = true;";
content += "} else {";
content += "isFullScreen = false;";
content += "}";

content += "top_left_svg = d3.select(\"#top_left_tools\").select(\"svg\");";
content += "if (top_left_svg) {";
content += "topLeftToolD3 = top_left_svg.select(\"#TopLeftTool\");";
content += "topLeftToolElement = topLeftToolD3.node();";
content += "topLeftTool_text1Element = top_left_svg.select(\"#TopLeftTool_text1\").node();";
content += "topLeftTool_text2Element = top_left_svg.select(\"#TopLeftTool_text2\").node();";
content += "topLeftTool_text3Element = top_left_svg.select(\"#TopLeftTool_text3\").node();";
content += "}";

content += "top_right_svg = d3.select(\"#top_right_tools\").select(\"svg\");";
content += "if (top_right_svg) {";
content += "screenDepthPartD3 = top_right_svg.select(\"#screenDepth_part\");";
content += "screenGridPartD3 = top_right_svg.select(\"#screenGrid_part\");";
content += "screenPerspectivePartD3 = top_right_svg.select(\"#screenPerspective_part\");";
content += "screenAnchorPart1D3 = top_right_svg.select(\"#screenAnchor_part1\");";
content += "screenAnchorPart2D3 = top_right_svg.select(\"#screenAnchor_part2\");";
content += "toggleFullScreenButtonD3 = top_right_svg.select(\"#toggleFullScreen1\");";
content += "if (isFullScreen) {";
content += "toggleFullScreenButtonD3.attr(\"d\", fullScreenPath2);";
content += "} else {";
content += "toggleFullScreenButtonD3.attr(\"d\", fullScreenPath1);";
content += "}";
content += "}";

content += "isScreenDepthEnabled = getScreenDepth();";
content += "isScreenGridEnabled = getScreenGrid();";
content += "isScreenPerspectiveEnabled = getScreenPerspective();";
content += "isScreenAnchored = getScreenAnchored();";

content += "refreshScreenDepth();";
content += "refreshScreenGrid();";
content += "refreshScreenPerspective();";
content += "refreshScreenAnchor();";
content += "}";
*/
