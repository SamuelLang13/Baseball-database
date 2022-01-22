package cz.langsamu.tjv.baseballdatabase.client_console.data;

import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.model.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Collection;

@Component
public class TeamClient {

    private final WebClient webClient;
    private TeamDTO currentTeam;

    public TeamClient(@Value("${backend_url}") String backendUrl) {
        webClient = WebClient.create(backendUrl + "/teams");
    }

    public TeamDTO registerNewTeam(TeamDTO teamDTO) {
        return webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(teamDTO)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(TeamDTO.class)
                .block(Duration.ofSeconds(5));
    }

    public TeamDTO readById(Long id) {
        return webClient.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(TeamDTO.class)
                .block();
    }

    public Collection<TeamDTO> readAll() {
        return webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToFlux(TeamDTO.class)
                .collectList()
                .block();
    }

    public TeamDTO getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(TeamDTO currentTeam) {
        this.currentTeam = currentTeam;
    }
}
