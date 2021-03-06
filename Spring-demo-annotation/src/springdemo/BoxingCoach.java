package springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class BoxingCoach implements Coach {

    private FortuneService fortuneService;

        public BoxingCoach(FortuneService fortuneService) {

        System.out.println(">>BoxingCoach: inside default constructor");

        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Do Jab drills";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
