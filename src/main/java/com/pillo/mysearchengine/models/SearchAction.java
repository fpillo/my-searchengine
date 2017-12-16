package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchAction {

    private final List<Token> tokens;

}
