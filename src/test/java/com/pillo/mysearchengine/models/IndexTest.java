package com.pillo.mysearchengine.models;

import org.junit.Before;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IndexTest {

    private Index index;

    @Before
    public void setUp() {
        final Map<Token, Set<Document>> invertedMap = new HashMap<>();
        index = new Index(invertedMap);
    }



}
