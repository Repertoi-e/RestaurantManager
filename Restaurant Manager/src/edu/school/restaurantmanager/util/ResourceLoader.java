package edu.school.restaurantmanager.util;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;

import java.net.URL;

public class ResourceLoader {

    public static URL getResource(String resource) {
        URL url ;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader != null) {
            url = classLoader.getResource(resource);
            if(url != null)
                return url;
        }

        classLoader = Loader.class.getClassLoader();
        if(classLoader != null) {
            url = classLoader.getResource(resource);
            if(url != null)
                return url;
        }

        return ClassLoader.getSystemResource(resource);
    }
}
