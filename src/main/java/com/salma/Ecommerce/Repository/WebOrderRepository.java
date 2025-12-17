package com.salma.Ecommerce.Repository;

import com.salma.Ecommerce.Entity.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {
}
