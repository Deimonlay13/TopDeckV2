package cl.gdl.pokeapi.dto;


import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.List;
import lombok.AccessLevel;

@Data
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
