package springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PracticeActivity7DemoApp {

    public static void main(String[] args) {

        //Read spring java config class
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PracticeSportConfig.class);

        //Retrieve coach bean through container
        Coach theCoach = applicationContext.getBean("wrestlingCoach",Coach.class);

        //use coach to display workout and fortune information
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyFortune());

        //closing the context
        applicationContext.close();
    }
}
