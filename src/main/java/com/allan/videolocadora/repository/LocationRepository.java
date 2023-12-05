package com.allan.videolocadora.repository;

import com.allan.videolocadora.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    
}
