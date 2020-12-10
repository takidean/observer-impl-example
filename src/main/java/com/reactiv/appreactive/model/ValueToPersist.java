package com.reactiv.appreactive.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Setter
@Getter
public class ValueToPersist {
    Integer finalValue;
    @TextIndexed
    String name;
}
