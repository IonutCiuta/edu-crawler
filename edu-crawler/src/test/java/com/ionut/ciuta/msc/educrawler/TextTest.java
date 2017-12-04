package com.ionut.ciuta.msc.educrawler;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ionutciuta24@gmail.com on 04.12.2017.
 */
public class TextTest {
    @Test
    public void testNormalize() throws Exception {
        assertEquals("COMP DIGITALE", Text.normalize("COMP. DIGITALE"));
    }
}