package com.pillo.mysearchengine.models;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class LowerCaseTokenFilterTest {

    private LowerCaseTokenFilter lowerCaseTokenFilter = new LowerCaseTokenFilter();

    @Test
    public void test_filter_one_token_should_return_one_lowercase() throws Exception {
        final List<Token> originalTokens = new ArrayList<>();
        final Token originalToken = new Token("ToKen");
        originalTokens.add(originalToken);

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(1, resultTokens.size());
        assertEquals("token", resultTokens.get(0).getValue());
    }

    @Test
    public void test_filter_two_identical_tokens_should_return_one_lowercase() throws Exception {
        final List<Token> originalTokens = new ArrayList<>();
        originalTokens.addAll(Arrays.asList(new Token("ToKen"), new Token("ToKen")));

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(1, resultTokens.size());
        assertEquals("token", resultTokens.get(0).getValue());
    }

    @Test
    public void test_filter_two_different_cases_tokens_should_return_two_lowercase() throws Exception {
        final List<Token> originalTokens = new ArrayList<>();
        originalTokens.addAll(Arrays.asList(new Token("ToKen"), new Token("toKeN")));

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(2, resultTokens.size());
        assertEquals("token", resultTokens.get(0).getValue());
        assertEquals("token", resultTokens.get(1).getValue());
    }

    @Test
    public void test_filter_two_different_tokens_should_return_two_lowercase() throws Exception {
        final List<Token> originalTokens = new ArrayList<>();
        originalTokens.addAll(Arrays.asList(new Token("ToKen"), new Token("WORD")));

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(2, resultTokens.size());
        assertEquals("token", resultTokens.get(0).getValue());
        assertEquals("word", resultTokens.get(1).getValue());
    }

    @Test
    public void test_filter_three_different_tokens_should_return_three_lowercase() throws Exception {
        final List<Token> originalTokens = new ArrayList<>();
        originalTokens.addAll(Arrays.asList(new Token("12B3"), new Token("tokeN!"), new Token("@A")));

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(3, resultTokens.size());
        assertEquals("12b3", resultTokens.get(0).getValue());
        assertEquals("token!", resultTokens.get(1).getValue());
        assertEquals("@a", resultTokens.get(1).getValue());
    }

}
