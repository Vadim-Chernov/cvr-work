package cvr.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication

public class Main{
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
//        Runner runner = context.getBean(Runner.class);
//        runner.run();
//        SpringApplication application = new SpringApplication(Main.class);
//        application.run(args);
    }

}
