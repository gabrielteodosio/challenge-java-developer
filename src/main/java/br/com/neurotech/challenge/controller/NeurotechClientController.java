package br.com.neurotech.challenge.controller;

import br.com.neurotech.challenge.entity.NeurotechClient;
import br.com.neurotech.challenge.entity.NeurotechClientCredit;
import br.com.neurotech.challenge.entity.SpecialNeurotechClientCredit;
import br.com.neurotech.challenge.entity.VehicleModel;
import br.com.neurotech.challenge.service.NeurotechClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/client")
public class NeurotechClientController {

    @Autowired
    private NeurotechClientService neurotechClientService;

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody NeurotechClient neurotechClient
    ) {
        NeurotechClient savedClient = neurotechClientService.create(neurotechClient);

        if (savedClient != null) {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedClient.getId())
                    .toUri();

            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("Location", String.valueOf(location));

            return new ResponseEntity<>(savedClient, responseHeaders, HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Error saving the client to the database", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieve(
            @PathVariable("id") String id
    ) {
        Optional<NeurotechClient> clientOptional = neurotechClientService.retrieve(id);

        if (clientOptional.isPresent()) {
            return new ResponseEntity<>(clientOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/all")
    public ResponseEntity<?> retrieveAll() {
        List<NeurotechClient> neurotechClientList =  neurotechClientService.retrieveAll();

        if (!neurotechClientList.isEmpty()) {
            return new ResponseEntity<List<NeurotechClient>>(neurotechClientList, HttpStatus.OK);
        }

        return new ResponseEntity<>("No clients registered", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") String id
    ) {
        Optional<NeurotechClient> neurotechClient = neurotechClientService.delete(id);

        if (neurotechClient.isPresent()) {
            return new ResponseEntity<>(neurotechClient, HttpStatus.OK);
        }

        return new ResponseEntity<>("Error deleting user " + id, HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") String id,
            @RequestBody NeurotechClient neurotechClient
    ) {
        Optional<NeurotechClient> neurotechClientOptional = neurotechClientService.update(id, neurotechClient);

        if (neurotechClientOptional.isPresent()) {
            return new ResponseEntity<>(neurotechClientOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Error updating the client " + id, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/credit")
    public ResponseEntity<?> isCreditAvailable(
            @RequestParam("clientId") String clientId
    ) {
        Optional<NeurotechClient> clientOptional = neurotechClientService.retrieve(clientId);

        if (clientOptional.isPresent()) {
            NeurotechClient client = clientOptional.get();
            NeurotechClientCredit clientCredit = new NeurotechClientCredit(client, new ArrayList<>());

            if (client.getIncome() >= 5000 && client.getIncome() <= 15000) {
                clientCredit.getAvailableCredit().add(VehicleModel.HATCH);
            }

            if (client.getIncome() > 8000 && client.getAge() > 20) {
                clientCredit.getAvailableCredit().add(VehicleModel.SUV);
            }

            return new ResponseEntity<>(clientCredit, HttpStatus.OK);
        }

        return new ResponseEntity<>("Could not find client " + clientId, HttpStatus.NOT_FOUND);
    }

    @GetMapping("special-credit")
    public ResponseEntity<?> getAllSpecialClients() {
        List<NeurotechClient> specialClients = neurotechClientService.getAllSpecialClients();
        SpecialNeurotechClientCredit specialNeurotechClientCredit = new SpecialNeurotechClientCredit(specialClients);
        return new ResponseEntity<>(specialNeurotechClientCredit, HttpStatus.OK);
    }
}
