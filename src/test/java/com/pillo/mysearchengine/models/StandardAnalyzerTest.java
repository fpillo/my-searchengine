package com.pillo.mysearchengine.models;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StandardAnalyzerTest {

    private StandardAnalyzer standardAnalyzer;

    @Before
    public void setUp() {
        final Tokenizer tokenizer = new StandardTokenizer();
        final TokenFilter tokenFilter = new LowerCaseTokenFilter();

        standardAnalyzer = new StandardAnalyzer(tokenizer, Arrays.asList(tokenFilter));
    }

    @Test
    public void test_analyze() throws Exception {
        final List<Token> tokens = standardAnalyzer.analyze("This is a test for Standard Analyzer!");

        assertEquals(7, tokens.size());
        assertEquals("this", tokens.get(0).getValue());
        assertEquals("is", tokens.get(1).getValue());
        assertEquals("a", tokens.get(2).getValue());
        assertEquals("test", tokens.get(3).getValue());
        assertEquals("for", tokens.get(4).getValue());
        assertEquals("standard", tokens.get(5).getValue());
        assertEquals("analyzer!", tokens.get(6).getValue());
    }

}
