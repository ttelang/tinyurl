package encoder.src.main.java.learnbay.tinyurl.encoder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/link")
public class TinyURlController {
    @GetMapping("/{url}")
    public Link getTinyURL() {
       Link link = TinyURLService.getShortURL();
       log.info("Generating tiny url: {}", link);
       return link;
    }

}