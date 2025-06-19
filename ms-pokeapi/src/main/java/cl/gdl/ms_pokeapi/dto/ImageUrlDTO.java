package cl.gdl.ms_pokeapi.dto;


import lombok.Data;
import lombok.experimental.FieldDefaults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ImageUrlDTO {
    String small;
}
