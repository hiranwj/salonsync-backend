package com.hiranwj.salonsync.repository;

import com.hiranwj.salonsync.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
