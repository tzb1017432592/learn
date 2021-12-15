package com.example.reflectLearn.clazzLoader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * @author tianzhoubing
 * @date 2021/12/1 20:22
 * @description
 **/
public class CustomLoader2 extends URLClassLoader {
    public CustomLoader2(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public CustomLoader2(URL[] urls) {
        super(urls);
    }

    public CustomLoader2(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
