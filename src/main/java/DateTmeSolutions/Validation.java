package DateTmeSolutions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
  private static final Pattern timePattern =
      Pattern.compile("^([0-1]?[0-9]|2[0-3])::[0-5][0-9]::[0-5][0-9]$");
  private static final Pattern datePattern =
      Pattern.compile("(0[1-9]|[1-2][0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.[0-9]{4}");

  public static boolean timeCheck(String time) {
    Matcher timeMatcher = timePattern.matcher(time);
    return timeMatcher.find();
  }

  public static boolean dateCheck(String date) {
    Matcher dateMatcher = datePattern.matcher(date);
    return dateMatcher.find();
  }
}
