package com.dds.bookmydoctor.repo;

import com.dds.bookmydoctor.entity.Appointment;
import com.dds.bookmydoctor.entity.Doctor;
import com.dds.bookmydoctor.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Integer> {
  List<Appointment> findByUser(User user);
  List<Appointment> findByDoctor(Doctor doctor);
  List<Appointment> findByUserAndDoctor(User user, Doctor doctor);
}
