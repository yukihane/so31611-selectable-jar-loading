package com.github.yukihane.so31611;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.eclipse.jdt.internal.jarinjarloader.JIJConstants;
import org.eclipse.jdt.internal.jarinjarloader.RsrcURLStreamHandlerFactory;

public class Launcher {

    public static void main(final String[] args) throws Exception {
        final ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(cl));

        final String rsrcPath = System.getProperty("loadJar");

        final URL currentUrl = new URL(JIJConstants.INTERNAL_URL_PROTOCOL_WITH_COLON + JIJConstants.CURRENT_DIR);
        final URL jarUrl = new URL(
                JIJConstants.JAR_INTERNAL_URL_PROTOCOL_WITH_COLON + rsrcPath + JIJConstants.JAR_INTERNAL_SEPARATOR);

        final ClassLoader jceClassLoader = new URLClassLoader(new URL[] { currentUrl, jarUrl }, null);
        Thread.currentThread().setContextClassLoader(jceClassLoader);

        final Class <?> c = Class.forName("com.github.yukihane.so31611.Main", true, jceClassLoader);
        final Method main = c.getMethod(JIJConstants.MAIN_METHOD_NAME, new Class[] { args.getClass() });
        main.invoke((Object) null, new Object[] { args });
    }
}
