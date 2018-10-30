
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

import com.dukescript.api.canvas.events.Event;
import com.dukescript.api.canvas.events.EventHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 *
 * @author antonepple
 */
public  class EventDispatcher {

    private static EventDispatcher INSTANCE;
    private final Map<Event.Type<? extends Event>,ProxyEventHandler<? extends Event>> eventHandlerMap;

    public EventDispatcher() {
        eventHandlerMap =
                new HashMap<Event.Type<? extends Event>,ProxyEventHandler<? extends Event>>();
    }

    public final <T extends Event> void addEventHandler(
            final Event.Type<T> eventType,
            final EventHandler<? super T> eventHandler) {
        validateEventType(eventType);
        validateEventHandler(eventHandler);

        final ProxyEventHandler<T> proxy = createGetEventHandlerProxy(eventType);

        proxy.add((EventHandler<T>) eventHandler);
    }

    /**
     * Unregisters a previously registered event handler.
     *
     * @param <T> the specific event class of the handler
     * @param eventType the event type from which to unregister
     * @param eventHandler the handler to unregister
     * @throws NullPointerException if the event type or handler is null
     */
    public final <T extends Event> void removeEventHandler(
            final Event.Type<T> eventType,
            final EventHandler<? super T> eventHandler) {
        validateEventType(eventType);
        validateEventHandler(eventHandler);

        final ProxyEventHandler<T> proxy = (ProxyEventHandler<T>) eventHandlerMap.get(eventType);

        if (proxy != null) {
            proxy.remove(eventHandler);
        }
    }

    private static void validateEventType(final Event.Type<?> eventType) {
        if (eventType == null) {
            throw new NullPointerException("Event type cannot be null");
        }
    }

    private static void validateEventHandler(
            final EventHandler<?> eventHandler) {
        if (eventHandler == null) {
            throw new NullPointerException("Event handler cannot be null");
        }
    }

    public static EventDispatcher create() {
        if (INSTANCE == null) {
            ServiceLoader<EventDispatcher> dispatchers = ServiceLoader.load(EventDispatcher.class);
            for (EventDispatcher eventDispatcher : dispatchers) {
                INSTANCE = eventDispatcher;
                return eventDispatcher;
            }
        }
        return INSTANCE;
    }

    public void dispatchEvent(Event event){
        Event.Type type = event.getType();
        do {
            dispatchEvent(type, event);
            type = type.getSuperType();
        } while (type != null);
        
    }
    
    private void dispatchEvent(
            final Event.Type<? extends Event> handlerType, Event event) {
        final ProxyEventHandler<? extends Event> proxy =
                eventHandlerMap.get(handlerType);

        if (proxy != null) {
            proxy.dispatchEvent(event);
        }
    }

    
    private <T extends Event> ProxyEventHandler<T>
            createGetEventHandlerProxy(final Event.Type<T> eventType) {

        ProxyEventHandler<? extends Event> proxy = eventHandlerMap.get(eventType);
        if (proxy == null) {
            proxy = new ProxyEventHandler<Event>();
            eventHandlerMap.put(eventType, proxy);
        }
        return (ProxyEventHandler<T>) proxy;
    }

    
}
