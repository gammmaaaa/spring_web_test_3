package ru.t1.java.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.t1.java.demo.model.Client;
import ru.t1.java.demo.model.dto.CheckRequest;
import ru.t1.java.demo.model.dto.CheckResponse;
import ru.t1.java.demo.model.dto.ClientDto;
import ru.t1.java.demo.service.ClientService;
import ru.t1.java.demo.util.ClientMapper;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    @GetMapping(value = "/parse")
    public void parseSource() {
        throw new IllegalStateException();

//        clientRepository.save(Client.builder()
//                .firstName("John42")
//                .build());
//        clientRepository.findClientByFirstName("John42");
//        metricService.incrementByName(Metrics.CLIENT_CONTROLLER_REQUEST_COUNT.getValue());
    }

    @PostMapping(value = "/checkClient")
    public ResponseEntity<CheckResponse> parseSource(@RequestBody CheckRequest checkRequest) {
        return ResponseEntity.ok()
                .body(CheckResponse.builder()
                        .blocked(Math.random() < 0.5)
                        .build());
    }

    @GetMapping("/client")
    public ResponseEntity<ClientDto> getClient() {
        return ResponseEntity.ok()
                .body(ClientDto.builder()
                        .firstName("John")
                        .build());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/register")
    public ResponseEntity<Client> register(@RequestBody ClientDto clientDto) {
        log.info("Registering client: {}", clientDto);
        Client client = clientService.registerClient(
                clientMapper.toEntityWithId(clientDto)
        );
        log.info("Client registered: {}", client.getId());
        return ResponseEntity.ok().body(client);
    }
}
