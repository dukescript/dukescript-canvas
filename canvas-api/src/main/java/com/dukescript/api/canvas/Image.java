
package com.dukescript.api.canvas;

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

import java.util.Objects;

/**
 * Image represents an Image Resource defined by a path.
 *
 * @author antonepple
 */
public final class Image {

    private String src;
    private int cacheHash;
    private Object cached;

    /** Creates a new image referencing a specified URL.
     * @param src the (relative) URL to the image
     * @return an object representing the image
     */
    public static Image create(String src) {
        return new Image(src);
    }

    private Image(String src) {
        this.src = src;
    }

    void cache(Object toCache) {
        this.cached = toCache;
        cacheHash = hashCode();
    }

    private boolean isCached() {
        return cacheHash == hashCode();
    }

    Object getCached() {
        return isCached() ? cached : null;
    }

    public String getSrc() {
        return src;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.src) ^ (cached == null ? 1231 : 1237);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Image other = (Image) obj;
        if (!Objects.equals(this.src, other.src)) {
            return false;
        }
        if ((cached == null) != (other.getCached() == null)) {
            return false;
        }
        return true;
    }
}
