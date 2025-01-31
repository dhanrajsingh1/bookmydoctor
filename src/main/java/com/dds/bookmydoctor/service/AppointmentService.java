package com.dds.bookmydoctor.service;

import com.dds.bookmydoctor.entity.Appointment;
import com.dds.bookmydoctor.entity.User;
import com.dds.bookmydoctor.repo.AppointmentRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
  @Autowired
  private AppointmentRepo appointmentRepo;

  @Autowired
  private UserService userService;

  @Autowired
  private DoctorService doctorService;

  public String createAppointment(Integer userId, Integer doctorId, String date){
    Appointment appointment = new Appointment();
    appointment.setUser(userService.getUser(userId));
    appointment.setDoctor(doctorService.getDoctor(doctorId));
    appointment.setAppointmentDate(date);

    try {
      Appointment savedAppointment = appointmentRepo.save(appointment);
      return "Appoint saved successfully with ID: " + savedAppointment.getId();
    } catch (Exception e) {
      return "Failed to save appointment due to an unexpected error: " + e.getMessage();
    }

  }

  public List<Appointment> appointmentList(Integer userId){
    User user = userService.getUser(userId);
    return appointmentRepo.findByUser(user);
  }
}
