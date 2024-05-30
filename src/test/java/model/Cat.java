package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cat {
    private final String name;
    private final String breed;
    private final String[] properties;
    @JsonCreator
    public Cat(@JsonProperty("name") String name,
               @JsonProperty("breed") String breed,
               @JsonProperty("properties") String[] properties) {
        this.name = name;
        this.breed = breed;
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public String[] getProperties() {
        return properties;
    }
}