package com.piradrian.clientinvoice.validation;

import com.piradrian.clientinvoice.model.ClientModel;
import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.model.InvoiceModel;
import com.piradrian.clientinvoice.model.ProductModel;
import com.piradrian.clientinvoice.repository.ClientRepository;
import com.piradrian.clientinvoice.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvoiceValidation {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;


    public InvoiceValidation(ClientRepository clientRepository, ProductRepository productRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    public void createValidation(InvoiceDetailModel invoiceDetail, ClientModel client) throws Exception {

        Optional<ClientModel> paramClient = clientRepository.findById(client.getId());
        Optional<ProductModel> paramProduct = productRepository.findById(invoiceDetail.getProductModel().getId());

        if(paramClient.isEmpty()){
            throw new Exception("El cliente no existe");
        }
        if(paramProduct.isEmpty()){
            throw new Exception("El producto no existe");
        }
    }

    public void findByIdValidation(Long id) throws Exception {
        if(id <= 0){
            throw new Exception("El id no es válido");
        }
    }

    public static void hasInvalidValues (InvoiceModel invoice) throws Exception {
        if(invoice.getClientModel() == null){
            throw new Exception("El cliente es inválido");
        }
        if (invoice.getTotal() != null) {
            throw new Exception("El total debe ser generado automáticamente.");
        }
    }

}

