package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@ToString
public class SearchResponse {

    private final Long took;

    private final List<Document> documents;

}
