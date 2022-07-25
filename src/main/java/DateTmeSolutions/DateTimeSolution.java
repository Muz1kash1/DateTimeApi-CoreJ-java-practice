package DateTmeSolutions;

import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class DateTimeSolution {

  private static final LocalDate firstDayOfMonth =
      LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());

  public static List<LocalDate> getAllMonthDays() {
    List<LocalDate> allDays = new ArrayList<>();
    LocalDate dateCounter = firstDayOfMonth;
    while (!dateCounter.equals(LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth()))) {
      allDays.add(dateCounter);
      dateCounter = dateCounter.plusDays(1);
    }

    return allDays;
  }

  public static LocalDate getTokioTwoWeeksLater() {
    return LocalDate.now(ZoneId.of("Asia/Tokyo")).plusWeeks(2);
  }

  public static LocalDate dateAfterSomeWorkDays(
      int demandedDays, List<LocalDate> holidays, List<DayOfWeek> Weekends) {
    LocalDate targetDate = LocalDate.now();
    while (demandedDays > 0) {
      targetDate = targetDate.plusDays(1);
      if (holidays.contains(targetDate) || Weekends.contains(targetDate.getDayOfWeek())) {
        continue;
      }
      demandedDays--;
    }

    return targetDate;
  }

  public static Duration getWorkHoursBetweenTwoDates(
      LocalDate startDate, LocalDate endDate,int workTimePerDay, List<DayOfWeek> weekends) {
    LocalDate dateEncount = startDate;
    Duration timeEncount = Duration.ZERO;

    while (!dateEncount.equals(endDate)) {
      dateEncount = dateEncount.plusDays(1);
      if (weekends.contains(dateEncount.getDayOfWeek())) {
        continue;
      }
      timeEncount = timeEncount.plusHours(workTimePerDay);
    }
    return timeEncount;
  }

  public static ZonedDateTime parseStringToDateTimeUTC(String stringToParse, String timezone) {
    String[] dateAndTimeSeparated = stringToParse.split(" ");
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

  public static void currentTimeInAllTimezones(){
    Set<String> zoneIdSet = ZoneId.getAvailableZoneIds();
    for (String zone : zoneIdSet){
      System.out.println(ZonedDateTime.of(LocalDateTime.now(),ZoneId.of(zone)));
    }
  }

  public static void functionRuntimeMilis(UnaryOperator<Object> somefunc){
    Instant start = Instant.now();
    somefunc.apply(somefunc);
    Instant finish = Instant.now();
    long elapsed = Duration.between(start, finish).toNanos();
    System.out.println("Прошло времени, нс: " + elapsed);

  }
  //Какая то простая функция для примера

}
