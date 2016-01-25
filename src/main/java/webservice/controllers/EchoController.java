package webservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import webservice.EchoMessage;
@RestController
public class EchoController {

	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping("/echo")
    public EchoMessage echoMessage(@RequestParam(value="name", defaultValue="World") String name) {
        return new EchoMessage(counter.incrementAndGet(),
                            String.format(template, name));
    }
}