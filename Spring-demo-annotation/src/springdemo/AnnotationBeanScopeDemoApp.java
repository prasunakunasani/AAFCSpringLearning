package springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {

    public static void main(String[] args) {
        //load the Spring config file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //retrieve beans from Spring container
        Coach theCoach = context.getBean("boxingCoach", Coach.class);

        Coach alphaCoach = context.getBean("boxingCoach",Coach.class);

        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyFortune());

        boolean result = theCoach == alphaCoach;

        System.out.println("Pointing to the same object: " + result);

        System.out.println("Memory location for theCoach: " + theCoach);

        System.out.println("Memory location for alphaCoach: " + alphaCoach);

        context.close();

    }
}
