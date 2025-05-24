package cl.gdl.pokeapi.dto;


import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardMarketDTO {
    CardMarketPriceDTO prices;
    

}
