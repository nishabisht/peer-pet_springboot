package com.springboot.rest.petpeers.repository;

import com.springboot.rest.petpeers.entity.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository  extends JpaRepository<PetEntity,Long> {
}
