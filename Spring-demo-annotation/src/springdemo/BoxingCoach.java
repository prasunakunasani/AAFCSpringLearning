package springdemo;

import org.springframework.stereotype.Component;

@Component
public class BoxingCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Do Jab drills";
    }
}
