package com.pillo.mysearchengine.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@ToString(of = {"name"})
@EqualsAndHashCode
public class Document {

    @NotBlank
    private final String name;

    @NotBlank
    private final String text;

}
