package com.dds.bookmydoctor.service;

import com.dds.bookmydoctor.dto.DoctorSearchCriteria;
import com.dds.bookmydoctor.entity.Doctor;
import com.dds.bookmydoctor.repo.DoctorRepo;
import com.dds.bookmydoctor.specifications.DoctorSpecification;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
  
  @Autowired
  private DoctorRepo doctorRepo;

  public Doctor createDoctor(Doctor doctor){
    return doctorRepo.save(doctor);
  }

  public Doctor getDoctor(Integer id){
    Optional<Doctor> doctor = doctorRepo.findById(id);
    if(doctor.isPresent()){
      return doctor.get();
    }
    throw  new RuntimeException("Doctor not found");
  }

  public List<Doctor> getDoctorList(){
    return doctorRepo.findAll();
  }

  public List<Doctor> searchDoctors(DoctorSearchCriteria searchCriteria) {
    Specification<Doctor> specification = Specification.where(null);

    if (searchCriteria.getSpecialization() != null) {
      specification = specification.and(DoctorSpecification.hasSpecialization(searchCriteria.getSpecialization()));
    }
    if (searchCriteria.getName() != null) {
      specification = specification.and(DoctorSpecification.hasName(searchCriteria.getName()));
    }


    return doctorRepo.findAll(specification);
  }
}
