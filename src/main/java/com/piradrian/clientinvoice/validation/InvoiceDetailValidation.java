package com.piradrian.clientinvoice.validation;

import com.piradrian.clientinvoice.model.InvoiceDetailModel;
import com.piradrian.clientinvoice.repository.InvoiceDetailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class InvoiceDetailValidation {
    private final InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetailValidation(InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    public void createValidation(InvoiceDetailModel invoiceDetail) throws Exception {
        hasInvalidValues(invoiceDetail);
    }

    public void updateValidation(InvoiceDetailModel invoiceDetail) throws Exception {
        hasInvalidValues(invoiceDetail);

        Optional<InvoiceDetailModel> optionalInvoiceDetail = invoiceDetailRepository.findById(invoiceDetail.getId());

        if (invoiceDetail.getId() <= 0){
            throw new Exception("El id no es válido");
        }
        if(optionalInvoiceDetail.isEmpty()){
            throw new Exception("El detalle de factura que intenta modificar no existe");
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

    public static void hasInvalidValues (InvoiceDetailModel invoiceDetail) throws Exception {

        if (invoiceDetail.getAmount() <= 0) {
            throw new Exception("La cantidad es inválida.");
        }
        if(invoiceDetail.getPrice() != null){
            throw new Exception("El precio debe ser generado automáticamente.");
        }
        if(invoiceDetail.getProductModel() == null){
            throw new Exception("El producto es inválido.");
        }
        if (invoiceDetail.getInvoiceModel() == null){
            throw new Exception("La factura es inválida.");
        }

        // Verificar que el producto exista en la bbdd
        // Verificar que el cliente exista en la bbdd

    }

}
