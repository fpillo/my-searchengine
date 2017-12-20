package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class SearchResponse {

    private final Long took;

    private final List<Document> documents;

    public Integer size() {
        if (CollectionUtils.isEmpty(documents)) {
            return 0;
        }

        return documents.size();
    }

}
