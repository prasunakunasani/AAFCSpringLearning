package springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    /*
    @Autowired
    public TennisCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }
*/

    public TennisCoach() {
        System.out.println(">> TennisCoach: inside default constructor");
    }


    //define an init method
    @PostConstruct
    public void doMyStartUpStuff(){
        System.out.println(">>TennisCoach: inside of doMyStartUpStuff");
    }

    //define an destroy method
    @PreDestroy
    public void doMyCleanUpStuff()
    {
        System.out.println(">>TennisCoach: inside of doMyCleanUpStuff");
    }

    /*
    //define a setter method
    @Autowired
    public void doSomeCrazyStuff(FortuneService fortuneService) {
        System.out.println(">>TennisCoach: inside doSomeCrazyStuff method");
        this.fortuneService = fortuneService;
    }
    */

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
