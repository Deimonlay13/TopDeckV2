package cl.gdl.ms_pokeapi.service.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.gdl.ms_pokeapi.dto.PokeCardDTO;
import cl.gdl.ms_pokeapi.response.PokeCardResponse;
import cl.gdl.ms_pokeapi.service.IPokeCardService;

@Service
public class PokeCardServiceImpl implements IPokeCardService{

    private static final Set<String> VALID_TYPES = new HashSet<>(Arrays.asList(
            "colorless", "darkness", "dragon", "fairy", "fighting",
            "fire", "grass", "lightning", "metal", "psychic", "water"));

    @Override
    public List<PokeCardDTO> getCardsByType(String type) throws IOException, InterruptedException {
        String selectedType = type.trim().toLowerCase();

        if (!VALID_TYPES.contains(type)) {
            throw new IllegalArgumentException("Tipo no válido: " + type + ". Tipos válidos: " + VALID_TYPES);
        }

        String apiUrl = String.format("https://api.pokemontcg.io/v2/cards?q=types:%s&pageSize=20", selectedType);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            PokeCardResponse cardResponse = mapper.readValue(response.body(), PokeCardResponse.class);
            return cardResponse.getData();
        } else {
            throw new IOException("Error al obtener cartas, código de estado: " + response.statusCode());
        }
        
    }


    @Override
    public List<PokeCardDTO> getCardsByName(String name) throws IOException, InterruptedException {

        String selectedName = name.trim().toLowerCase();
        String apiUrl = String.format("https://api.pokemontcg.io/v2/cards?q=name:%s&pageSize=20", selectedName);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            PokeCardResponse cardResponse = mapper.readValue(response.body(), PokeCardResponse.class);
            return cardResponse.getData();
        } else {
            throw new IOException("Error al obtener cartas, código de estado: " + response.statusCode());
        }
    }


    @Override
    public List<PokeCardDTO> getCardsBySet(String set) throws IOException, InterruptedException {
        String selectedSet = set.trim().toLowerCase();
        String apiUrl = String.format("https://api.pokemontcg.io/v2/cards?q=set.name:%s&pageSize=20", selectedSet);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            PokeCardResponse cardResponse = mapper.readValue(response.body(), PokeCardResponse.class);
            return cardResponse.getData();
        } else {
            throw new IOException("Error al obtener cartas, código de estado: " + response.statusCode());
        }
    }

    @Override
    public List<PokeCardDTO> getCardsBySetDate(String date) throws IOException, InterruptedException {
        String selectedSetDate = date.trim().toLowerCase();
        String encodedQuery = URLEncoder.encode("name:" + selectedSetDate, StandardCharsets.UTF_8);
        String apiUrl = String.format("https://api.pokemontcg.io/v2/cards?q=%s&orderBy=-set.releaseDate&pageSize=20", encodedQuery);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            PokeCardResponse cardResponse = mapper.readValue(response.body(), PokeCardResponse.class);
            return cardResponse.getData();
        } else {
            throw new IOException("Error al obtener cartas, código de estado: " + response.statusCode());
        }
    }

    @Override
    public PokeCardDTO getCartaById(String id) throws IOException, InterruptedException {
        String apiUrl = "https://api.pokemontcg.io/v2/cards/" + id;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode dataNode = root.get("data");

            if (dataNode == null || dataNode.isNull()) {
                throw new IOException("Respuesta inválida: no se encontró campo 'data'");
            }

            PokeCardDTO carta = mapper.treeToValue(dataNode, PokeCardDTO.class);
            System.out.println("Carta obtenida: " + carta);

            return carta;
        } else {
            throw new IOException("Error al obtener carta, código de estado: " + response.statusCode());
        }
    }    

}
