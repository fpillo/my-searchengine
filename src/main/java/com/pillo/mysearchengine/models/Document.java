package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Document {

    private final String name;

    private final String text;

}
