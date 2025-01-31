package com.dds.bookmydoctor.service;

import com.dds.bookmydoctor.entity.Doctor;
import com.dds.bookmydoctor.repo.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorAuthenticationService {

  private final DoctorRepo doctorRepo;

  @Autowired
  public DoctorAuthenticationService(DoctorRepo doctorRepo) {
    this.doctorRepo = doctorRepo;
  }

  public boolean isAuthenticated = false;

  public String authenticate(String email, String password){
    Doctor doctor = doctorRepo.findByEmail(email);
    if(doctor!=null){
      if(doctor.getPassword().equals(password)){
        isAuthenticated =true;
        return "Logged in successfully.";
      }
    }

    return "Login failed.";
  }
}
