package com.deserialize.mapping.utils;

import com.deserialize.mapping.main.AbstractDeserializeMappingPathType;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class FileUtils {

    public static File getFile(String resource) throws URISyntaxException, MalformedURLException {

        if (resource.startsWith(AbstractDeserializeMappingPathType.URL.getName())) {
            return Paths.get(new URL(resource.substring(AbstractDeserializeMappingPathType.URL.getName().length())).toURI()).toFile();
        }

        return new File(getFilePath(resource));
    }

    private static String getFilePath(String resource) throws URISyntaxException {
        if (StringUtils.isBlank(resource)) {
            throw new RuntimeException("'resource' cannot be blank");
        }

        if (resource.startsWith(AbstractDeserializeMappingPathType.FILE.getName())) {
            return resource.substring(AbstractDeserializeMappingPathType.FILE.getName().length());
        }

        if (resource.startsWith(AbstractDeserializeMappingPathType.CLASSPATH.getName())) {
            return String.valueOf(Paths.get(ClassLoader.getSystemResource(resource.substring(AbstractDeserializeMappingPathType.CLASSPATH.getName().length())).toURI()));
        }

        return String.valueOf(Paths.get(ClassLoader.getSystemResource(resource).toURI()));
    }
}
