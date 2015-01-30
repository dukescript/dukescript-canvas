/*
 * #%L
 * DukeScript HTML5 Canvas - a library from the "DukeScript Canvas API" project.
 * Visit http://dukescript.com for support and commercial license.
 * %%
 * Copyright (C) 2015 Eppleton IT Consulting
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package com.dukescript.canvas.html;

import net.java.html.js.JavaScriptBody;


/**
 *
 * @author Anton Epple toni.epple@eppleton.de
 */
class TextMetrics {

    private Object textMetrics;

    TextMetrics(Object measureTextImpl) {
        this.textMetrics = measureTextImpl;
    }

    @JavaScriptBody(args = {"textMetrics"}, body = "return textMetrics.width;")
    private native double getWidth(Object textMetrics);

    @JavaScriptBody(args = {"textMetrics"}, body = "return textMetrics.height;")
    private native double getHeight(Object textMetrics);

    public double getWidth() {
        return getWidth(textMetrics);
    }

    public double getHeight() {
        return getHeight(textMetrics);
    }
}
