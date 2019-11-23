package example.junit5;

import java.util.stream.Stream;

public class FirstImpression {
  public static boolean isOdd(int number) {
    return number % 2 != 0;
  }

  static Stream<String> blankStrings() {
    return Stream.of(null, "", "  ");
  }

}
