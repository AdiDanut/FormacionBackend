package com.example.block16springcloud.repository;

import com.example.block16springcloud.entity.ViajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeRepository extends JpaRepository<ViajeEntity, Long> {
}

