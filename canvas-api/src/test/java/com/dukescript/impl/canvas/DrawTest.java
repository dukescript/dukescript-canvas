package com.dukescript.impl.canvas;

/*
 * #%L
 * Canvas API - a library from the "DukeScript" project.
 * %%
 * Copyright (C) 2015 - 2016 Dukehoff GmbH
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

import com.dukescript.api.canvas.GraphicsContext2D;
import net.java.html.junit.BrowserRunner;
import net.java.html.junit.HTMLContent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@HTMLContent("\n"
    + "  <canvas id='test'></canvas>\n"
)
@RunWith(BrowserRunner.class)
public class DrawTest {

    @Before
    // BEGIN: com.dukescript.impl.canvas.DrawTest#initializeCanvasContext
    public void initializeCanvasContext() {
        context = GraphicsContext2D.getOrCreate("test");
    }
    private GraphicsContext2D context;
    // END: com.dukescript.impl.canvas.DrawTest#initializeCanvasContext

    @Test
    public void drawSquare() {
        // BEGIN: com.dukescript.impl.canvas.DrawTest#drawSquare
        context.setStrokeStyle(context.getWebColor("blue"));
        context.beginPath();
        context.moveTo(10, 10);
        context.lineTo(10, 20);
        context.lineTo(20, 20);
        context.lineTo(20, 10);
        context.closePath();
        context.stroke();
        // END: com.dukescript.impl.canvas.DrawTest#drawSquare
    }
}
