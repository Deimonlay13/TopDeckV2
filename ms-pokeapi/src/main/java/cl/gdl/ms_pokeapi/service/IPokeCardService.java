package cl.gdl.ms_pokeapi.service;

import java.io.IOException;
import java.util.List;

import cl.gdl.ms_pokeapi.dto.PokeCardDTO;

public interface IPokeCardService {

    List<PokeCardDTO> getCardsByType(String type) throws IOException, InterruptedException;

    List<PokeCardDTO> getCardsByName(String name) throws IOException, InterruptedException;

    List<PokeCardDTO> getCardsBySet(String set) throws IOException, InterruptedException;

    List<PokeCardDTO> getCardsBySetDate(String date) throws IOException, InterruptedException;

    PokeCardDTO getCartaById(String id) throws IOException, InterruptedException;
    
}
