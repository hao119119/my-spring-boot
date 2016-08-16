package com.example.myproject.domain;
import org.springframework.boot.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;

@Component
public class MyBean  implements CommandLineRunner{

    //@Value("${name}")
    private String name;

    @Autowired
    public MyBean(ApplicationArguments args) {
        System.out.println(name);
        boolean debug = args.containsOption("debug");
        System.out.print(debug);
        List<String> files = args.getNonOptionArgs();
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("run once");

    }
}

