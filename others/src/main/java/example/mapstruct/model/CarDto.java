package example.mapstruct.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CarDto {
  private String manufacturer;
  private int seatCount;
  private String stringPower;
  private String manufacturingDate;
  private LocalDate manufacturingString;
  private PersonDto personDto;
}
