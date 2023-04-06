package com.piradrian.clientinvoice.validation;

import com.piradrian.clientinvoice.model.ClientModel;
import com.piradrian.clientinvoice.repository.ClientRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientValidation {

    private ClientRepository clientRepository;

    public void createValidation(ClientModel client) throws Exception {
        Optional<ClientModel> optionalClient = clientRepository.findById(client.getId());

        hasInvalidValues(client);

        if (optionalClient.isPresent()){
            throw new Exception("El cliente ya existe.");
        }
    }

    public void updateValidation(ClientModel client) throws Exception {
        Optional<ClientModel> optionalClient = clientRepository.findById(client.getId());

        hasInvalidValues(client);

        if (client.getId() <= 0){
            throw new Exception("El id no es válido");
        }
        if(optionalClient.isEmpty()){
            throw new Exception("El cliente que intenta modificar no existe");
        }

    }

    public void findByIdValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    public void deleteValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    public static void hasInvalidValues (ClientModel client) throws Exception {
        if (client.getName() == null || client.getName().isEmpty()) {
            throw new Exception("El nombre del cliente no puede estar vacío.");
        }
        if (client.getLastname() == null || client.getLastname().isEmpty()) {
            throw new Exception("El apellido del cliente no puede estar vacío.");
        }
        if (client.getDocnumber() == null || client.getDocnumber().isEmpty()) {
            throw new Exception("El número de documento del cliente no puede estar vacío.");
        }
    }

}
