package lt.jrgames.dragonsofmugloar.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DragonsOfMugloarRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        System.out.println("Hello Mugloar");
    }
}
