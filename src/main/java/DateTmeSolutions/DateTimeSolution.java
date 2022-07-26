package DateTmeSolutions;

import DateTmeSolutions.DateTimeExceptions.NonMatchingStringFormatException.DateTimeWrongFormatException;
import DateTmeSolutions.DateTimeExceptions.WrongInputException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DateTimeSolution {
  private static final Clock clock = Clock.systemUTC();
  // Задание 1
  public static List<LocalDate> getAllMonthDays() {

    List<LocalDate> allDays = new ArrayList<>();
    LocalDate dateCounter = LocalDate.now(clock).with(TemporalAdjusters.firstDayOfMonth());
    while (!dateCounter.equals(
        LocalDate.now(clock).with(TemporalAdjusters.firstDayOfNextMonth()))) {
      allDays.add(dateCounter);
      dateCounter = dateCounter.plusDays(1);
    }

    return allDays;
  }
  // Задание 2
  public static LocalDate getTokioTwoWeeksLater() {
    return LocalDate.now(ZoneId.of("Asia/Tokyo")).plusWeeks(2);
  }
  // Задание 3
  public static LocalDate dateAfterSomeWorkDays(
      int demandedDays, List<LocalDate> holidays, List<DayOfWeek> Weekends) {
    if (demandedDays <= 0) {
      throw new WrongInputException("demanded Days out of range");
    }
    LocalDate targetDate = LocalDate.now(clock);
    while (demandedDays > 0) {
      targetDate = targetDate.plusDays(1);
      if (holidays.contains(targetDate) || Weekends.contains(targetDate.getDayOfWeek())) {
        continue;
      }
      demandedDays--;
    }

    return targetDate;
  }
  // Задание 4
  public static Duration getWorkHoursBetweenTwoDates(
      LocalDateTime startDate,
      LocalDateTime endDate,
      double workTimePerDay,
      List<DayOfWeek> weekends) {
    if (endDate.getYear() < startDate.getYear()
        || (endDate.getYear() == startDate.getYear()
            && endDate.getDayOfYear() < startDate.getDayOfYear())
        || ((endDate.getYear() == startDate.getYear()
            && endDate.getDayOfYear() == startDate.getDayOfYear()
            && endDate.getHour() < startDate.getHour()))
        || ((endDate.getYear() == startDate.getYear())
            && (endDate.getDayOfYear() == startDate.getDayOfYear())
            && (endDate.getHour() == startDate.getHour())
            && (endDate.getMinute() < startDate.getMinute()))
        || ((endDate.getYear() == startDate.getYear())
            && (endDate.getDayOfYear() == startDate.getDayOfYear())
            && (endDate.getHour() == startDate.getHour())
            && (endDate.getMinute() == startDate.getMinute())
            && (endDate.getSecond() < startDate.getSecond()))) {
      throw new WrongInputException("start date bigger then end");
    }
    LocalDateTime dateEncount = startDate;
    Duration timeEncount = Duration.ofSeconds(0);

    while (!dateEncount.equals(endDate)) {
      dateEncount = dateEncount.plusSeconds(1);
      if (weekends.contains(dateEncount.getDayOfWeek())) {
        continue;
      }
      timeEncount = timeEncount.plusSeconds(1);
    }
    return timeEncount;
  }
  // Задание 5
  public static ZonedDateTime parseStringToDateTimeUTC(String stringToParse, String timezone) {
    String[] dateAndTimeSeparated = stringToParse.split(" ");
    if (Validation.dateCheck(dateAndTimeSeparated[0]) == false) {
      throw new DateTimeWrongFormatException("Non format date");
    }
    if (Validation.timeCheck(dateAndTimeSeparated[1]) == false) {
      throw new DateTimeWrongFormatException("Non format time");
    }
    String[] dateMeta = dateAndTimeSeparated[0].split("\\.");

    for (String metadata : dateMeta) {
      if (metadata.startsWith("0")) {
        metadata = String.valueOf(metadata.charAt(1));
      }
    }
    String[] timeMeta = dateAndTimeSeparated[1].split("::");

    for (String metadata : timeMeta) {
      if (metadata.startsWith("0")) {
        metadata = String.valueOf(metadata.charAt(1));
      }
    }

    LocalDateTime parsedDateTime =
        LocalDateTime.of(
            Integer.parseInt(dateMeta[2]),
            Integer.parseInt(dateMeta[1]),
            Integer.parseInt(dateMeta[0]),
            Integer.parseInt(timeMeta[0]),
            Integer.parseInt(timeMeta[1]),
            Integer.parseInt(timeMeta[2]));

    return parsedDateTime.atZone(ZoneId.of(timezone));
  }
  // Задание 6
  public static int currentTimeInAllTimezones() {
    Set<String> zoneIdSet = ZoneId.getAvailableZoneIds();

    for (String zone : zoneIdSet) {
      System.out.println(ZonedDateTime.of(LocalDateTime.now(clock), ZoneId.of(zone)));
    }
    return zoneIdSet.size();
  }
  // Задание 7
  public static void functionRuntimeMilis(Method method)
      throws InvocationTargetException, IllegalAccessException {
    Instant start = clock.instant();
    List<Objects> params = new ArrayList<>();
    System.out.println(method.invoke(params));
    Instant finish = clock.instant();
    long elapsed = Duration.between(start, finish).toMillis();
    System.out.println("Прошло времени, мс: " + elapsed);
  }

  public static void functionRuntimeWithInterface(SomeFunctionalIntarface method)
      throws InterruptedException {
    Instant start = clock.instant();
    method.somemethod();
    TimeUnit.SECONDS.sleep(1);

    Instant finish = clock.instant();
    System.out.format(
        "%dD, %02d:%02d:%02d.%04d \n",
        Duration.between(start, finish).toDays(),
        Duration.between(start, finish).toHours(),
        Duration.between(start, finish).toMinutes(),
        Duration.between(start, finish).getSeconds(),
        Duration.between(start, finish).toMillis());
  }
}
