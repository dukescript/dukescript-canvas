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
import com.dukescript.api.canvas.Dimension;
import com.dukescript.api.canvas.Image;
import com.dukescript.api.canvas.ImageData;
import com.dukescript.api.canvas.Style;
import com.dukescript.api.canvas.Style.Color;
import com.dukescript.api.canvas.Style.LinearGradient;
import com.dukescript.api.canvas.Style.Pattern;
import com.dukescript.api.canvas.Style.RadialGradient;
import com.dukescript.spi.canvas.GraphicsEnvironment;
import java.util.List;

import net.java.html.js.JavaScriptBody;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Anton Epple toni.epple@dukescript.com
 */
@ServiceProvider(service = GraphicsEnvironment.class)
public class HTML5GraphicsEnvironment implements GraphicsEnvironment<Object> {

    @Override
    public Object getOrCreateCanvas(String id) {
        Object canvas = getImpl(id);
        if (canvas == null) {
            canvas = createImpl(id);
        }
        adjustForRetina(canvas);
        return canvas;
    }

    @JavaScriptBody(args = {"canvas"}, body = "var ctx = canvas.getContext('2d');\n"
            + "if (window.devicePixelRatio) {\n"
            + "var devicePixelRatio = window.devicePixelRatio;\n"
            + "var canvasWidth = canvas.getAttribute('width');\n"
            + "var canvasHeight = canvas.getAttribute('height');\n"
            + "canvas.style.width = canvasWidth+'px';\n"
            + "canvas.style.height = canvasHeight+'px';\n"
            + "canvas.setAttribute( 'width', canvasWidth*devicePixelRatio);\n"
            + "canvas.setAttribute( 'height', canvasHeight*devicePixelRatio);\n"
            + "ctx.transform(devicePixelRatio,0,0,devicePixelRatio,0,0);\n"
            + "}")
    private static native void adjustForRetina(Object canvas);

    @JavaScriptBody(args = {"canvas"}, body = "var ctx = canvas.getContext('2d');\n"
            + "if (window.devicePixelRatio) {\n"
            + "var devicePixelRatio = window.devicePixelRatio;\n"
            + "var canvasWidth = canvas.getAttribute('width');\n"
            + "canvas.style.width = canvasWidth+'px';\n"
            + "canvas.setAttribute( 'width', canvasWidth*devicePixelRatio);\n"
            + "ctx.transform(devicePixelRatio,0,0,devicePixelRatio,0,0);\n"
            + "}")
    private static native void adjustWidthForRetina(Object canvas);

    @JavaScriptBody(args = {"canvas"}, body = "var ctx = canvas.getContext('2d');\n"
            + "if (window.devicePixelRatio) {\n"
            + "var devicePixelRatio = window.devicePixelRatio;\n"
            + "var canvasHeight = canvas.getAttribute('height');\n"
            + "canvas.style.height = canvasHeight+'px';\n"
            + "canvas.setAttribute( 'height', canvasHeight*devicePixelRatio);\n"
            + "ctx.transform(devicePixelRatio,0,0,devicePixelRatio,0,0);\n"
            + "}")
    private static native void adjustHeightForRetina(Object canvas);

    @JavaScriptBody(args = {"id"}, body = "var canvas = document.getElementById(id);return canvas;")
    private static native Object getImpl(String id);

    @JavaScriptBody(args = {"id"}, body = "var canvas = document.createElement('canvas');"
            + "var body = document.getElementsByTagName('body')[0];body.appendChild(canvas); return canvas;")
    private static native Object createImpl(String id);

