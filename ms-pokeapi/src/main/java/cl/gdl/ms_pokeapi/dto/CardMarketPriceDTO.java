package cl.gdl.ms_pokeapi.dto;


import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardMarketPriceDTO {
    Double averageSellPrice;


}
