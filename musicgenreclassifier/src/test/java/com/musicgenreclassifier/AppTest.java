package com.musicgenreclassifier;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        Map<Integer, String> testMap = new HashMap<>();
        testMap.put(1, "ada");
        testMap.put(1, "new");
        System.out.println(testMap);
        assertTrue( true );
    }
}
