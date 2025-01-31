package com.dds.bookmydoctor.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDto {
  private Integer userId;
  private Integer doctorId;
  private String appointmentDate;
}
