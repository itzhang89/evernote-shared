package example.mapstruct.mapper;

import example.mapstruct.model.Car;
import example.mapstruct.model.CarDto;
import example.mapstruct.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

class CarMapperTest {
  Car.CarBuilder carBuilder;
  Person.PersonBuilder personBuilder;

  @BeforeEach
  void setUp() {
    carBuilder = Car.builder();
    personBuilder = Person.builder();
  }

  @Test
  void shouldBasicMappingFromSourceMakeToManufacturer() {
    carBuilder.make("2342");
    CarDto carDto = CarMapper.INSTANCE.carToCarDto(carBuilder.build());

    assertEquals("2342", carDto.getManufacturer());
  }

  @Test
  void shouldBasicMappingFromSourceNumberOfSeatsToSeatCount() {
    carBuilder.numberOfSeats("2342");
    CarDto carDto = CarMapper.INSTANCE.carToCarDto(carBuilder.build());

    assertEquals(2342, carDto.getSeatCount());
  }

  @Test
  void shouldUseCustomMethodMappingFromPersonToPersonDto() {
    carBuilder.person(personBuilder.name("custom method map").build());
    CarDto carDto = CarMapper.INSTANCE.carToCarDto(carBuilder.build());

    assertEquals("custom method map", carDto.getPersonDto().getFullName());
  }

  @Test
  void shouldConvertDateToStringFromSourceDateToTargetStringWithDateFormat() {
    LocalDate localDate = LocalDate.of(2019, 7, 23);

    carBuilder.manufacturingDate(localDate);
    CarDto carDto = CarMapper.INSTANCE.carToCarDto(carBuilder.build());

    assertEquals("23.07.2019", carDto.getManufacturingDate());
  }

  @Test
  void shouldConvertStringToDateFromSourceStringToTargetDateWithDateFormat() {
    carBuilder.manufacturingString("23.07.2019");
    LocalDate manufacturingStringToDate = CarMapper.INSTANCE.carToCarDto(carBuilder.build())
        .getManufacturingString();

    assertEquals(2019, manufacturingStringToDate.getYear());
    assertEquals(7, manufacturingStringToDate.getMonthValue());
    assertEquals(23, manufacturingStringToDate.getDayOfMonth());

  }

  @Test
  void shouldConvertDoubleToStringFromSourceDoubleToTargetStringWithNumberFormat() {
    carBuilder.doublePower(102.23);
    String sPower = CarMapper.INSTANCE.carToCarDto(carBuilder.build())
        .getStringPower();

    assertEquals("1.02E2", sPower);
  }
}