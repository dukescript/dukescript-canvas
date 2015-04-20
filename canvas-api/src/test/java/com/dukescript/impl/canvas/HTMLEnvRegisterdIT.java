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
package com.dukescript.impl.canvas;

import com.dukescript.spi.canvas.GraphicsEnvironment;
import java.io.File;
import java.util.Enumeration;
import java.util.ServiceLoader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

public class HTMLEnvRegisterdIT {
    @Test public void checkFactoryIsReallyAvailable() {
        for (GraphicsEnvironment e : ServiceLoader.load(GraphicsEnvironment.class)) {
            if (e instanceof HTML5GraphicsEnvironment) {
                return;
            }
        }
        fail("cannot find HTML5GraphicsEnvironment in " + System.getProperty("java.class.path"));
    }
    
    @Test public void noAnnotationProcessorRegistrationFound() throws Exception {
        final String expName = GraphicsEnvironment.class.getName();
        File dir= new File(HTML5GraphicsEnvironment.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        File mtfs = new File(new File(dir, "META-INF"), "services");
        int cnt = 0;
        for (String name : mtfs.list()) {
            assertEquals(name, expName);
            cnt++;
        }
        assertEquals(cnt, 1, "Only one entry in META-INF/services");
    }
}
