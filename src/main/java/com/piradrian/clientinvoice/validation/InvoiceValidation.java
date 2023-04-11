package com.piradrian.clientinvoice.validation;

import com.piradrian.clientinvoice.model.InvoiceModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class InvoiceValidation {

    public void createValidation(InvoiceModel invoice) throws Exception {
        hasInvalidValues(invoice);
        if (invoice.getCreated_at() != null) {
            throw new Exception("La fecha de creación debe ser generada automáticamente.");
        }
    }

    public void updateValidation(InvoiceModel invoice, Optional<InvoiceModel> optionalInvoice) throws Exception {
        hasInvalidValues(invoice);

        if(optionalInvoice.isEmpty()){
            throw new Exception("El reporte que intentas actualizar no existe");
        }

        if (invoice.getClientModel() == null || invoice.getId() <= 0) {
            throw new Exception("El cliente es inválido.");
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

    public static void hasInvalidValues (InvoiceModel invoice) throws Exception {
        if(invoice.getClientModel() == null){
            throw new Exception("El cliente es inválido");
        }
        if (invoice.getTotal() <= 0) {
            throw new Exception("El total es inválido.");
        }
    }

}

