package com.jacksondouglas.ordering.repository;

import com.jacksondouglas.ordering.domain.PurchaseItem;
import com.jacksondouglas.ordering.domain.PurchaseItemPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, PurchaseItemPk> {
}
