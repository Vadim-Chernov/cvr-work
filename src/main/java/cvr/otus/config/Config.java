package cvr.otus.config;

import cvr.otus.dao.*;
import cvr.otus.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public StudentDao studentDao() {
        return new StudentDaoImpl();
    }

    @Bean
    public StudentService studentService(StudentDao studentDao) {
        return new StudentServiceImpl(studentDao);
    }

    @Bean
    public QuestionDao questionDao() {
        return new QuestionDaoImpl();
    }

    @Bean
    public QuestionService questionService(QuestionDao questionDao) {
        return new QuestionServiceImpl(questionDao);
    }
}