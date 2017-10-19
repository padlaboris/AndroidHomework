package com.example.padlabear.hw;

import junit.framework.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Mocks {

    public static String stream(final String name) throws IOException {
        final InputStream resourceAsStream = Mocks.class.getClassLoader().getResourceAsStream(name);
        Assert.assertNotNull("Not found json file", resourceAsStream);
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = resourceAsStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString("UTF-8");
    }


}
