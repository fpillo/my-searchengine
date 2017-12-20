package com.pillo.mysearchengine.models;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SearchResponse {

    private final Long took;

    private final List<Document> documents = new ArrayList<>();

    public SearchResponse(final Long took, final List<Document> documents) {
        this.took = took;
        this.documents.addAll(documents);
    }

    public Integer size() {
        if (CollectionUtils.isEmpty(documents)) {
            return 0;
        }

        return documents.size();
    }

    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        documents.forEach(document -> {
            stringBuilder.append(document.getMetaData().getName()).append("\n");
        });

        return stringBuilder.toString();
    }

}
