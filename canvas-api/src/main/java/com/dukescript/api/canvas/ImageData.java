
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

/**
 * ImageData is an updateable 2-Dimensional Map of Color values. Created (
 * createPixelMap / getSnapShot ) by GraphicsContext2D. you can modify the
 * individual pixels and render it using paintPixelMap on GraphicsContext2D
 *
 * @author antonepple
 * @param <Image> The native Image Type
 */
public interface ImageData <Image> {

    /**
     * get the height.
     *
     * @return the height
     */
    public double getHeight();

    /**
     * get the width
     *
     * @return the width
     */
    public double getWidth();

    /**
     * get the red value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the red value as an int (0 -255)
     */
    public int getR(int x, int y);

    /**
     * get the green value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the green value as an int (0 -255)
     */
    public int getG(int x, int y);

    /**
     * get the blue value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the blue value as an int (0 -255)
     */
    public int getB(int x, int y);

    /**
     * get the alpha (transparency) value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @return the alpha value as an int (0 - 255)
     */
    public int getA(int x, int y);

     /**
     * set the red value at a specified coordinate
     *
     * @param x x coordinate 
     * @param y y coordinate
     * @param value the red value as an int (0 - 255)
     */
    public void setR(int x, int y, int value);

     /**
     * set the green value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param value the green value as an int (0 - 255)
     */
    public void setG(int x, int y, int value);

     /**
     * set the blue value at a specified coordinate
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param value the blue value as an int (0 - 255)
     */
    public void setB(int x, int y, int value);

    /**
     * set the alpha (transparency) value at a specified coordinate
     *
     * @param x x coordinate
     * @param y  y coordinate
     * @param value the alpha value as an int (0 - 255)
     */
    public void setA(int x, int y, int value);
    
    /**
     * set all data at once
     * 
     * @param data 
     */
    public void setData(int[]data);
    
    public Image getImage();
}
