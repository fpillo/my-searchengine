package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@ToString
public class SearchRequest {

    @NotBlank
    private final String q;

    @NotNull
    private final SearchOperator operator;

}
