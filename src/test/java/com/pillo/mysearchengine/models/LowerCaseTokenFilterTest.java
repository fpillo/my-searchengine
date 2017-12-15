package com.pillo.mysearchengine.models;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class LowerCaseTokenFilterTest {

    private LowerCaseTokenFilter lowerCaseTokenFilter = new LowerCaseTokenFilter();

    @Test
    public void test_filter_one_token_should_return_one_lowercase() throws Exception {
        final Set<Token> originalTokens = new HashSet<>();
        final Token originalToken = new Token("ToKen");
        originalTokens.add(originalToken);

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(1, resultTokens.size());
        assertEquals("token", resultTokens.get(0).getValue());
    }

    @Test
    public void test_filter_two_identical_tokens_should_return_one_lowercase() throws Exception {
        final Set<Token> originalTokens = new HashSet<>();
        originalTokens.addAll(Arrays.asList(new Token("ToKen"), new Token("ToKen")));

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(1, resultTokens.size());
        assertEquals("token", resultTokens.get(0).getValue());
    }

    @Test
    public void test_filter_two_different_cases_tokens_should_return_one_lowercase() throws Exception {
        final Set<Token> originalTokens = new HashSet<>();
        originalTokens.addAll(Arrays.asList(new Token("ToKen"), new Token("toKeN")));

        final List<Token> resultTokens = lowerCaseTokenFilter.filter(originalTokens).stream().collect(Collectors.toList());

        assertEquals(1, resultTokens.size());
        assertEquals("token", resultTokens.get(0).getValue());
    }

}
