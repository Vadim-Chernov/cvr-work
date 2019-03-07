package cvr.otus.aspects;

import cvr.otus.domain.Student;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AccessAspect {
    private final static Logger LOGGER = Logger.getLogger(AccessAspect.class);

    public AccessAspect() {
        log(" ================== ");
        log("|Start AccessAspect|");
        log(" ================== ");
    }

    @Around(value = "execution(* cvr.otus.service.ExamRunner.run(.. )) && args(student)")
    public Exception around(ProceedingJoinPoint joinPoint, Student student) {
        String params = Arrays.toString(joinPoint.getArgs());
        String name = joinPoint.getSignature().getName();
        log("running : " + name);
        log("params  : " + params);
        Object proceed = null;
        String role = student.getRole();
        if (!"student".equals( role )){
            proceed = "Доступ пользователю с ролью[" + role+"] запрещен";

            System.err.println(proceed);
            log("retVal  : " +proceed);
            return new Exception(proceed.toString());
        }
        try {
            proceed = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            log(throwable.getMessage());
        }
        log("retVal  : " +proceed);
        return null;
    }

    private void log(String logMessage) {
        LOGGER.info(logMessage);
    }
}
