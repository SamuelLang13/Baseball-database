package cz.langsamu.tjv.baseballdatabase.client_console.data;

import cz.langsamu.tjv.baseballdatabase.client_console.model.AwardDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.model.PlayerDTO;
import cz.langsamu.tjv.baseballdatabase.client_console.ui.views.PlayerView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;
import java.util.Collection;

@Component
public class AwardClient {

    private final WebClient client;

    public AwardClient(@Value("${backend_url}") String backedUrl) {
        client = WebClient.create(backedUrl + "/awards");
    }

    public AwardDTO create(AwardDTO user) {
        return client.post()
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(user)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(AwardDTO.class)
                .block(Duration.ofSeconds(5));
    }

    public AwardDTO readById(Long id) {
        return client.get()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(AwardDTO.class)
                .block();
    }

    public Collection<AwardDTO> readAll() {
        return client.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToFlux(AwardDTO.class)
                .collectList()
                .block();
    }

    public AwardDTO update(Long id, AwardDTO awardDTO) {
        return client.put()
                .uri("/{id}", id)
                .bodyValue(awardDTO)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .bodyToMono(AwardDTO.class)
                .block();
    }

    public void delete(Long id) {
        client.delete()
                .uri("/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError,
                        errorResponse -> errorResponse.bodyToMono(String.class).map(RuntimeException::new))
                .toBodilessEntity()
                .block();
    }
}
