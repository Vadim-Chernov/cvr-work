package cvr.otus.data;

import cvr.otus.data.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class DataLoader {
    @Value("${questions}")
    private String questions;
    @Value("${students}")
    private String students;
    @Bean
    Data data(){
        Data data = new Data(students, questions);
        return data;
    }
}