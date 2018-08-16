package springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PessimisticFortuneService implements FortuneService {


    // - Your fortune service should read the fortunes from a file.
    //Create a properties file with fortunes in them

    //    - The fortune service should load the fortunes into an array
    //load the fortunes from the properties file into a array variable

    @Value("${foo.fortune}")
    String[] data;

    private Random myRandom = new Random();

    @Override
    public String getFortune() {

        //    - When the getFortune() method is called it would return a random fortune from the array.

        //pick a random string from the array
        int index = myRandom.nextInt(data.length);

        //grab the string from array
        String theFortune = data[index];

        return theFortune;

    }
}
