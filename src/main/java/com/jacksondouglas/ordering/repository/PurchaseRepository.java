package com.jacksondouglas.ordering.repository;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.domain.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    @Transactional(readOnly = true)
    Page<Purchase> findByClient(Client client, Pageable pageRequest);
}
