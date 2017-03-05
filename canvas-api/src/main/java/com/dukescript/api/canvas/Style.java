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

        public static Color ALICEBLUE = new Color("#F0F8FF");
        public static Color ANTIQUEWHITE = new Color("#FAEBD7");
        public static Color AQUA = new Color("#00FFFF");
        public static Color AQUAMARINE = new Color("#7FFFD4");
        public static Color AZURE = new Color("#F0FFFF");
        public static Color BEIGE = new Color("#F5F5DC");
        public static Color BISQUE = new Color("#FFE4C4");
        public static Color BLACK = new Color("#000000");
        public static Color BLANCHEDALMOND = new Color("#FFEBCD");
        public static Color BLUE = new Color("#0000FF");
        public static Color BLUEVIOLET = new Color("#8A2BE2");
        public static Color BROWN = new Color("#A52A2A");
        public static Color BURLYWOOD = new Color("#DEB887");
        public static Color CADETBLUE = new Color("#5F9EA0");
        public static Color CHARTREUSE = new Color("#7FFF00");
        public static Color CHOCOLATE = new Color("#D2691E");
        public static Color CORAL = new Color("#FF7F50");
        public static Color CORNFLOWERBLUE = new Color("#6495ED");
        public static Color CORNSILK = new Color("#FFF8DC");
        public static Color CRIMSON = new Color("#DC143C");
        public static Color CYAN = new Color("#00FFFF");
        public static Color DARKBLUE = new Color("#00008B");
        public static Color DARKCYAN = new Color("#008B8B");
        public static Color DARKGOLDENROD = new Color("#B8860B");
        public static Color DARKGRAY = new Color("#A9A9A9");
        public static Color DARKGREY = new Color("#A9A9A9");
        public static Color DARKGREEN = new Color("#006400");
        public static Color DARKKHAKI = new Color("#BDB76B");
        public static Color DARKMAGENTA = new Color("#8B008B");
        public static Color DARKOLIVEGREEN = new Color("#556B2F");
        public static Color DARKORANGE = new Color("#FF8C00");
        public static Color DARKORCHID = new Color("#9932CC");
        public static Color DARKRED = new Color("#8B0000");
        public static Color DARKSALMON = new Color("#E9967A");
        public static Color DARKSEAGREEN = new Color("#8FBC8F");
        public static Color DARKSLATEBLUE = new Color("#483D8B");
        public static Color DARKSLATEGRAY = new Color("#2F4F4F");
        public static Color DARKSLATEGREY = new Color("#2F4F4F");
        public static Color DARKTURQUOISE = new Color("#00CED1");
        public static Color DARKVIOLET = new Color("#9400D3");
        public static Color DEEPPINK = new Color("#FF1493");
        public static Color DEEPSKYBLUE = new Color("#00BFFF");
        public static Color DIMGRAY = new Color("#696969");
        public static Color DIMGREY = new Color("#696969");
        public static Color DODGERBLUE = new Color("#1E90FF");
        public static Color FIREBRICK = new Color("#B22222");
        public static Color FLORALWHITE = new Color("#FFFAF0");
        public static Color FORESTGREEN = new Color("#228B22");
        public static Color FUCHSIA = new Color("#FF00FF");
        public static Color GAINSBORO = new Color("#DCDCDC");
        public static Color GHOSTWHITE = new Color("#F8F8FF");
        public static Color GOLD = new Color("#FFD700");
        public static Color GOLDENROD = new Color("#DAA520");
        public static Color GRAY = new Color("#808080");
        public static Color GREY = new Color("#808080");
        public static Color GREEN = new Color("#008000");
        public static Color GREENYELLOW = new Color("#ADFF2F");
        public static Color HONEYDEW = new Color("#F0FFF0");
        public static Color HOTPINK = new Color("#FF69B4");
        public static Color INDIANRED = new Color("#CD5C5C");
        public static Color INDIGO = new Color("#4B0082");
        public static Color IVORY = new Color("#FFFFF0");
        public static Color KHAKI = new Color("#F0E68C");
        public static Color LAVENDER = new Color("#E6E6FA");
        public static Color LAVENDERBLUSH = new Color("#FFF0F5");
        public static Color LAWNGREEN = new Color("#7CFC00");
        public static Color LEMONCHIFFON = new Color("#FFFACD");
        public static Color LIGHTBLUE = new Color("#ADD8E6");
        public static Color LIGHTCORAL = new Color("#F08080");
        public static Color LIGHTCYAN = new Color("#E0FFFF");
        public static Color LIGHTGOLDENRODYELLOW = new Color("#FAFAD2");
        public static Color LIGHTGRAY = new Color("#D3D3D3");
        public static Color LIGHTGREY = new Color("#D3D3D3");
        public static Color LIGHTGREEN = new Color("#90EE90");
        public static Color LIGHTPINK = new Color("#FFB6C1");
        public static Color LIGHTSALMON = new Color("#FFA07A");
        public static Color LIGHTSEAGREEN = new Color("#20B2AA");
        public static Color LIGHTSKYBLUE = new Color("#87CEFA");
        public static Color LIGHTSLATEGRAY = new Color("#778899");
        public static Color LIGHTSLATEGREY = new Color("#778899");
        public static Color LIGHTSTEELBLUE = new Color("#B0C4DE");
        public static Color LIGHTYELLOW = new Color("#FFFFE0");
        public static Color LIME = new Color("#00FF00");
        public static Color LIMEGREEN = new Color("#32CD32");
        public static Color LINEN = new Color("#FAF0E6");
        public static Color MAGENTA = new Color("#FF00FF");
        public static Color MAROON = new Color("#800000");
        public static Color MEDIUMAQUAMARINE = new Color("#66CDAA");
        public static Color MEDIUMBLUE = new Color("#0000CD");
        public static Color MEDIUMORCHID = new Color("#BA55D3");
        public static Color MEDIUMPURPLE = new Color("#9370DB");
        public static Color MEDIUMSEAGREEN = new Color("#3CB371");
        public static Color MEDIUMSLATEBLUE = new Color("#7B68EE");
        public static Color MEDIUMSPRINGGREEN = new Color("#00FA9A");
        public static Color MEDIUMTURQUOISE = new Color("#48D1CC");
        public static Color MEDIUMVIOLETRED = new Color("#C71585");
        public static Color MIDNIGHTBLUE = new Color("#191970");
        public static Color MINTCREAM = new Color("#F5FFFA");
        public static Color MISTYROSE = new Color("#FFE4E1");
        public static Color MOCCASIN = new Color("#FFE4B5");
        public static Color NAVAJOWHITE = new Color("#FFDEAD");
        public static Color NAVY = new Color("#000080");
        public static Color OLDLACE = new Color("#FDF5E6");
        public static Color OLIVE = new Color("#808000");
        public static Color OLIVEDRAB = new Color("#6B8E23");
        public static Color ORANGE = new Color("#FFA500");
        public static Color ORANGERED = new Color("#FF4500");
        public static Color ORCHID = new Color("#DA70D6");
        public static Color PALEGOLDENROD = new Color("#EEE8AA");
        public static Color PALEGREEN = new Color("#98FB98");
        public static Color PALETURQUOISE = new Color("#AFEEEE");
        public static Color PALEVIOLETRED = new Color("#DB7093");
        public static Color PAPAYAWHIP = new Color("#FFEFD5");
        public static Color PEACHPUFF = new Color("#FFDAB9");
        public static Color PERU = new Color("#CD853F");
        public static Color PINK = new Color("#FFC0CB");
        public static Color PLUM = new Color("#DDA0DD");
        public static Color POWDERBLUE = new Color("#B0E0E6");
        public static Color PURPLE = new Color("#800080");
        public static Color REBECCAPURPLE = new Color("#663399");
        public static Color RED = new Color("#FF0000");
        public static Color ROSYBROWN = new Color("#BC8F8F");
        public static Color ROYALBLUE = new Color("#4169E1");
        public static Color SADDLEBROWN = new Color("#8B4513");
        public static Color SALMON = new Color("#FA8072");
        public static Color SANDYBROWN = new Color("#F4A460");
        public static Color SEAGREEN = new Color("#2E8B57");
        public static Color SEASHELL = new Color("#FFF5EE");
        public static Color SIENNA = new Color("#A0522D");
        public static Color SILVER = new Color("#C0C0C0");
        public static Color SKYBLUE = new Color("#87CEEB");
        public static Color SLATEBLUE = new Color("#6A5ACD");
        public static Color SLATEGRAY = new Color("#708090");
        public static Color SLATEGREY = new Color("#708090");
        public static Color SNOW = new Color("#FFFAFA");
        public static Color SPRINGGREEN = new Color("#00FF7F");
        public static Color STEELBLUE = new Color("#4682B4");
        public static Color TAN = new Color("#D2B48C");
        public static Color TEAL = new Color("#008080");
        public static Color THISTLE = new Color("#D8BFD8");
        public static Color TOMATO = new Color("#FF6347");
        public static Color TURQUOISE = new Color("#40E0D0");
        public static Color VIOLET = new Color("#EE82EE");
        public static Color WHEAT = new Color("#F5DEB3");
        public static Color WHITE = new Color("#FFFFFF");
        public static Color WHITESMOKE = new Color("#F5F5F5");
        public static Color YELLOW = new Color("#FFFF00");
        public static Color YELLOWGREEN = new Color("#9ACD32");
        
        public static Color TRANSPARENT = new Color("rgba(0,0,0,0.0)");
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
         * @param stops the stops of this gradient
         */
        private Gradient(double x0, double y0, double x1, double y1, List<Stop> stops) {
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

        private LinearGradient(double x0, double y0, double x1, double y1, List<Stop> stops) {
            super(x0, y0, x1, y1, stops);
        }

        /**
         *
         * @param x0 the x coordinate of the start point for this gradient
         * @param y0 the y coordinate of the start point for this gradient
         * @param x1 the x coordinate of the end point for this gradient
         * @param y1 the y coordinate of the end point for this gradient
         * @param stops the stops of this gradient
         * @return linearGradient the gradient
         */
        public static LinearGradient create(double x0, double y0, double x1, double y1, List<Stop> stops) {
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
            super(x0, y0, x1, y1, stops);
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
         * @param stops the stops of this gradient
         * @return radialGradient the gradient
         */
        public static RadialGradient create(double x0, double y0, double r0, double x1, double y1, double r1, List<Stop> stops) {
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
