
package com.dukescript.api.canvas.events;

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


/**
 *
 * @author antonepple
 */
public class MouseEvent<T> extends Event<T>{
      /**
     * Common supertype for all mouse event types.
     */
    public static final Event.Type<MouseEvent> ANY =
            new Event.Type<MouseEvent>("MOUSE", Event.ANY);

    public static final Event.Type<MouseEvent> MOUSE_PRESSED =
            new Event.Type<MouseEvent>("MOUSE_PRESSED",ANY);

    public static final Event.Type<MouseEvent> MOUSE_RELEASED =
            new Event.Type<MouseEvent>( "MOUSE_RELEASED",ANY);

    public static final Event.Type<MouseEvent> MOUSE_CLICKED =
            new Event.Type<MouseEvent>("MOUSE_CLICKED",ANY);

    public static final Event.Type<MouseEvent> MOUSE_ENTERED_TARGET =
            new Event.Type<MouseEvent>("MOUSE_ENTERED_TARGET",ANY);

    public static final Event.Type<MouseEvent> MOUSE_ENTERED =
            new Event.Type<MouseEvent>( "MOUSE_ENTERED",ANY);

    public static final Event.Type<MouseEvent> MOUSE_EXITED_TARGET =
            new Event.Type<MouseEvent>( "MOUSE_EXITED_TARGET",ANY);

    public static final Event.Type<MouseEvent> MOUSE_EXITED =
            new Event.Type<MouseEvent>( "MOUSE_EXITED",ANY);

    public static final Event.Type<MouseEvent> MOUSE_MOVED =
            new Event.Type<MouseEvent>("MOUSE_MOVED",ANY);

    public static final Event.Type<MouseEvent> MOUSE_DRAGGED =
            new Event.Type<MouseEvent>("MOUSE_DRAGGED",ANY);

    private double x;
    private double y;
    
    public MouseEvent(T source, Event.Type<? extends MouseEvent> type , double x, double y) {
        super(source, type );
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
    
    
}
