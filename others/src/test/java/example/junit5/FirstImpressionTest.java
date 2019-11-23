package example.junit5;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Month;
import java.util.EnumSet;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FirstImpressionTest {

  private static Stream<Arguments> provideStringsForIsBlank() {
    return Stream.of(
        Arguments.of(null, true),
        Arguments.of("", true),
        Arguments.of("  ", true),
        Arguments.of("not blank", false)
    );
  }

  private static Stream<String> howToUseTheMethodSourceAnnotationWhenUseMethodAnnotationGivenNoSpecialValueAttribute() {
    return Stream.of(null, "", "  ");
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    // six numbers
  void isOdd_ShouldReturnTrueForOddNumbers(int number) {
    assertTrue(FirstImpression.isOdd(number));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "  "})
  void isBlank_ShouldReturnTrueForNullOrBlankStrings(String input) {
    assertTrue(Strings.isBlank(input));
  }

  @ParameterizedTest
  @NullSource
  void isBlank_ShouldReturnTrueForNullInputs(String input) {
    assertTrue(Strings.isBlank(input));
  }

  @ParameterizedTest
  @EmptySource
  void isBlank_ShouldReturnTrueForEmptyStrings(String input) {
    assertTrue(Strings.isBlank(input));
  }

  @ParameterizedTest
  @NullAndEmptySource
  void isBlank_ShouldReturnTrueForNullAndEmptyStrings(String input) {
    assertTrue(Strings.isBlank(input));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"  ", "\t", "\n"})
  void isBlank_ShouldReturnTrueForAllTypesOfBlankStrings(String input) {
    assertTrue(Strings.isBlank(input));
  }

  @ParameterizedTest
  @EnumSource(Month.class)
    // passing all 12 months
  void howToIteratorTheEmumClassByEnumSourceAnnotation(Month month) {
    int monthNumber = month.getValue();
    assertTrue(monthNumber >= 1 && monthNumber <= 12);
  }

  @ParameterizedTest
  @EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
  void filterSomeEnumClassByNameAttributeInEnumSourceAnnotation(Month month) {
    final boolean isALeapYear = false;
    assertEquals(30, month.length(isALeapYear));
  }

  @ParameterizedTest
  @EnumSource(
      value = Month.class,
      names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
      mode = EnumSource.Mode.EXCLUDE)
  void exceptOtherEnumClassByNameInEnumSourceAnnotation(Month month) {
    final boolean isALeapYear = false;
    assertEquals(31, month.length(isALeapYear));
  }

  @ParameterizedTest
  @EnumSource(value = Month.class, names = ".+BER", mode = EnumSource.Mode.MATCH_ANY)
  void passARegularExpressionToLiteralStringsInEnumSourceAnnotation(Month month) {
    EnumSet<Month> months =
        EnumSet.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER);
    assertTrue(months.contains(month));
  }

  @ParameterizedTest
  @CsvSource({"test,TEST", "tEst,TEST", "Java,JAVA"})
  void useParametersOnceLikeCSVFileInCsvSourceAnnotation(String input, String expected) {
    String actualValue = input.toUpperCase();
    assertEquals(expected, actualValue);
  }

  @ParameterizedTest
  @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
  void separateParametersByOtherDelimiterAttributeInCsvSourceAnnotation(String input, String expected) {
    String actualValue = input.toLowerCase();
    assertEquals(expected, actualValue);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
  void toUpperCase_ShouldGenerateTheExpectedUppercaseValueCSVFile(String input, String expected) {
    String actualValue = input.toUpperCase();
    assertEquals(expected, actualValue);
  }

  @ParameterizedTest
  @MethodSource("provideStringsForIsBlank")
  void howToUseTheMethodSourceAnnotationWhenUseMethodAnnotationGivenValueAttributeIsStaticMethodInSameClass(String input, boolean expected) {
    assertEquals(expected, Strings.isBlank(input));
  }

  @ParameterizedTest
  @MethodSource
    // hmm, no method name ...
  void howToUseTheMethodSourceAnnotationWhenUseMethodAnnotationGivenNoSpecialValueAttribute(String input) {
    assertTrue(Strings.isBlank(input));
  }

//  @ParameterizedTest
//  @MethodSource("example.junit5.FirstImpression#blankStrings")
//  void howToUseTheMethodSourceAnnotationAWhenUseMethodAnnotationGivenFullyQualifiedNameMethodName(String input) {
//    assertTrue(Strings.isBlank(input));
//  }

}