package cl.gdl.ms_pedido.dto;


import lombok.Data;
import lombok.experimental.FieldDefaults;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardMarketDTO {
    CardMarketPriceDTO prices;
    

}
