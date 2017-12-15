package com.pillo.mysearchengine.models;

import java.util.List;

public interface TokenFilter {

    List<Token> filter(List<Token> tokens);

}
