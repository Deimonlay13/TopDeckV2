package cl.gdl.ms_pedido.dto;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CartaDTO {
    private String name;
    private ImageUrlDTO images;
    private String supertype;
    private String rarity;
    private String number;
    private String artist;
    private CardMarketDTO cardmarket;
    
    public BigDecimal getAverageSellPrice() {
        if (cardmarket != null && cardmarket.getPrices() != null) {
            return BigDecimal.valueOf(cardmarket.getPrices().getAverageSellPrice());
        }
        return BigDecimal.ZERO;
    }
    
}


