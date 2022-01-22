package cz.langsamu.tjv.baseballdatabase.client_console.data;

import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Component
public class PlayerAwardClient {

    private final WebClient client;

    public PlayerAwardClient(@Value("${backend_url}") String backedUrl) {
        client = WebClient.create(backedUrl + "/PlayerAward");
    }

    public void registerPlayerAward(Long awardId, Long playerId) {
        client.post()
                .uri("/addingPlayerAward/{awardID}/{playerID}", awardId, playerId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .toBodilessEntity()
                .block();
    }
}

