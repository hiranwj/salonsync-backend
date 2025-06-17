package com.hiranwj.salonsync.repository;

import com.hiranwj.salonsync.model.StylistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StylistScheduleRepository extends JpaRepository<StylistSchedule, Integer> {

    List<StylistSchedule> findByStylistId(Integer stylistId);
}
