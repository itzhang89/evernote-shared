package example.mapstruct.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Car {
  private String make;
  private double price;
  private double doublePower;
  private LocalDate manufacturingDate;
  private String manufacturingString;
  private String numberOfSeats;

  private Person person;
}
