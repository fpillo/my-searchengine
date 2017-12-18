package com.pillo.mysearchengine.models;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

public class IntersectionGrouper implements DocumentGrouper {

    @Getter
    private Set<Document> grouped;

    @Override
    public Set<Document> group(final Set<Document> documents) {
        if (grouped == null) {
            grouped = new HashSet<>(documents);
        } else {
            grouped.retainAll(documents);
        }

        return grouped;
    }
}
