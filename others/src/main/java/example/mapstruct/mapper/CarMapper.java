package example.mapstruct.mapper;

import example.mapstruct.model.Car;
import example.mapstruct.model.CarDto;
import example.mapstruct.model.Person;
import example.mapstruct.model.PersonDto;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

@Mapper
public interface CarMapper {
  CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

  @Mapping(target = "personDto", source = "person")
  @Mapping(target = "manufacturingDate", source = "manufacturingDate", dateFormat = "dd.MM.yyyy")
  @Mapping(target = "manufacturingString", source = "manufacturingString", dateFormat = "dd.MM.yyyy")
  @Mapping(target = "stringPower", source = "doublePower", numberFormat = "#.##E0")
  @Mapping(target = "manufacturer", source = "make")
  @Mapping(target = "seatCount", source = "numberOfSeats")
  CarDto carToCarDto(Car car);

  @Mapping(source = "name", target = "fullName")
  PersonDto personToPersonDto(Person person);

  @IterableMapping(dateFormat = "dd.MM.yyyy")
  List<String> stringListToDateList(List<Date> dates);
}
