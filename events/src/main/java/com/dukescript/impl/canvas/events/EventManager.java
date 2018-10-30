/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dukescript.impl.canvas.events;

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
import com.dukescript.api.canvas.events.EventSource;
import com.dukescript.api.canvas.events.Event;
import com.dukescript.api.canvas.events.EventHandler;
import com.dukescript.api.canvas.events.KeyCode;
import com.dukescript.api.canvas.events.KeyEvent;
import com.dukescript.api.canvas.events.MouseEvent;
import com.dukescript.api.canvas.events.TouchEvent;
import com.dukescript.api.canvas.events.TouchEvent.Touch;
import java.util.ArrayList;

/**
 *
 * @author antonepple
 */
public class EventManager<S> extends EventSource<S> {

    private final S source;
    private final EventDispatcher ed;

    public EventManager(S source, String id) {
        this.source = source;
        this.ed = new EventDispatcher();

    }

    @Override
    public final <T extends Event> void addEventHandler(Event.Type<T> type, EventHandler<T> handler) {
        ed.addEventHandler(type, handler);
    }

    @Override
    public final <T extends Event> void removeEventHandler(Event.Type<T> type, EventHandler<T> handler) {
        ed.removeEventHandler(type, handler);
    }

    public void keyPress(int keyCode) {
        if (ed != null) {
            ed.dispatchEvent(new KeyEvent<S>(source, KeyEvent.KEY_PRESSED, KeyCode.getKeyCode(keyCode)));
        }
    }

    public void keyUp(int keyCode) {
        if (ed != null) {
            ed.dispatchEvent(new KeyEvent<S>(source, KeyEvent.KEY_RELEASED, KeyCode.getKeyCode(keyCode)));
        }
    }

    public void keyDown(int keyCode) {
        if (ed != null) {
            ed.dispatchEvent(new KeyEvent<S>(source, KeyEvent.KEY_TYPED, KeyCode.getKeyCode(keyCode)));
        }
    }

    public void touchStarted(Object[] touches) {
        if (ed != null) {
            ArrayList<Touch> touchList = new ArrayList<Touch>();
            for (int i = 0; i < touches.length; i++) {
                Object touchX = touches[i];
                Object touchY = touches[++i];
                TouchEvent.Touch touch = new TouchEvent.Touch((Double) touchX, (Double) touchY);
                touchList.add(touch);
            }
            ed.dispatchEvent(new TouchEvent<S>(source,
                    TouchEvent.TOUCH_STARTED,
                    touchList
            ));
        }
    }

    public void touchCancel(Object[] touches) {
        if (ed != null) {
            ArrayList<Touch> touchList = new ArrayList<Touch>();
            for (int i = 0; i < touches.length; i++) {
                Object touchX = touches[i];
                Object touchY = touches[++i];
                TouchEvent.Touch touch = new TouchEvent.Touch((Double) touchX, (Double) touchY);
                touchList.add(touch);
            }
            ed.dispatchEvent(new TouchEvent<S>(source,
                    TouchEvent.TOUCH_END,
                    touchList
            ));
        }
    }

    public void touchMove(Object[] touches) {
        if (ed != null) {
            ArrayList<Touch> touchList = new ArrayList<Touch>();
            for (int i = 0; i < touches.length; i++) {
                Object touchX = touches[i];
                Object touchY = touches[++i];
                TouchEvent.Touch touch = new TouchEvent.Touch((Double) touchX, (Double) touchY);
                touchList.add(touch);
            }
            ed.dispatchEvent(new TouchEvent<S>(source,
                    TouchEvent.TOUCH_MOVED,
                    touchList
            ));
        }
    }

    public void touchEnd(Object[] touches) {
        if (ed != null) {
            ArrayList<Touch> touchList = new ArrayList<Touch>();
            for (int i = 0; i < touches.length; i++) {
                Object touchX = touches[i];
                Object touchY = touches[++i];
                TouchEvent.Touch touch = new TouchEvent.Touch((Double) touchX, (Double) touchY);
                touchList.add(touch);
            }
            ed.dispatchEvent(new TouchEvent<S>(source,
                    TouchEvent.TOUCH_END,
                    touchList
            ));
        }
    }
//  mousedown, mouseenter, mouseleave, mousemove, mouseout, mouseover, mouseup,

    public void mouseClick(int realX, int realY) {
        if (ed != null) {
            ed.dispatchEvent(new MouseEvent<S>(source, MouseEvent.MOUSE_CLICKED, realX, realY));
        }
    }

    public void mousePressed(int realX, int realY) {
        if (ed != null) {
            ed.dispatchEvent(new MouseEvent<S>(source, MouseEvent.MOUSE_PRESSED, realX, realY));
        }
    }

    public void mouseUp(int realX, int realY) {
        if (ed != null) {
            ed.dispatchEvent(new MouseEvent<S>(source, MouseEvent.MOUSE_RELEASED, realX, realY));
        }
    }
}
