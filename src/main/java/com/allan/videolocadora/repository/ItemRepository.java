package com.allan.videolocadora.repository;

import com.allan.videolocadora.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
