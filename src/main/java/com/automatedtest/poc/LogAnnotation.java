package com.automatedtest.poc;

//import com.automatedtest.core.logger.BFLogger;

//import com.automatedtest.core.logger.BFLogger;

import com.automatedtest.core.logger.BFLogger;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Aspect
public class LogAnnotation {


    private static final Pattern  PATTERN = Pattern.compile("\\(([^)]+)\\)");

    @Before("execution(* *.*(..)) && @annotation(com.automatedtest.poc.LogMethod)")
    public void logMethodCall(JoinPoint joinPoint) {
        BFLogger.logDebug("Called " + joinPoint);
    }

    /**
     * The method allows automatic logging for @When and @Then cucumber annotations
     **/
    @Before("execution(* *.*(..)) && (@annotation(cucumber.api.java.en.Then) || @annotation(cucumber.api.java.en.When))")
    public void logStepDefinition(JoinPoint joinPoint) {
        try {
            Signature signature = joinPoint.getSignature();
            String methodName = signature.getName();
            String fullName = signature.toLongString();
            Matcher matcher = PATTERN.matcher(fullName);
            Method method = getMethod(signature, methodName, matcher);
            logEvent(joinPoint, method);

        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private Method getMethod(Signature signature, String methodName, Matcher matcher) throws ClassNotFoundException, NoSuchMethodException {
        Method method = null;
        boolean containsArguments = matcher.find();
        if (containsArguments) {
            String argsClassNames = matcher.group(1);
            Class[] classes = getClasses(argsClassNames);
            if (classes != null) {
                method = signature.getDeclaringType().getMethod(methodName, classes);
            }
        } else {
            method = signature.getDeclaringType().getMethod(methodName);

        }
        return method;
    }

    private void logEvent(JoinPoint joinPoint, Method method) {
        Object[] methodArgs = joinPoint.getArgs();

        When when = method.getAnnotation(When.class);
        if (when != null) {
            BFLogger.logDebug("WHEN " + replaceArgs(when.value(), methodArgs));
        }
        Then then = method.getAnnotation(Then.class);
        if (then != null) {
            BFLogger.logDebug("THEN " + replaceArgs(then.value(), methodArgs));
        }
    }

    private String replaceArgs(String string, Object[] args) {
        if (args != null || args.length > 0) {
            for (Object arg : args) {
                string = StringUtils.replaceOnce(string, ".*", arg.toString());
            }
        }
        return string;
    }

    private Class[] getClasses(String names) throws ClassNotFoundException {
        if (names == null || names.isEmpty()) {
            return null;
        } else if (names.contains(",")) {
            String[] classNames = names.split(",");
            Class[] classes = new Class[classNames.length];
            for (int i = 0; i < classes.length; i++) {
                classes[i] = Class.forName(classNames[i]);
            }
            return classes;
        } else {
            return new Class[]{Class.forName(names)};
        }
    }
}
