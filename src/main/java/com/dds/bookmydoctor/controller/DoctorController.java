package com.dds.bookmydoctor.controller;

import com.dds.bookmydoctor.entity.Doctor;
import com.dds.bookmydoctor.service.DoctorAuthenticationService;
import com.dds.bookmydoctor.service.DoctorService;
import com.dds.bookmydoctor.service.UserAuthenticationService;
import com.dds.bookmydoctor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

  @Autowired
  DoctorAuthenticationService doctorAuthenticationService;

  @Autowired
  UserAuthenticationService userAuthenticationService;

  @Autowired
  private DoctorService doctorService;

  @Autowired
  private UserService userService;

  @GetMapping("/users")
  public ResponseEntity<?> doctorsList(){
    if(doctorAuthenticationService.isAuthenticated){
      return ResponseEntity.ok(userService.getUserList());
    }

    return ResponseEntity.ok("Login first please");
  }


  @GetMapping("/login")
  public ResponseEntity<String> login(
      @RequestParam("email") String email,
      @RequestParam("password") String password){
    String authenticated = doctorAuthenticationService.authenticate(email, password);

    return ResponseEntity.ok(authenticated);
  }

  @PostMapping("/register")
  public ResponseEntity<Doctor> register(@RequestBody Doctor doctor){
    boolean isAuthenticated = doctorAuthenticationService.isAuthenticated;
    Doctor savedDoctor = doctorService.createDoctor(doctor);
    return  ResponseEntity.ok(savedDoctor);
  }

  @GetMapping("/list")
  public ResponseEntity<?> doctorList(){
    if(doctorAuthenticationService.isAuthenticated){
      return ResponseEntity.ok(doctorService.getDoctorList());
    }

    return ResponseEntity.ok("Login first please");
  }
}
