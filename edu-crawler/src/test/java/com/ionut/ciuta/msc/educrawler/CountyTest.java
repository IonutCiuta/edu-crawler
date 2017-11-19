package com.ionut.ciuta.msc.educrawler;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ionutciuta24@gmail.com on 19.11.2017.
 */
public class CountyTest {

    @Test
    public void test() throws Exception {
        assertEquals(42, County.getAll().size());
    }
}