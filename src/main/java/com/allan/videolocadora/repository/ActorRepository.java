package com.allan.videolocadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allan.videolocadora.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
    
}
