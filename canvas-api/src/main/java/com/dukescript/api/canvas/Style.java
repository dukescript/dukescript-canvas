
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Style for Stroke and Fill of GraphicsContext2D. Styles are created using
 * GraphicsContexts factory methods. If the Implementation supports it, native
 * Styles will be cached for performance reasons. This happens the first time
 * the Style is actually used.
 *
 * @author antonepple
 */
public class Style {

    Object cached;
    private int cacheHash;

    Style() {
    }

    void cache(Object toCache) {
        cacheHash = hashCode();
        this.cached = toCache;
    }

    private boolean isCached() {
        return cacheHash == hashCode();
    }

    Object getCached() {
        return isCached() ? cached : null;
    }

    /**
     * A Fill Pattern using an Image Resource to create a fill style supporting
     * different repeat styles repeat, repeat-x, repeat-y, or no-repeat. Default
     * is repeat.
     */
    public static final class Pattern extends Style {

        private final Image imageResource;
        private final String repeat;

        /**
         *
         * @param imageResource the base image of thsi pattern
         * @param repeat the repeat pattern, possible values are repeat,
         * repeat-x, repeat-y, or no-repeat.
         */
        public Pattern(Image imageResource, String repeat) {
            this.imageResource = imageResource;
            this.repeat = repeat;
        }

        /**
         * Get the base image of this pattern
         *
         * @return the base image of this pattern
         */
        public Image getImageResource() {
            return imageResource;
        }

        /**
         * Get the repeat style for this pattern
         *
         * @return return the repeat style
         */
        public String getRepeat() {
            return repeat;
        }

    }

    /**
     * An RGB color
     */
    public static final class Color extends Style {

        private String web;

        /**
         * Creates an RGB color specified with an HTML or CSS attribute string.
         *
         * @param webColor Color defined as web color (e.g. #ff0000)
         */
        public Color(String webColor) {
            this.web = webColor;
        }

        /**
         *
         *
         * @return the Color value as a Web Color (e.g. #ff0000)
         */
        public String getAsString() {
            return web;
        }
    }

    private static class Gradient extends Style {

        protected final List<Stop> stops;
        protected final double x0, y0, x1, y1;

        /**
         *
         * @param x0 the x coordinate of the start point for this gradient
         * @param y0 the y coordinate of the start point for this gradient
         * @param x1 the x coordinate of the end point for this gradient
         * @param y1 the y coordinate of the end point for this gradient
         * @param stops  the stops of this gradient
         */
        private Gradient(double x0, double y0, double x1, double y1,List<Stop> stops) {
            this.x0 = x0;
            this.y0 = y0;
            this.x1 = x1;
            this.y1 = y1;
            this.stops = new ArrayList<Stop>(stops);
        }

        /**
         * Get the X coordinate of the Gradients start point
         *
         * @return x coordinate
         */
        public double getX0() {
            return x0;
        }

        /**
         * Set the X coordinate of the Gradients start point
         *
         * @param x0 x coordinate public void setX0(double x0) { this.x0 = x0; }
         */
        /**
         * Get the Y coordinate of the Gradients start point
         *
         * @return y coordinate
         */
        public double getY0() {
            return y0;
        }

        /**
         * Set the Y coordinate of the Gradients start point
         *
         * @param y0 y coordinate public void setY0(double y0) { this.y0 = y0; }
         */
        /**
         * Set the X coordinate of the Gradients end point
         *
         * @return x coordinate
         */
        public double getX1() {
            return x1;
        }

        /**
         * Set the X coordinate of the Gradients end point
         *
         * @param X coordinate public void setX1(double x1) { this.x1 = x1; }
         */
        /**
         * Get the Y coordinate of the Gradients end point
         *
         * @return y coordinate
         */
        public double getY1() {
            return y1;
        }


