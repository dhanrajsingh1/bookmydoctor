package com.dds.bookmydoctor.repo;

import com.dds.bookmydoctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DoctorRepo extends JpaRepository<Doctor, Integer>, JpaSpecificationExecutor<Doctor> {
  Doctor findByEmail(String email);
}
