package cz.langsamu.tjv.baseballdatabase.client_console.data;

import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.BaseballDatabasePrompt;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.PlayerView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;
import java.util.Collection;

@Component
public class PlayerClient {
    private final WebClient playerWebClient;
    private PlayerDTO currentPlayer;

    public PlayerClient(@Value("${backend_url}") String backedUrl) {
        playerWebClient = WebClient.create(backedUrl + "/players");
    }

    public PlayerDTO create(PlayerDTO user) {
        return playerWebClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(PlayerDTO.class)
                .block(Duration.ofSeconds(5));
    }

    public PlayerDTO readById(Long id) {
        return playerWebClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(PlayerDTO.class)
                .block();
    }

    public Collection<PlayerDTO> readAll() {
        return playerWebClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToFlux(PlayerDTO.class)
                .collectList()
                .block();
    }

    public PlayerDTO update(PlayerDTO playerDTO) {
        return playerWebClient.put()
                .uri("/{id}", currentPlayer.getPlayerID())
                .bodyValue(playerDTO)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(PlayerDTO.class)
                .block();
    }

    public void delete() {
        playerWebClient.delete()
                .uri("/{id}", currentPlayer.getPlayerID())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        x -> setCurrentPlayer(null),
                        e -> PlayerView.printError(new RuntimeException(e.getMessage()))
                );
    }

    public PlayerDTO getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerDTO player) {
        this.currentPlayer = player;
    }

}
