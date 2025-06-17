package com.hiranwj.salonsync.repository;

import com.hiranwj.salonsync.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

    List<Feedback> findByStylistId(Integer stylistId);

}
