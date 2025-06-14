package com.hiranwj.salonsync.repository;

import com.hiranwj.salonsync.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    boolean existsByStylistIdAndAppointmentDateAndAppointmentTime(Integer stylistId, String appointmentDate, String appointmentTime);
}
