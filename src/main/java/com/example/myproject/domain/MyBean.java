package com.example.myproject.domain;
import org.springframework.boot.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.List;

@Component
public class MyBean  implements CommandLineRunner, ExitCodeGenerator{

    @Value("${name}")
    private String name;

    @Value("${my_name}")
    private String myName;

    @Value("${agent.driver}")
    private String driver;

    @Autowired
    public MyBean(ApplicationArguments args) {
        System.out.println(name);
        boolean debug = args.containsOption("debug");
        System.out.println(debug);
        List<String> files = args.getNonOptionArgs();
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(name);
        System.out.println(myName);
        System.out.println(driver);
        System.out.println("run once");


    }

    @Override
    public int getExitCode() {
        System.out.print("exit test");
        return 0;
    }
}

