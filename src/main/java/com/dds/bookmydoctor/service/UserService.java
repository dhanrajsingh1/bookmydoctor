package com.dds.bookmydoctor.service;

import com.dds.bookmydoctor.entity.User;
import com.dds.bookmydoctor.repo.UserRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  public User createUser(User user){
    return userRepo.save(user);
  }

  public User getUser(Integer id){
    Optional<User> user = userRepo.findById(id);
    if(user.isPresent()){
      return user.get();
    }

    throw new RuntimeException("User not found");
  }

  public List<User> getUserList(){
    return userRepo.findAll();
  }

}
