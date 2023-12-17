package pt.iade.joaquimclaudio.server.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/greeter")
public class GreeterController {
    private final Logger logger = LoggerFactory.getLogger(GreeterController.class);

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getGreeting(){
        logger.info("Saying hello to the world.");
        return "Hello World";
    }

    @GetMapping(path = "{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getNamedGreeting(@PathVariable("name") String name){
        logger.info("Saying hello to "+name+".");
        return "Hello, " + name;
    }
}
