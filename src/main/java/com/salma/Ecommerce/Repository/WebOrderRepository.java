package com.salma.Ecommerce.Repository;

import com.salma.Ecommerce.Entity.User;
import com.salma.Ecommerce.Entity.WebOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebOrderRepository extends JpaRepository<WebOrder, Long> {

    List<WebOrder>findByUser(User user);
}
