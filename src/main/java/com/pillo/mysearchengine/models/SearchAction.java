package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public class SearchAction {

    private final Set<Token> tokens;

}
