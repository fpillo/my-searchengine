package com.pillo.mysearchengine.models;

import java.util.Set;

public interface TokenFilter {

    Set<Token> filter(Set<Token> tokens);

}
