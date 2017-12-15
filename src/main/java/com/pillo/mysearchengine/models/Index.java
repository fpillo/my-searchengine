package com.pillo.mysearchengine.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Index {

    private final String name;

    private final Map<String, Set<Document>> invertedMap;

    public Index(final String name) {
        this.name = name;
        this.invertedMap = new HashMap<>();
    }

}
