
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
public class KeyEvent<T> extends Event<T>{

    public static final Event.Type<KeyEvent> ANY =
            new Event.Type<KeyEvent>("KEY", Event.ANY);

    public static final Event.Type<KeyEvent> KEY_PRESSED =
            new Event.Type<KeyEvent>("KEY_PRESSED",ANY);

    public static final Event.Type<KeyEvent> KEY_RELEASED =
            new Event.Type<KeyEvent>( "KEY_RELEASED",ANY);

    public static final Event.Type<KeyEvent> KEY_TYPED =
            new Event.Type<KeyEvent>("KEY_TYPED",ANY);

    
    private final KeyCode keyCode;
    public KeyEvent(T source, Type type, KeyCode keyCode) {
        super(source, type);
        this.keyCode = keyCode;
    }

    public KeyCode getKeyCode() {
        return keyCode;
    }

    
     
   
    
}
