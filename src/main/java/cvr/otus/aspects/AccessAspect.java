package cvr.otus.aspects;

import cvr.otus.domain.Student;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Order(0)
public class AccessAspect {

    @Around(value = "execution(* cvr.otus.service.ExamRunner.run(.. )) && args(student)")
    public Exception around(ProceedingJoinPoint joinPoint, Student student) {
        String params = Arrays.toString(joinPoint.getArgs());
        String name = joinPoint.getSignature().getName();
        Object proceed;
        String role = student.getRole();
        if (!"student".equals( role )){
            proceed = "Доступ пользователю с ролью[" + role+"] запрещен";

            System.err.println(proceed);
            return new Exception(proceed.toString());
        }
        try {
            proceed = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable err) {
            err.printStackTrace();
        }
        return null;
    }

}
