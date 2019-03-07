package cvr.otus.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:file-path.properties")
public class DataLoader {

    private final String questions;

    private final String students;

    public DataLoader( @Value("${questions}") String questions, @Value("${students}")String students) {
        this.questions = questions;
        this.students = students;
    }

    @Bean
    Data data(){
        return new Data(students, questions);
    }
}