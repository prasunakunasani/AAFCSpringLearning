package springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PracticeSportConfig {

    @Bean
    public FortuneService worriedFortuneService(){
        return new WorriedFortuneService();
    }

    @Bean
    public Coach wrestlingCoach()
    {
        return new WrestlingCoach(worriedFortuneService());
    }
}
