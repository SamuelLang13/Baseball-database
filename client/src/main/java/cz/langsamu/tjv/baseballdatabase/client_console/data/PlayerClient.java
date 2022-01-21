package cz.langsamu.tjv.baseballdatabase.client_console.data;

import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.PlayerView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;

import java.time.Duration;
import java.util.Collection;

@Component
public class PlayerClient {
    private final WebClient playerWebClient;
    private final PlayerView playerView;
    private PlayerDTO currentPlayerID;

    public PlayerClient(@Value("${backend_url}") String backedUrl, PlayerView playerView) {
        playerWebClient = WebClient.create(backedUrl+"/players");
        this.playerView = playerView;
    }

    public PlayerDTO getCurrentPlayerID() {
        return currentPlayerID;
    }

    public void setCurrentPlayerID(PlayerDTO player){
        this.currentPlayerID = player;
        if(currentPlayerID!= null){
            try{
                readById(currentPlayerID.getPlayerID());
            }catch (WebClientException e){
                this.currentPlayerID=null;
                throw e;
            }
        }
    }

    public PlayerDTO create(PlayerDTO user) {
        return playerWebClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(PlayerDTO.class)
                .block(Duration.ofSeconds(5));
    }

    public Collection<PlayerDTO> readAll() {
        return playerWebClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(PlayerDTO.class)
                .collectList()
                .block();
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

    public void update(PlayerDTO playerDTO) {

    }

    public void delete() {
        if(currentPlayerID == null)
            throw new IllegalStateException("Current player's ID is not set");
        playerWebClient.delete()
                .uri("/{id}", currentPlayerID)
                .retrieve()
                .toBodilessEntity()
                .subscribe(
                        x -> {setCurrentPlayerID(null);},
                        e -> {PlayerView.printError(new RuntimeException(e));}
                );
    }
}
