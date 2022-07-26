package DateTmeSolutions.DateTimeExceptions.NonMatchingStringFormatException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeWrongFormatException extends RuntimeException {

  public DateTimeWrongFormatException(String message) {
    super(message);
  }
}
