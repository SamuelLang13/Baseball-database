package cz.langsamu.tjv.baseballdatabase.client_console.data;

import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.PlayerView;
import org.springframework.beans.factory.annotation.Value;
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
    private Long currentPlayerID;

    public PlayerClient(@Value("http://localhost:8080") String backedUrl, PlayerView playerView) {
        playerWebClient = WebClient.create(backedUrl+"/players");
        this.playerView = playerView;
    }

    public Long getCurrentPlayerID(){
        return currentPlayerID;
    }

    public void setCurrentPlayerID(Long id){
        this.currentPlayerID = id;
        if(currentPlayerID!= null){
            try{
                readOne();
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

    private PlayerDTO readOne() {
        if(currentPlayerID == null)
            throw new IllegalStateException("Current player's ID is not set");
        return playerWebClient.get()
                .uri("/{id}", currentPlayerID)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
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
                        e -> {PlayerView.printErrorPlayer(e);}
                );
    }
}
