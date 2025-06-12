package com.hiranwj.salonsync.repository;

import com.hiranwj.salonsync.model.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StylistRepository extends JpaRepository<Stylist, Integer> {
    Optional<Stylist> findByEmail(String email);
}
