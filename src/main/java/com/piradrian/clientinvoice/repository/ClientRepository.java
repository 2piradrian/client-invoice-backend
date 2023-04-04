package com.piradrian.clientinvoice.repository;

import com.piradrian.clientinvoice.model.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientModel, Long> {
}