        /**
         * Get the stops of this gradient.
         *
         * @return the stops of this gradient
         */
        public List<Stop> getStops() {
            return new ArrayList<Stop>(stops);
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 29 * hash + Objects.hashCode(this.stops);
            hash = 29 * hash + (int) (Double.doubleToLongBits(this.x0) ^ (Double.doubleToLongBits(this.x0) >>> 32));
            hash = 29 * hash + (int) (Double.doubleToLongBits(this.y0) ^ (Double.doubleToLongBits(this.y0) >>> 32));
            hash = 29 * hash + (int) (Double.doubleToLongBits(this.x1) ^ (Double.doubleToLongBits(this.x1) >>> 32));
            hash = 29 * hash + (int) (Double.doubleToLongBits(this.y1) ^ (Double.doubleToLongBits(this.y1) >>> 32));
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
            final LinearGradient other = (LinearGradient) obj;
            if (!Objects.equals(this.stops, other.stops)) {
                return false;
            }
            if (Double.doubleToLongBits(this.x0) != Double.doubleToLongBits(other.x0)) {
                return false;
            }
            if (Double.doubleToLongBits(this.y0) != Double.doubleToLongBits(other.y0)) {
                return false;
            }
            if (Double.doubleToLongBits(this.x1) != Double.doubleToLongBits(other.x1)) {
                return false;
            }
            if (Double.doubleToLongBits(this.y1) != Double.doubleToLongBits(other.y1)) {
                return false;
            }
            return true;
        }
    }

    /**
     * A Linear Gradient. The Gradient has a direction defined by two
     * coordinates and stops defining the Color at a specific position.
     */
    public static class LinearGradient extends Gradient {

       private LinearGradient(double x0, double y0, double x1, double y1,List<Stop> stops) {
            super(x0, y0, x1, y1, stops);
        }

        /**
         *
         * @param x0 the x coordinate of the start point for this gradient
         * @param y0 the y coordinate of the start point for this gradient
         * @param x1 the x coordinate of the end point for this gradient
         * @param y1 the y coordinate of the end point for this gradient
         * @param stops  the stops of this gradient
         * @return linearGradient the gradient
         */
        public static LinearGradient create(double x0, double y0, double x1, double y1,List<Stop> stops) {
            return new LinearGradient(x0, y0, x1, y1, stops);
        }

    }

    /**
     * A Radial Gradient. Radial gradients are defined with two imaginary
     * circles, a starting circle and an ending circle. The gradient starts with
     * the start circle and moves towards the end circle.
     */
    public static final class RadialGradient extends Gradient {

        final private double r0, r1;


        private RadialGradient(double x0, double y0, double r0, double x1, double y1, double r1, List<Stop> stops) {
            super(x0, y0, x1, y1,stops);
            this.r0 = r0;
            this.r1 = r1;
        }

         /**
         * Create a new RadialGradient
         *
         * @param x0 x Coordinate of starting circle
         * @param y0 y Coordinate of starting circle
         * @param r0 radius of starting circle
         * @param x1 x coordinate of ending circle
         * @param y1 y coordinate of ending circle
         * @param r1 radius of ending circle
         * @param stops  the stops of this gradient
         * @return radialGradient the gradient
         */
        public static RadialGradient create(double x0, double y0, double r0, double x1, double y1, double r1, List<Stop> stops){
            return new RadialGradient(x0, y0, r0, x1, y1, r1, stops);
        }
        
        /**
         * get the radius of the start circle.
         *
         * @return the radius
         */
        public double getR0() {
            return r0;
        }

        /**
         * set the radius of the start circle.
         *
         * @param r0 the radius
         *
         * public void setR0(double r0) { this.r0 = r0; }
         */
        /**
         * get the radius of the end circle
         *
         * @return the radius
         */
        public double getR1() {
            return r1;
        }

        /*
         * set the radius of the end circle.
         *
         * @param r1 the radius.
         *
         * public void setR1(double r1) { this.r1 = r1; }
         */
        
        
        
        @Override
        public int hashCode() {
            int hash = super.hashCode();
            hash = 17 * hash + (int) (Double.doubleToLongBits(this.r0) ^ (Double.doubleToLongBits(this.r0) >>> 32));
            hash = 17 * hash + (int) (Double.doubleToLongBits(this.r1) ^ (Double.doubleToLongBits(this.r1) >>> 32));

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
            if (!super.equals(obj)) {
                return false;
            }
            final RadialGradient other = (RadialGradient) obj;
            if (Double.doubleToLongBits(this.r0) != Double.doubleToLongBits(other.r0)) {
                return false;
            }
            if (Double.doubleToLongBits(this.r1) != Double.doubleToLongBits(other.r1)) {
                return false;
            }
            if ((this.getCached() == null) != (other.getCached() == null)) {
                return false;
            }
            return true;
        }
    }

    public static class Stop {
        private final double pos;
        private final String style;

        public Stop(double pos, String style) {
            this.pos = pos;
            this.style = style;
        }

        public double getPos() {
            return pos;
        }

        public String getStyle() {
            return style;
        }
        
        
    }

}
