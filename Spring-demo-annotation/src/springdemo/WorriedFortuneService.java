package springdemo;

public class WorriedFortuneService implements FortuneService {
    @Override
    public String getFortune() {
        return "What if it rains?";
    }
}
