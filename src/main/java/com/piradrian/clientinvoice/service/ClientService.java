package com.piradrian.clientinvoice.service;

import com.piradrian.clientinvoice.model.ClientModel;
import com.piradrian.clientinvoice.repository.ClientRepository;
import com.piradrian.clientinvoice.validation.ClientValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientValidation clientValdation;
    @Autowired
    private ClientRepository clientRepository;

    public ClientModel create(ClientModel client) throws Exception {
        clientValdation.createValidation(client);
        return clientRepository.save(client);
    }

    public ClientModel update(ClientModel client) throws Exception{
        clientValdation.updateValidation(client);
        return clientRepository.save(client);
    }

    public ClientModel findById(Long id) {
        Optional<ClientModel> optionalClient = clientRepository.findById(id)/*.orElseThrow()*/;
        return optionalClient.orElse(null);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
