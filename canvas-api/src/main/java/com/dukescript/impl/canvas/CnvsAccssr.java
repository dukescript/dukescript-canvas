
package com.dukescript.impl.canvas;

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

import java.util.logging.Level;
import java.util.logging.Logger;
import com.dukescript.api.canvas.GraphicsContext2D;
import com.dukescript.spi.canvas.GraphicsEnvironment;

/**
 *
 * @author antonepple
 */
public abstract class CnvsAccssr {

    static CnvsAccssr DEFAULT;

    public CnvsAccssr() {
        if (DEFAULT != null) {
            throw new IllegalStateException("Already initialized");
        }
        DEFAULT = this;
    }

    public static CnvsAccssr getDefault() {
        if (DEFAULT == null) {
            try {
                Class.forName(GraphicsContext2D.class.getName(), true, GraphicsContext2D.class.getClassLoader());
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CnvsAccssr.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return DEFAULT;
    }

    public abstract <Canvas> GraphicsContext2D create(GraphicsEnvironment<Canvas> environment, Canvas c);
}
