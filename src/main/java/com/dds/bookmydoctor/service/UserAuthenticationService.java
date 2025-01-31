package com.dds.bookmydoctor.service;

import com.dds.bookmydoctor.entity.User;
import com.dds.bookmydoctor.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

  private final UserRepo userRepo;

  @Autowired
  public UserAuthenticationService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public boolean isAuthenticated = false;

  public String authenticate(String email, String password){
    User user = userRepo.findByEmail(email);
    if(user!=null){
      if(user.getPassword().equals(password)){
        if(!isAuthenticated){
          isAuthenticated =true;
          return "Logged in successfully.";
        }
        return "Already logged in";
      }
    }

    return "Login failed.";
  }

  public String deAuthenticate() {
    if(isAuthenticated){
      isAuthenticated =false;
      return "Logged out successfully.";
    }

    return "Already logged out";
  }
}
