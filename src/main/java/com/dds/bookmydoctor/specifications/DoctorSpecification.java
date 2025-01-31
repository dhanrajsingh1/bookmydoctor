package com.dds.bookmydoctor.specifications;

import com.dds.bookmydoctor.entity.Doctor;
import org.springframework.data.jpa.domain.Specification;

public class DoctorSpecification {

  public static Specification<Doctor> hasName(String name) {
    return (root, query, builder) -> {
      if (name == null || name.isEmpty()) {
        return null;
      }
      return builder.like(root.get("name"), "%" + name + "%");
    };
  }

  public static Specification<Doctor> hasSpecialization(String specialization) {
    return (root, query, builder) -> {
      if (specialization == null || specialization.isEmpty()) {
        return null;
      }
      return builder.like(root.get("specialization"), "%" + specialization + "%");
    };
  }
}
