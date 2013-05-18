package com.github.soaserele.docx4jmerge;

import org.apache.commons.io.IOUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(JUnit4.class)
public class TestMergeUtil {
    private static final List<String> RESOURCES = Arrays.asList("/Hello.docx", "/World.docx");

    private static final List<String> source = new ArrayList<String>() {
        private static final long serialVersionUID = 1L;

        {
            add("Hello.docx");
            add("World.docx");
        }
    };

    @Test
    public void testMerge() throws IOException, Docx4JException {
        List<InputStream> streamsToMerge = new ArrayList<InputStream>();
        for (String resource : RESOURCES) {
            streamsToMerge.add(TestMergeUtil.class.getResourceAsStream(resource));
        }

        byte[] resultAsBytes = IOUtils.toByteArray(MergeUtil.merge(streamsToMerge));
        Assert.assertNotNull(resultAsBytes);
        Assert.assertTrue(resultAsBytes.length > 0);
    }
}
