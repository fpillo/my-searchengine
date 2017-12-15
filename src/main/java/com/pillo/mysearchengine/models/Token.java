package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(of = {"value"})
public class Token implements Comparable<Token> {

    private final String value;

    @Override
    public int compareTo(final Token token) {
        return value.compareTo(token.getValue());
    }
}

