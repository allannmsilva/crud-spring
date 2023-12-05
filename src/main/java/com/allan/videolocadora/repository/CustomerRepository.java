package com.allan.videolocadora.repository;

import com.allan.videolocadora.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
