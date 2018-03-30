
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

import com.dukescript.api.canvas.ImageData;
import net.java.html.js.JavaScriptBody;



/**
 *
 * @author Anton Epple toni.epple@dukescript.com
 */
class ImageDataWrapper implements ImageData <Object>{

    private double width = -1;
    private double height = -1;
    private final Object imageData;
    private Data data;

    public ImageDataWrapper(Object imageData) {
        this.imageData = imageData;
    }

    private Data getData() {
        if (data == null) {
            data = new Data(getDataImpl(imageData));
        }
        getWidth();
        getHeight();
        return data;
    }

    @JavaScriptBody(args = {"imageData"}, body = "return imageData.data")
    public native Object getDataImpl(Object imageData);

    @Override
    public double getWidth() {
        if (width == -1) {
            width = getWidthImpl(imageData);
        }
        return width;
    }

    @JavaScriptBody(args = {"imageData"}, body = "return imageData.width;")
    private static native int getWidthImpl(Object imageData);

    @Override
    public double getHeight() {
        if (height == -1) {
            height = getHeightImpl(imageData);
        }
        return height;
    }

    @JavaScriptBody(args = {"imageData"}, body = "return imageData.height;")
    private static native int getHeightImpl(Object imageData);

    Object object() {
        return imageData;
    }

    @Override
    public int getR(int x, int y) {
        int idx = (x + ( y * (int)width)) * 4;
        return getData().get(idx);
    }

    @Override
    public int getG(int x, int y) {
        int idx = (x + (y * (int)width)) * 4;
        return getData().get(idx + 1);
    }

    @Override
    public int getB(int x, int y) {
        int idx = (x +( y * (int)width)) * 4;
        return getData().get(idx + 2);
    }

    @Override
    public int getA(int x, int y) {
        int idx = (x + (y * (int)width)) * 4;
        return getData().get(idx + 3);
    }

    @Override
    public void setR(int x, int y, int value) {
        int idx = (x + ( y * (int)width)) * 4;
        getData().set(idx, value);
    }

    @Override
    public void setG(int x, int y, int value) {
        int idx = (x + (y * (int)width)) * 4;
        getData().set(idx + 1, value);
    }

    @Override
    public void setB(int x, int y, int value) {
        int idx = (x + (y * (int)width)) * 4;
        getData().set(idx + 2, value);
    }

    @Override
    public void setA(int x, int y, int value) {
        int idx = (x + (y * (int)width)) * 4;
        getData().set(idx + 3, value);
    }

    @Override
    public Object getImage() {
        return imageData;
    }

    private static class Data {

        Object data;

        public Data(Object data) {
            this.data = data;
        }

        public int get(int index) {
            return getImpl(data, index);
        }

        public void set(int index, int value) {
            setImpl(data, index, value);
        }

        @JavaScriptBody(args = {"data", "index", "value"}, body = "data[index]=value;")
        private static native void setImpl(Object data, int index, int value);

        @JavaScriptBody(args = {"data", "index"}, body = "return data[index];")
        private static native int getImpl(Object data, int index);
    }
}
