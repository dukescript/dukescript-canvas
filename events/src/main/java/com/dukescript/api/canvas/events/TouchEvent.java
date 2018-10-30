
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

import java.util.Collections;
import java.util.List;

/**
 *
 * @author antonepple
 */
public final class TouchEvent<T> extends Event<T> {

//    private final int eventGroupId;
    private final List<Touch> touches;

    public static final Event.Type<TouchEvent> ANY
            = new Event.Type<TouchEvent>("TOUCH", Event.ANY);

    public static final Event.Type<TouchEvent> TOUCH_STARTED
            = new Event.Type<TouchEvent>("TOUCH_STARTED", ANY);

    public static final Event.Type<TouchEvent> TOUCH_MOVED
            = new Event.Type<TouchEvent>("TOUCH_MOVED", ANY);

    public static final Event.Type<TouchEvent> TOUCH_END
            = new Event.Type<TouchEvent>("TOUCH_END", ANY);

    public TouchEvent(T source, Event.Type<TouchEvent> eventType,
          List<Touch> touches//, int eventGroupId
    ) {
        super(source, eventType);
        this.touches = touches != null ? Collections.unmodifiableList(touches) : null;
//        this.eventGroupId = eventGroupId;
    }

//    public int getNumTouches() {
//        return touches.size();
//    }
    @Override
    public Event.Type<TouchEvent> getType() {
        return (Event.Type<TouchEvent>) super.getType();
    }

//    public final int getEventGroupId() {
//        return eventGroupId;
//    }

    public Touch getPrimaryTouch() {
        return touches.get(0);
    }

    public List<Touch> getAllTouches() {
        return touches;
    }
    
    public static class Touch {

        double x, y;

        public Touch(double x, double y) {
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

}
