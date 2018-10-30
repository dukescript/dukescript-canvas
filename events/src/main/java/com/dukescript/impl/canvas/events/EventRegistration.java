package com.dukescript.impl.canvas.events;

/*-
 * #%L
 * events - a library from the "DukeScript" project.
 * %%
 * Copyright (C) 2016 - 2018 Dukehoff GmbH
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */
import net.java.html.js.JavaScriptBody;

/**
 *
 * @author antonepple
 */
public class EventRegistration {

    @JavaScriptBody(args = {"toolkit", "id"}, javacall = true, body
            = "var game = document.getElementById(id);\n"
            + "game.addEventListener('touchstart',function (event) {"
            + "   event.preventDefault();\n"
            + "   var touches = event.touches;\n"
            + "   var result = []; "
            + "   var rect = game.getBoundingClientRect();"
            + "   for (var i = 0, l = touches.length; i < l; ++i) {\n"
            + "       result [i*2] = rect.left + touches[i].clientX;\n"
            + "       result[(i*2)+1] = rect.top + touches[i].clientY;"
            + "   }\n"
            + "  if(result.length>0) {toolkit.@com.dukescript.impl.canvas.events.EventManager::touchStarted([Ljava/lang/Object;)(result);}\n"
            + "});"
            + "game.addEventListener('touchmove',function (event) {\n"
            + "   event.preventDefault();\n"
            + "   var touches = event.touches;\n"
            + "   var result = []; "
            + "   var rect = game.getBoundingClientRect();"
            + "   for (var i = 0, l = touches.length; i < l; ++i) {\n"
            + "       result [i*2] = rect.left + touches[i].clientX;\n"
            + "       result[(i*2)+1] = rect.top + touches[i].clientY;"
            + "   }\n"
            + "  if(result.length>0) {toolkit.@com.dukescript.impl.canvas.events.EventManager::touchMove([Ljava/lang/Object;)(result);}\n"
            + "});"
            + "game.addEventListener('touchend',function (event) {\n"
            + "   var touches = event.touches;\n"
            + "   var result = []; "
            + "   var rect = game.getBoundingClientRect();"
            + "   for (var i = 0, l = touches.length; i < l; ++i) {\n"
            + "       result [i*2] = rect.left + touches[i].clientX;\n"
            + "       result[(i*2)+1] = rect.top + touches[i].clientY;"
            + "   }\n"
            + "  if(result.length>0) {toolkit.@com.dukescript.impl.canvas.events.EventManager::touchEnd([Ljava/lang/Object;)(result);}\n"
            + "});"
            + "game.addEventListener('touchcancel',function (event) {\n"
            + "   event.preventDefault();\n"
            + "   var touches = event.touches;\n"
            + "   var result = []; "
            + "   var rect = game.getBoundingClientRect();"
            + "   for (var i = 0, l = touches.length; i < l; ++i) {\n"
            + "       result [i*2] = rect.left + touches[i].clientX;\n"
            + "       result[(i*2)+1] = rect.top + touches[i].clientY;"
            + "   }\n"
            + "  if(result.length>0) {toolkit.@com.dukescript.impl.canvas.events.EventManager::touchCancel([Ljava/lang/Object;)(result);}\n"
            + "});"
    )
    public static native void registerTouchEvents(EventManager toolkit, String id);

    @JavaScriptBody(args = {"toolkit", "id"}, javacall = true, body
            = "var flag = false;"
            + "var game = document.getElementById(id);\n"
            + "game.addEventListener('mousedown', function (event) {\n"
            //            + "   event.preventDefault();\n"
            + "    flag = true;"
//            + "    var rect = game.getBoundingClientRect();"
//            + "    var realX = event.clientX - rect.left;"
//            + "    var realY = event.clientY -rect.top;"
//            + "    toolkit.@com.dukescript.impl.canvas.events.EventManager::mouseDown(II)(realX, realY);"
            + "});\n"
            + "game.addEventListener('mousemove', function (event) {\n"
            //            + "   event.preventDefault();\n"
          
            + "    var rect = game.getBoundingClientRect();"
            + "    var realX = event.clientX - rect.left;"
            + "    var realY = event.clientY -rect.top;"
            + "    if (flag)toolkit.@com.dukescript.impl.canvas.events.EventManager::mousePressed(II)(realX, realY);"
            + "});\n"
            + "game.addEventListener('click',function (event) {\n"
            //            + "   event.preventDefault();\n"
            + "  var rect = game.getBoundingClientRect();"
            + "  var realX = event.clientX - rect.left;"
            + "  var realY = event.clientY -rect.top;"
            + "  toolkit.@com.dukescript.impl.canvas.events.EventManager::mouseClick(II)(realX, realY);"
            + "});\n"
            + "game.addEventListener('mouseup',function (event) {\n"
            //            + "   event.preventDefault();\n"
            + "  flag = false;"
            + "  var rect = game.getBoundingClientRect();"
            + "  var realX = event.clientX - rect.left;"
            + "  var realY = event.clientY -rect.top;"
            + "  toolkit.@com.dukescript.impl.canvas.events.EventManager::mouseUp(II)(realX, realY);"
            + "});\n"
    )
    public static native void registerMouseEvents(EventManager toolkit, String id);

    @JavaScriptBody(args = {"toolkit", "id"}, javacall = true, body
            = "var game = document.getElementById(id);\n"
            + "game.addEventListener('keypress', function (event) {\n"
            //            + "   event.preventDefault();\n"
            + "  toolkit.@com.dukescript.impl.canvas.events.EventManager::keyPress(I)(event.keyCode);"
            + "});\n"
            + "game.addEventListener('keyup', function (event) {\n"
            //            + "   event.preventDefault();\n"
            + "  toolkit.@com.dukescript.impl.canvas.events.EventManager::keyUp(I)(event.keyCode);"
            + "});\n"
            + "game.addEventListener('keydown', function (event) {\n"
            //            + "   event.preventDefault();\n"
            + "  toolkit.@com.dukescript.impl.canvas.events.EventManager::keyDown(I)(event.keyCode);"
            + "});\n"
    )
    public static native void registerKeyEvents(EventManager toolkit, String id);
}
