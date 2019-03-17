package cvr.otus.aspects;

import cvr.otus.domain.Student;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Order(0)
public class AccessAspect {

    public AccessAspect() {
        System.err.println(" ****************** " + new Date());
        System.err.println("|Start LoggerAspect|");
        System.err.println(" ================== ");

    }

    @Around(value = "execution(* cvr.otus.service.ExamRunner.run(.. )) && args(student)")
    public Exception around(ProceedingJoinPoint joinPoint, Student student) {
        String role = student.getRole();
        if (!"student".equals(role)) {
            String message = "Доступ пользователю [" + student.getName() + "] с ролью[" + role + "] запрещен";

            System.err.println(message);
            return new Exception(message);
        }
        try {
            joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable err) {
            err.printStackTrace();
        }
        return null;
    }

}
