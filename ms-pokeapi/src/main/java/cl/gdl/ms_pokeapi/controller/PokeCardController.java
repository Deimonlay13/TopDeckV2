package cl.gdl.pokeapi.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.gdl.pokeapi.dto.PokeCardDTO;
import cl.gdl.pokeapi.service.IPokeCardService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/pokemon-cards")
public class PokeCardController {
    private final IPokeCardService pokeCardService;
    public PokeCardController(IPokeCardService pokeCardService) {
        this.pokeCardService = pokeCardService;
    }
    @GetMapping("/by-type")
    public List<PokeCardDTO> getCardsByType(@RequestParam String type) throws IOException, InterruptedException {
        return pokeCardService.getCardsByType(type);
    }
    
    @GetMapping("/by-name")
    public List<PokeCardDTO> getCardsByName(@RequestParam String name) throws IOException, InterruptedException {
        return pokeCardService.getCardsByName(name);
    }

    @GetMapping("/by-set")
    public List<PokeCardDTO> getCardsBySet(@RequestParam String set) throws IOException, InterruptedException {
        return pokeCardService.getCardsBySet(set);
    }

    @GetMapping("/by-set-date")
    public List<PokeCardDTO> getCardsBySetDate(@RequestParam String date) throws IOException, InterruptedException {
        return pokeCardService.getCardsBySetDate(date);
    }

}
