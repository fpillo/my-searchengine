package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@ToString(of = {"name"})
public class MovieData implements MetaData {

    @NotBlank
    private final String name;

    @NotBlank
    private final String text;

}
