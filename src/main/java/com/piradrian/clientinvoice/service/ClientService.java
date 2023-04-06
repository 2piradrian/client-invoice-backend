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
    private ClientValidation clientValidation;
    @Autowired
    private ClientRepository clientRepository;

    public ClientModel create(ClientModel client) throws Exception {
        clientValidation.createValidation(client);
        return clientRepository.save(client);
    }

    public ClientModel update(ClientModel client) throws Exception{
        clientValidation.updateValidation(client);
        return clientRepository.save(client);
    }

    public ClientModel findById(Long id) throws Exception {
        clientValidation.findByIdValidation(id);
        return clientRepository.findById(id).orElse(null);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public void delete(Long id) throws Exception {
        clientValidation.deleteValidation(id);
        clientRepository.deleteById(id);
    }
}
