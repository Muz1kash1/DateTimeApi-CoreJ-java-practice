package DateTmeSolutions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeBroker {
  public static void brokeInvocation()
      throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    Class[] parameterTypes = new Class[2];
    parameterTypes[0] = String.class;
    parameterTypes[1] = String.class;

    Method functionToPass = DateTimeSolution.class.getMethod("getTokioTwoWeeksLater");
    DateTimeSolution dateTimeSolution = new DateTimeSolution();
    dateTimeSolution.functionRuntimeMilis(functionToPass);
  }
}
