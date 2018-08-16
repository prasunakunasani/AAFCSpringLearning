package springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

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
