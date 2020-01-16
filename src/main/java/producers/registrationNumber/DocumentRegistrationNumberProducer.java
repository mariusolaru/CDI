package producers.registrationNumber;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.util.Random;

@ApplicationScoped
public class DocumentRegistrationNumberProducer {

    @Produces
    @DocumentRegistrationNumber
    public Integer getRegistrationNumber() {
        return new Random().nextInt();
    }
}