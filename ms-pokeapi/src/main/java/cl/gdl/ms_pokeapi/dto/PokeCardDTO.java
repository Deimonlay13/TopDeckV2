package cl.gdl.ms_pokeapi.dto;


import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PokeCardDTO {
    String id;
    String name;
    ImageUrlDTO images;
    List <String> types;
    List <String> subtypes;
    String supertype;
    String rarity;
    String number;
    String artist;
    CardMarketDTO cardmarket;
    SetDTO set;


}
