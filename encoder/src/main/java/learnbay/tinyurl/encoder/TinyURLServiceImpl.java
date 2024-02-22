package encoder.src.main.java.learnbay.tinyurl.encoder;

import java.util.Random;
import org.springframework.sterotype.Service;

@Service
public class TinyURLServiceImpl implements TinyURLService {
    private final Random random;

    TinyURLServiceImpl() {
        this.random = new Random();
    }

    @Override 
    public Link getShortURL() {
        return random;
    }
    
}
