package com.pillo.mysearchengine.models;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StandardTokenizerTest {

    private StandardTokenizer standardTokenizer = new StandardTokenizer();

    @Test
    public void test_tokenize_should_return_one_token() throws Exception {
        final List<Token> tokens = standardTokenizer.tokenize("test");

        assertEquals(1, tokens.size());
        assertEquals("test", tokens.get(0).getValue());
    }

    @Test
    public void test_tokenize_with_trim_should_return_one_token() throws Exception {
        final List<Token> tokens = standardTokenizer.tokenize("  test ");

        assertEquals(1, tokens.size());
        assertEquals("test", tokens.get(0).getValue());
    }

    @Test
    public void test_tokenize_empty_text_should_return_zero_token() throws Exception {
        final List<Token> tokens = standardTokenizer.tokenize("");

        assertEquals(0, tokens.size());
    }

    @Test
    public void test_tokenize_should_return_four_tokens() throws Exception {
        final List<Token> tokens = standardTokenizer.tokenize(" This  is a test! ");

        assertEquals(4, tokens.size());
        assertEquals("This", tokens.get(0).getValue());
        assertEquals("is", tokens.get(1).getValue());
        assertEquals("a", tokens.get(2).getValue());
        assertEquals("test!", tokens.get(3).getValue());
    }
}
