package DateTimeSolutionTesting;

import DateTmeSolutions.DateTimeExceptions.NonMatchingStringFormatException.DateTimeWrongFormatException;
import DateTmeSolutions.DateTimeExceptions.WrongInputException;
import DateTmeSolutions.DateTimeSolution;
import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateTimeSolutionTest {
  private final List<LocalDate> testList = DateTimeSolution.getAllMonthDays();
  List<LocalDate> holidays = new ArrayList<>();
  List<DayOfWeek> weekends = new ArrayList<>();
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
  private Clock clock = Clock.systemUTC();

  {
    holidays.add(LocalDate.parse("2022-12-31"));
    holidays.add(LocalDate.parse("2023-05-09"));
    holidays.add(LocalDate.parse("2023-03-08"));
    holidays.add(LocalDate.parse("2023-02-23"));

    weekends.add(DayOfWeek.SATURDAY);
    weekends.add(DayOfWeek.SUNDAY);
  }
  // Задание 1
  @Test
  public void getAllMonthDays_now_shouldReturnTrue() {
    LocalDate now = LocalDate.now(clock);
    Assert.assertEquals(now.lengthOfMonth(), testList.size());
  }
  // Задание 2
  @Test
  public void getTokioTwoWeeksLater_now_shouldReturnTrue() {
    LocalDate thoWeeksLater =
        LocalDate.from(LocalDate.now(clock).atStartOfDay(ZoneId.of("Asia/Tokyo"))).plusWeeks(2);
    Assert.assertEquals(thoWeeksLater, DateTimeSolution.getTokioTwoWeeksLater());
  }
  // Задание 3
  @Test
  public void dateAfterSomeWorkDays_minus7_shouldThrowWrongImputException() {
    Assert.assertThrows(
        WrongInputException.class,
        () -> DateTimeSolution.dateAfterSomeWorkDays(-7, holidays, weekends));
  }
  // Задание 4
  @Test
  public void getWorkHoursBetweenTwoDates_beginBiggerThanEnd_shouldThrowWrongImputException() {
    Assert.assertThrows(
        WrongInputException.class,
        () ->
            DateTimeSolution.getWorkHoursBetweenTwoDates(
                LocalDateTime.parse("2022-12-31 11:00:00", formatter),
                LocalDateTime.parse("2021-05-09 11:00:00", formatter),
                8,
                weekends));
  }

  @Test
  public void getWorkngHoursBetweenTwoDates_2022_12_31To2023_05_09_shouldReturn2649600() {
    Assert.assertEquals(
        DateTimeSolution.getWorkHoursBetweenTwoDates(
                LocalDateTime.parse("2022-12-31 10:40:30", formatter),
                LocalDateTime.parse("2023-12-31 10:40:31", formatter),
                8.0,
                weekends)
            .toSeconds(),
        22464000);
  }
  // Задание 5
  @Test
  public void parseStringToDateUTC_16_09_2016_11_46_01_Asia_Tokyo_shouldReturn_same() {
    Assert.assertEquals(
        DateTimeSolution.parseStringToDateTimeUTC("16.09.2016 11::46::01", "Asia/Tokyo"),
        LocalDateTime.parse("2016-09-16 11:46:01", formatter).atZone(ZoneId.of("Asia/Tokyo")));
  }

  @Test
  public void
      parseStringToDateUTC_jf_09_2016_11_46_01_Asia_Tokyo_shouldThrowDateTimeWrongFormatException() {
    Assert.assertThrows(
        DateTimeWrongFormatException.class,
        () -> DateTimeSolution.parseStringToDateTimeUTC("jf.09.2016 11::46::01", "Asia/Tokyo"));
  }
  // Задание 6
  @Test
  public void currentTimeInAllTimeZones_now_shouldReturn601() {
    Assert.assertEquals(DateTimeSolution.currentTimeInAllTimezones(), 601);
  }
  // Задание 7
  @Test
  public void functionRuntimeWithInterface_shouldPrintExecutionTime() throws InterruptedException {
    Instant start = clock.instant();
    DateTimeSolution.functionRuntimeWithInterface(() -> System.out.println("something to print"));
    Instant finish = clock.instant();
    long period = Duration.between(start, finish).toMillis();
    Assert.assertTrue(period >= 100);
  }
}
