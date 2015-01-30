/*
 * #%L
 * DukeScript Canvas API - a library from the "DukeScript Canvas API" project.
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
package com.dukescript.html.canvas.spi;

import com.dukescript.html.canvas.GraphicsContext2D;
import com.dukescript.html.canvas.impl.CnvsAccssr;

/**
 *
 * @author antonepple
 */
public class GraphicsUtils {

    private GraphicsUtils() {
    }

    /**
     * Use this to get A GraphicsContext2D to draw on. Pass in a  
     * {@link com.dukescript.html.canvas.spi.GraphicsEnvironment} and a String to identify
     * your Canvas. 
     * In the HTML5 implementation, the {@link com.dukescript.html.canvas.spi.GraphicsEnvironment} 
     * will look for a Canvas with that ID in your HTML (or add a new one to the page).
     * @param env the GraphicsEnvironment
     * @param id used by the {@link GraphicsEnvironment to identify the Canvas}
     * @return A GraphicsContext2D
     */
    public static GraphicsContext2D getOrCreate(GraphicsEnvironment env, String id) {       
        return CnvsAccssr.getDefault().create(env, env.getOrCreateCanvas(id));
    }

}