    @JavaScriptBody(wait4js = false, args = {"canvas", "centerX", "centerY", "radius", "startAngle", "endAngle", "acw"},
            body = "var context = canvas.getContext('2d');"
            + "context.arc(centerX, centerY, radius, startAngle, endAngle, acw);"
    )
    @Override
    public native void arc(Object canvas,
            double centerX,
            double centerY,
            double radius,
            double startAngle,
            double endAngle,
            boolean acw);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x1", "y1", "x2", "y2", "r"},
            body = "canvas.getContext('2d').arcTo(x1,y1,x2,y2,r);")
    @Override
    public native void arcTo(Object canvas,
            double x1,
            double y1,
            double x2,
            double y2,
            double r);

    @JavaScriptBody(args = {"canvas", "x", "y"},
            body = "return canvas.getContext('2d').isPointInPath(x,y);")
    @Override
    public native boolean isPointInPath(Object canvas, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas"}, body = "canvas.getContext('2d').fill();")
    @Override
    public native void fill(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas"}, body = "canvas.getContext('2d').stroke();")
    @Override
    public native void stroke(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas"}, body = "canvas.getContext('2d').beginPath();")
    @Override
    public native void beginPath(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas"}, body = "canvas.getContext('2d').closePath();")
    @Override
    public native void closePath(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas"}, body = "canvas.getContext('2d').clip();")
    @Override
    public native void clip(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y"}, body = "canvas.getContext('2d').moveTo(x,y);")
    @Override
    public native void moveTo(Object canvas, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y"}, body = "canvas.getContext('2d').lineTo(x,y);")
    @Override
    public native void lineTo(Object canvas, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas", "cpx", "cpy", "x", "y"}, body = "canvas.getContext('2d').quadraticCurveTo(cpx,cpy,x,y);")
    @Override
    public native void quadraticCurveTo(Object canvas, double cpx, double cpy, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas", "cp1x", "cp1y", "cp2x", "cp2y", "x", "y"},
            body = "canvas.getContext('2d').bezierCurveTo(cp1x,cp1y,cp2x,cp2y,x,y);")
    @Override
    public native void bezierCurveTo(Object canvas, double cp1x, double cp1y, double cp2x, double cp2y, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y", "width", "height"}, body = "canvas.getContext('2d').fillRect(x,y,width,height);")
    @Override
    public native void fillRect(Object canvas, double x, double y, double width, double height);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y", "width", "height"}, body = "canvas.getContext('2d').strokeRect(x,y,width,height);")
    @Override
    public native void strokeRect(Object canvas, double x, double y, double width, double height);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y", "width", "height"}, body = "canvas.getContext('2d').clearRect(x,y,width,height);")
    @Override
    public native void clearRect(Object canvas, double x, double y, double width, double height);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y", "width", "height"}, body = "canvas.getContext('2d').rect(x,y,width,height);")
    @Override
    public native void rect(Object canvas, double x, double y, double width, double height);

    @JavaScriptBody(wait4js = false, args = {"canvas"}, body = "canvas.getContext('2d').save();")
    @Override
    public native void save(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas"}, body = "canvas.getContext('2d').restore();")
    @Override
    public native void restore(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "angle"}, body = "canvas.getContext('2d').rotate(angle);")
    @Override
    public native void rotate(Object canvas, double angle);

    @JavaScriptBody(wait4js = false, args = {"canvas", "a", "b", "c", "d", "e", "f"}, body = "canvas.getContext('2d').transform(a,b,c,d,e,f);")
    @Override
    public native void transform(Object canvas, double a, double b, double c, double d, double e, double f);

    @JavaScriptBody(wait4js = false, args = {"canvas", "a", "b", "c", "d", "e", "f"}, body = "canvas.getContext('2d').setTransform(a,b,c,d,e,f);")
    @Override
    public native void setTransform(Object canvas, double a, double b, double c, double d, double e, double f);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y"}, body = "canvas.getContext('2d').translate(x,y);")
    @Override
    public native void translate(Object canvas, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas", "x", "y"}, body = "canvas.getContext('2d').scale(x,y);")
    @Override
    public native void scale(Object canvas, double x, double y);

    @Override
    public Object drawImage(Object canvas, Image image, double x, double y, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        drawImageImpl(canvas, nativeImage, x, y);
        return nativeImage;
    }

    @Override
    public Object drawImage(Object canvas, Image image, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        drawImageImpl(canvas, nativeImage, x, y, width, height);
        return nativeImage;
    }

    @Override
    public Object drawImage(Object canvas, Image image, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        drawImageImpl(canvas, nativeImage, sx, sy, sWidth, sHeight, x, y, width, height);
        return nativeImage;
    }

    @JavaScriptBody(wait4js = false, args = {"ctx", "img", "x", "y", "width", "height"}, body
            = "img.onload=function(){\n"
            + "  ctx.getContext('2d').drawImage(img,x,y,width,height);\n"
            + "};\n"
            + "ctx.getContext('2d').drawImage(img,x,y,width,height);\n"
            + "return img;"
    )
    private native static void drawImageImpl(Object ctx, Object img, double x, double y, double width, double height);

    @JavaScriptBody(wait4js = false, args = {
        "ctx", "img", "sx", "sy", "swidth", "sheight", "x", "y", "width", "height"
    }, body
            = "img.onload=function(){\n"
            + "  ctx.getContext('2d').drawImage(img,sx,sy,swidth,sheight,x,y,width,height);"
            + "};\n"
            + "ctx.getContext('2d').drawImage(img,sx,sy,swidth,sheight,x,y,width,height);\n"
            + "return img;"
    )
    private native static void drawImageImpl(Object ctx, Object img, double sx, double sy, double sWidth, double sHeight, double x, double y, double width, double height);

    @JavaScriptBody(wait4js = false, args = {"ctx", "img", "x", "y"}, body
            = "img.onload=function(){\n"
            + "  ctx.getContext('2d').drawImage(img,x,y);\n"
            + "};\n"
            + "ctx.getContext('2d').drawImage(img,x,y);\n"
            + "return img;"
    )
    private native static void drawImageImpl(Object ctx, Object img, double x, double y);

    @Override
    public Object setFillStyle(Object canvas, Style style, Object nativeStyle) {
        if (nativeStyle == null) {
            nativeStyle = this.createNativeStyle(canvas, style);
        }
        setFillStyleImpl(canvas, nativeStyle);
        return nativeStyle;
    }

    protected Object createNativeStyle(Object canvas, Style style) {

        if (style instanceof Pattern) {
            return createPatternWrapper(canvas, ((Pattern) style).getImageResource(), ((Pattern) style).getRepeat());
        } else if (style instanceof Color) {
            return ((Color) style)
                    .getAsString();
        } else {
            return this.createGradient(canvas, style);
        }
    }

    @JavaScriptBody(wait4js = false, args = {"gradient", "position", "color"}, body
            = "gradient.addColorStop(position,color)")
    private static native void addColorStopImpl(Object gradient, double position, String color);

    @JavaScriptBody(wait4js = false, args = {"canvas", "obj"}, body = "canvas.getContext('2d').fillStyle=obj;")
    private native void setFillStyleImpl(Object canvas, Object obj);

    @Override
    public Object setStrokeStyle(Object canvas, Style style, Object nativeStyle) {
        if (nativeStyle == null) {
            nativeStyle = this.createNativeStyle(canvas, style);
        }
        setStrokeStyleImpl(canvas, nativeStyle);
        return nativeStyle;
    }

    @JavaScriptBody(wait4js = false, args = {"canvas", "obj"}, body = "canvas.getContext('2d').strokeStyle=obj;")
    private native void setStrokeStyleImpl(Object canvas, Object obj);

    /*
     @JavaScriptBody(wait4js=false, args = {"color"}, body = "canvas.getContext('2d').shadowColor=color.valueOf();")
     @Override
     public native void setShadowColor(String color);

     @JavaScriptBody(wait4js=false, args = {"blur"}, body = "canvas.getContext('2d').shadowBlur=blur;")
     @Override
     public native void setShadowBlur(double blur);

     @JavaScriptBody(wait4js=false, args = {"x"}, body = "canvas.getContext('2d').shadowOffsetX=x;")
     @Override
     public native void setShadowOffsetX(double x);

     @JavaScriptBody(wait4js=false, args = {"y"}, body = "canvas.getContext('2d').shadowOffsetY=y;")
     @Override
     public native void setShadowOffsetY(double y);

     @JavaScriptBody(wait4js=false, args={"canvas"}, body = "return canvas.getContext('2d').strokeStyle;")
     public native String getStrokeStyle();

     @JavaScriptBody(wait4js=false, args={"canvas"}, body = "return canvas.getContext('2d').shadowColor;")
     @Override
     public native String getShadowColor();

     @JavaScriptBody(wait4js=false, args={"canvas"}, body = "return canvas.getContext('2d').shadowBlur;")
     @Override
     public native double getShadowBlur();

     @JavaScriptBody(wait4js=false, args={"canvas"}, body = "return canvas.getContext('2d').shadowOffsetX;")
     @Override
     public native double getShadowOffsetX();

     @JavaScriptBody(wait4js=false, args={"canvas"}, body = "return canvas.getContext('2d').shadowOffsetY;")
     @Override
     public native double getShadowOffsetY();
     */
    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').lineCap;")
    @Override
    public native String getLineCap(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "style"}, body = "canvas.getContext('2d').lineCap=style.valueOf();")
    @Override
    public native void setLineCap(Object canvas, String style);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').lineJoin;")
    @Override
    public native String getLineJoin(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "style"}, body = "canvas.getContext('2d').lineJoin=style.valueOf();")
    @Override
    public native void setLineJoin(Object canvas, String style);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').lineWidth;")
    @Override
    public native double getLineWidth(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "width"}, body = "canvas.getContext('2d').lineWidth=width;")
    @Override
    public native void setLineWidth(Object canvas, double width);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').miterLimit;")
    @Override
    public native double getMiterLimit(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "limit"}, body = "canvas.getContext('2d').miterLimit=limit;")
    @Override
    public native void setMiterLimit(Object canvas, double limit);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').font;")
    @Override
    public native String getFont(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "font"}, body = "canvas.getContext('2d').font=font.valueOf();")
    @Override
    public native void setFont(Object canvas, String font);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').textAlign;")
    @Override
    public native String getTextAlign(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "textalign"}, body = "canvas.getContext('2d').textAlign=textalign.valueOf();")
    @Override
    public native void setTextAlign(Object canvas, String textAlign);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').textBaseline;")
    @Override
    public native String getTextBaseline(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "textbaseline"}, body = "canvas.getContext('2d').textBaseline=textbaseline.valueOf();")
    @Override
    public native void setTextBaseline(Object canvas, String textbaseline);

    @JavaScriptBody(wait4js = false, args = {"canvas", "text", "x", "y"}, body = "canvas.getContext('2d').fillText(text,x,y);")
    //    @JavaScriptBody(wait4js=false, args = {"text", "x", "y"}, body = "console.log(text);")
    @Override
    public native void fillText(Object canvas, String text, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas", "text", "x", "y", "maxwidth"}, body = "canvas.getContext('2d').fillText(text,x,y,maxwidth);")
    @Override
    public void fillText(Object canvas, String text, double x, double y, double maxWidth) {
    }

    @Override
    public Dimension measureText(Object canvas, String text) {
        measureTextImpl(canvas, text);
        return new Dimension(1, 1);
    }

    @JavaScriptBody(args = {"canvas", "text"},
            body = "return canvas.getContext('2d').measureText(text);")
    private native Object measureTextImpl(Object canvas, String text);

    @JavaScriptBody(wait4js = false, args = {"canvas", "text", "x", "y"}, body = "canvas.getContext('2d').strokeText(text,x,y);")
    @Override
    public native void strokeText(Object canvas, String text, double x, double y);

    @JavaScriptBody(wait4js = false, args = {"canvas", "text", "x", "y", "maxWidth"}, body = "canvas.getContext('2d').strokeText(text,x,y,maxWidth);")
    @Override
    public native void strokeText(Object canvas, String text, double x, double y, double maxWidth);

    @Override
    public ImageData createPixelMap(Object canvas, double x, double y) {
        return new ImageDataWrapper(createPixelMapImpl(canvas, x, y));
    }

    @JavaScriptBody(args = {"canvas", "x", "y"},
            body = "return canvas.getContext('2d').createImageData(x,y);")
    private native Object createPixelMapImpl(Object canvas, double x, double y);

    @Override
    public ImageData createPixelMap(Object canvas, ImageData imageData) {
        return new ImageDataWrapper(createPixelMapImpl(canvas, imageData.getWidth(), imageData.getHeight()));
    }

    @Override
    public ImageData createPixelMap(Object canvas, double x, double y, int[] all) {
        return new ImageDataWrapper(createPixelMapImpl(canvas, x, y, all));
    }

    @JavaScriptBody(args = {"canvas", "x", "y", "all"},
            body = "var pm = canvas.getContext('2d').createImageData(x,y);"
                    + "pm.data.set(all);"
                    + "return pm;")
    private native Object createPixelMapImpl(Object canvas, double x, double y, int[] all);

    @Override
    public ImageData getPixelMap(Object canvas, double x, double y, double width, double height) {
        return new ImageDataWrapper(getPixelMapImpl(canvas, x, y, width, height));
    }

    @JavaScriptBody(args = {"canvas", "x", "y", "width", "height"},
            body = "return canvas.getContext('2d').getImageData(x,y,width,height);")
    private native Object getPixelMapImpl(Object canvas, double x, double y, double width, double height);

    @Override
    public void putPixelMap(Object canvas, ImageData imageData, double x, double y) {
        putPixelMapImpl(canvas, ((ImageDataWrapper) imageData).object(), x, y);
    }

    @JavaScriptBody(wait4js = false, args = {"canvas", "imageData", "x", "y"},
            body = "canvas.getContext('2d').putImageData(imageData,x,y);")
    private native void putPixelMapImpl(Object canvas, Object imageData, double x, double y);

//    @Override
//    public void putPixelMap(Object canvas, ImageData imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight) {
//        putPixelMapImpl(canvas, ((ImageDataWrapper) imageData).object(), x, y, dirtyx, dirtyy, dirtywidth, dirtyheight);
//    }
    @JavaScriptBody(wait4js = false, args = {"canvas", "imageData", "x", "y", "dirtyx", "dirtyy", "dirtywidth", "dirtyheight"},
            body = "canvas.getContext('2d').putImageData(imageData,x,y, dirtyx, dirtyy, dirtywidth,dirtyheight);")
    private native void putPixelMapImpl(Object canvas, Object imageData, double x, double y, double dirtyx, double dirtyy, double dirtywidth, double dirtyheight);

    @JavaScriptBody(wait4js = false, args = {"canvas", "alpha"}, body = "canvas.getContext('2d').globalAlpha=alpha;")
    @Override
    public native void setGlobalAlpha(Object canvas, double alpha);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').globalAlpha;")
    @Override
    public native double getGlobalAlpha(Object canvas);

    @JavaScriptBody(wait4js = false, args = {"canvas", "operation"}, body = "canvas.getContext('2d').globalCompositeOperation=operation.valueOf();")
    @Override
    public native void setGlobalCompositeOperation(Object canvas, String operation);

    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d').globalCompositeOperation;")
    @Override
    public native String getGlobalCompositeOperation(Object canvas);

    LinearGradientWrapper createLinearGradientWrapper(Object canvas, double x0, double y0, double x1, double y1) {
        return new LinearGradientWrapper(createLinearGradientImpl(canvas, x0, y0, x1, y1));
    }

    @JavaScriptBody(args = {"canvas", "x0", "y0", "x1", "y1"}, body = "return canvas.getContext('2d').createLinearGradient(x0,y0,x1,y1);")
    private native Object createLinearGradientImpl(Object canvas, double x0, double y0, double x1, double y1);

    PatternWrapper createPatternWrapper(Object canvas, Image image, String repeat) {
        return new PatternWrapper(createPatternImpl(canvas, image, repeat));
    }

    @JavaScriptBody(args = {"canvas", "image", "repeat"}, body = "return canvas.getContext('2d').createPattern(image, repeat);")
    private static native Object createPatternImpl(Object canvas, Image image, String repeat);

    RadialGradientWrapper createRadialGradientWrapper(Object canvas, double x0, double y0, double r0, double x1, double y1, double r1) {
        return new RadialGradientWrapper(createRadialGradientImpl(canvas, x0, y0, r0, x1, y1, r1));
    }

    @JavaScriptBody(args = {"canvas", "x0", "y0", "r0", "x1", "y1", "r1"}, body = "return canvas.getContext('2d').createRadialGradient(x0,y0,r0,x1,y1,r1);")
    private static native Object createRadialGradientImpl(Object canvas, double x0, double y0, double r0, double x1, double y1, double r1);

//    @JavaScriptBody(wait4js=false, args = {"path"}, body = "var b = new Image(); b.src=path; return b;")
//    public native Image getImageForPathImpl(String path);
    @Override
    public int getHeight(Object canvas) {
        return getHeightImpl(canvas);
    }

    @Override
    public int getWidth(Object canvas) {
        return getWidthImpl(canvas);
    }

    @Override
    public void setHeight(Object canvas, int height) {
        setHeight_impl(canvas, height);
        adjustHeightForRetina(canvas);
    }

    @JavaScriptBody(wait4js = false, args = {"canvas", "height"}, body = "canvas.height=height;")
    public static native void setHeight_impl(Object canvas, int height);

    @Override
    public void setWidth(Object canvas, int width) {
        setWidth_impl(canvas, width);
        adjustWidthForRetina(canvas);
    }

    @JavaScriptBody(wait4js = false, args = {"canvas", "width"}, body = "canvas.width=width;")
    public static native void setWidth_impl(Object canvas, int width);

//    @JavaScriptBody(args = {"src"}, body = "var image = new Image();console.log('image complete '+image.complete);image.src = './'+ src; return image;")
    @JavaScriptBody(args = {"src"}, body = "var image = new Image();image.src = src; return image;")
    //    @JavaScriptBody(args = {"src"}, body = "return document.getElementById(src);")
    private static native Object createImage(String src);

    @Override
    public int getWidth(Object canvas, Image image, Object nativeImage) {

        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());

        }
        return getWidth(canvas, nativeImage);
    }

    @JavaScriptBody(args = {"canvas", "nativeImage"}, body = "return nativeImage.naturalWidth;")
    private static native int getWidth(Object canvas, Object nativeImage);

    @Override
    public int getHeight(Object canvas, Image image, Object nativeImage) {
        if (nativeImage == null) {
            nativeImage = createImage(image.getSrc());
        }
        return getHeight(canvas, nativeImage);
    }

    @JavaScriptBody(args = {"canvas", "nativeImage"}, body = "return nativeImage.naturalHeight;")
    private static native int getHeight(Object canvas, Object nativeImage);

    @Override
    public Object mergeImages(Object Canvas, Image a, Image b, Object cachedA, Object cachedB) {
        return mergeImages(cachedA, cachedB);
    }

    @JavaScriptBody(args = {"img1", "img2"}, body = "var canvas = document.createElement('img');\n"
            + "var context = canvas.getContext(\"2d\");\n"
            + "var width = img1.width;\n"
            + "var height = img1.height;\n"
            + "canvas.width = width;\n"
            + "canvas.height = height;\n"
            + "context.drawImage(img1, 0, 0);\n"
            + "context.drawImage(img2, 0, 0);\n"
            + "url = canvas.toDataURL();\n"
            + "var resultImage = document.createElement('img');\n"
            + "resultImage.src=url;\n"
            + "return resultImage;")
    public static native Object mergeImages(Object img1, Object img2);

//    @JavaScriptBody(args = {"canvas"}, body = "return canvas.getContext('2d');")
//    private static native Object getContext(Object canvas);
    @JavaScriptBody(args = {"canvas"}, body = "var height = canvas.height;\n"
            + "var devicePixelRatio = window.devicePixelRatio;\n"
            + "return (height/devicePixelRatio);"
    )
    private native int getHeightImpl(Object canvas);

    @JavaScriptBody(args = {"canvas"}, body = "var width = canvas.width;\n"
            + "var devicePixelRatio = window.devicePixelRatio;\n"
            + "return (width/devicePixelRatio);")
    private native int getWidthImpl(Object canvas);

    private Object createGradient(Object canvas, Style style) {
        if (style instanceof RadialGradient) {
            RadialGradientWrapper gradient = createRadialGradientWrapper(canvas,
                    ((RadialGradient) style).getX0(),
                    ((RadialGradient) style).getY0(),
                    ((RadialGradient) style).getR0(),
                    ((RadialGradient) style).getX1(),
                    ((RadialGradient) style).getY1(),
                    ((RadialGradient) style).getR1());
            List<Style.Stop> stops = ((RadialGradient) style).getStops();

            for (Style.Stop stop : stops) {
                addColorStopImpl(gradient.object(), stop.getPos(), stop.getStyle());
            }

            return gradient.object();
        } else if (style instanceof LinearGradient) {
            LinearGradientWrapper gradient = createLinearGradientWrapper(
                    canvas,
                    ((LinearGradient) style).getX0(),
                    ((LinearGradient) style).getY0(),
                    ((LinearGradient) style).getX1(),
                    ((LinearGradient) style).getY1());
            List<Style.Stop> stops = ((LinearGradient) style).getStops();
            for (Style.Stop stop : stops) {
                addColorStopImpl(gradient.object(), stop.getPos(), stop.getStyle());
            }
            return gradient.object();
        }
        return null;
    }

}
