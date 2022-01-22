package cz.langsamu.tjv.baseballdatabase.client_console.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PlayerTeamClient {
    private final WebClient client;

    public PlayerTeamClient(@Value("${backend_url}") String backedUrl) {
        client = WebClient.create(backedUrl + "/PlayerTeam");
    }

    public void registerPlayerAward(Long playerID, Long teamID) {
        client.post()
                .uri("/addingPlayerTeam/{teamID}/{playerID}", teamID, playerID)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .toBodilessEntity()
                .block();
    }
}
