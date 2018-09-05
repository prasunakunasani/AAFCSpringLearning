package springdemo;

public class WrestlingCoach implements Coach {

    private FortuneService fortuneService;

    public WrestlingCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "No Wrestling. Your university has no funding";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
