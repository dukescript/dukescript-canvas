
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
