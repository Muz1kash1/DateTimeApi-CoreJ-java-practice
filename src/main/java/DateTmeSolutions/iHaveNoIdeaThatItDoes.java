package DateTmeSolutions;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class iHaveNoIdeaThatItDoes {
    public static void checkThisReflectionalShit(String args[]) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class[] parameterTypes = new Class[2];
        parameterTypes[0] = String.class;
        parameterTypes[1] = String.class;

        Method functionToPass = DateTimeSolution.class.getMethod("currentTimeInAllTimezones");
        DateTimeSolution dateTimeSolution = new DateTimeSolution();
        dateTimeSolution.functionRuntimeMilis(functionToPass);
    }

}
