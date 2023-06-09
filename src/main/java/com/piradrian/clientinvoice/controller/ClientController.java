package com.piradrian.clientinvoice.controller;

import com.piradrian.clientinvoice.model.ClientModel;
import com.piradrian.clientinvoice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping(path = "/")
    public ResponseEntity<ClientModel> createClient(@RequestBody ClientModel client) throws Exception {
        return new ResponseEntity<>(clientService.create(client), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClientModel> getClientById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @PutMapping(path = "/")
    public ResponseEntity<ClientModel> updateClient(@RequestBody ClientModel client) throws Exception {
        return new ResponseEntity<>(clientService.update(client), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ClientModel> deleteClient(@PathVariable Long id) throws Exception {
        clientService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/")
    public ResponseEntity<List<ClientModel>> getAllClients() {
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }
}
