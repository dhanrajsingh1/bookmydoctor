package com.dds.bookmydoctor.controller;

import com.dds.bookmydoctor.dto.AppointmentDto;
import com.dds.bookmydoctor.dto.DoctorSearchCriteria;
import com.dds.bookmydoctor.entity.Doctor;
import com.dds.bookmydoctor.entity.User;
import com.dds.bookmydoctor.service.AppointmentService;
import com.dds.bookmydoctor.service.DoctorService;
import com.dds.bookmydoctor.service.UserAuthenticationService;
import com.dds.bookmydoctor.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserAuthenticationService userAuthenticationService;

  @Autowired
  private UserService userService;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private AppointmentService appointmentService;

  @GetMapping("/login")
  public ResponseEntity<String> login(
      @RequestParam("email") String email,
      @RequestParam("password") String password){
    String authenticated = userAuthenticationService.authenticate(email, password);

    return ResponseEntity.ok(authenticated);
  }

  @GetMapping("/logout")
  public ResponseEntity<String> logout(){
    String authenticated = userAuthenticationService.deAuthenticate();

    return ResponseEntity.ok(authenticated);
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@RequestBody User user){
    boolean isAuthenticated = userAuthenticationService.isAuthenticated;
    User savedUser = userService.createUser(user);
    return  ResponseEntity.ok(savedUser);
  }

  @GetMapping("/doctors")
  public ResponseEntity<?> doctorsList(){
    if(userAuthenticationService.isAuthenticated){
      return ResponseEntity.ok(doctorService.getDoctorList());
    }

    return ResponseEntity.ok("Login first please");
  }

  @GetMapping("/doctors/search")
  public ResponseEntity<?> searchDoctors(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String specialization){

    if(userAuthenticationService.isAuthenticated){
      DoctorSearchCriteria searchCriteria = new DoctorSearchCriteria();
      searchCriteria.setName(name);
      searchCriteria.setSpecialization(specialization);

      List<Doctor> searchedDotors = doctorService.searchDoctors(searchCriteria);
      return ResponseEntity.ok(searchedDotors);
    }

    return ResponseEntity.ok("Login first please");
  }

  @PostMapping("/appointment")
  public ResponseEntity<?> bookAppointment(@RequestBody AppointmentDto appointmentDto){

    if(userAuthenticationService.isAuthenticated){
      return ResponseEntity.ok(appointmentService.createAppointment(
          appointmentDto.getUserId(),
          appointmentDto.getDoctorId(),
          appointmentDto.getAppointmentDate()));
    }

    return ResponseEntity.ok("Login first please");
  }

  @GetMapping("/appointments/{userId}")
  public ResponseEntity<?> getAppointments(
      @PathVariable Integer userId){

    if(userAuthenticationService.isAuthenticated){
      return ResponseEntity.ok(appointmentService.appointmentList(userId));
    }

    return ResponseEntity.ok("Login first please");
  }
}
