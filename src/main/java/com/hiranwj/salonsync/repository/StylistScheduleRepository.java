package com.hiranwj.salonsync.repository;

import com.hiranwj.salonsync.model.StylistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StylistScheduleRepository extends JpaRepository<StylistSchedule, Integer> {
}
