package runner;

import com.automatedtest.core.runner.AfterSuite;
import com.automatedtest.core.runner.BeforeSuite;
import cucumber.api.junit.Cucumber;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class CustomTestRunner extends Runner {

    private Class clazz;
    private Cucumber cucumber;

    public CustomTestRunner(Class clasValue) throws Exception {
        clazz = clasValue;
        cucumber = new Cucumber(clasValue);
    }

    @Override
    public Description getDescription() {
        return cucumber.getDescription();
    }

    private void runPredefinedMethods(Class annotation) throws Exception {
        if (!annotation.isAnnotation()) {
            return;
        }
        Method[] methodList = this.clazz.getMethods();
        for (Method method : methodList) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation item : annotations) {
                if (item.annotationType().equals(annotation)) {
                    method.invoke(null);
                    break;
                }
            }
        }
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            runPredefinedMethods(BeforeSuite.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cucumber.run(notifier);
        try {
            runPredefinedMethods(AfterSuite.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
