package com.fstg.mediatech.controllers;

import com.fstg.mediatech.dto.ClientReponseDto;
import com.fstg.mediatech.dto.ClientRequestDto;
import com.fstg.mediatech.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String topic;
    @Autowired
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping("")
    public ResponseEntity<List<ClientReponseDto>>  getClients(){
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK) ;
    }
    @PostMapping("")
    public ResponseEntity<ClientReponseDto> save(@Valid @RequestBody ClientRequestDto clientRequestDto){
        ClientReponseDto clientReponseDto = clientService.save(clientRequestDto);
        kafkaTemplate.send(topic, clientReponseDto.toString());
        return new ResponseEntity<>(clientReponseDto, HttpStatus.CREATED);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<ClientReponseDto> findById(@PathVariable("id") Integer id) {
        ClientReponseDto clientReponseDto =  clientService.findById(id);
        return  ResponseEntity.ok(clientReponseDto);
    }
    @GetMapping("/nom/{nom}")
    public ResponseEntity<ClientReponseDto> findByNom(@PathVariable("nom") String nom) {
        ClientReponseDto clientReponseDto = clientService.findByNom(nom);
        return ResponseEntity.ok(clientReponseDto);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<ClientReponseDto> update(@Valid @RequestBody() ClientRequestDto clientRequestDto, @PathVariable Integer id) {
        ClientReponseDto clientReponseDto = clientService.update(clientRequestDto, id);
        return ResponseEntity.accepted().body(clientReponseDto);
    }
}
