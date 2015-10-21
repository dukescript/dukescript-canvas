
package com.dukescript.spi.canvas;

/*
 * #%L
 * canvas-api - a library from the "DukeScript" project.
 * %%
 * Copyright (C) 2015 Dukehoff GmbH
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
import com.dukescript.impl.canvas.CnvsAccssr;

/**
 *
 * @author antonepple
 */
public class GraphicsUtils {

    private GraphicsUtils() {
    }

    /**
     * Use this to get A GraphicsContext2D to draw on. Pass in a  
     * {@link com.dukescript.spi.canvas.GraphicsEnvironment} and a String to identify
     * your Canvas. 
     * In the HTML5 implementation, the {@link com.dukescript.spi.canvas.GraphicsEnvironment} 
     * will look for a Canvas with that ID in your HTML (or add a new one to the page).
     * @param env the GraphicsEnvironment
     * @param id used by the {@link GraphicsEnvironment to identify the Canvas}
     * @return A GraphicsContext2D
     */
    public static GraphicsContext2D getOrCreate(GraphicsEnvironment env, String id) {       
        return CnvsAccssr.getDefault().create(env, env.getOrCreateCanvas(id));
    }

}
