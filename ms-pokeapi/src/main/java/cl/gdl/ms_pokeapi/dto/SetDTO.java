package cl.gdl.ms_pokeapi.dto;


import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SetDTO {
    String id;
    String name;
    String series;
    String printedTotal;
    String releaseDate;

}
