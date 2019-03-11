package cvr.otus.aspects;

import cvr.otus.domain.Student;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.apache.log4j.Logger;

import java.util.Arrays;

@Aspect
@Component
@Order(10)
public class LoggerAspect {
//    private final PrintService printService = new PrintServiceImpl();

    private final static Logger LOGGER = Logger.getLogger(LoggerAspect.class);

    public LoggerAspect() {
        log(" ================== ");
        log("|Start LoggerAspect|");
        log(" ================== ");
    }

    @AfterReturning(value = "execution(* cvr.otus.service.StudentService.*(..))", returning = "retVal")
    public void afterReturning(JoinPoint joinPoint, Object retVal) {
        String params = Arrays.toString(joinPoint.getArgs());
        log("running : " + joinPoint.getSignature().getName());
        log("params  : " + params);
        log("retVal  : " + retVal);
        log("-----------------------------------");
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

    @Around(value = "execution(Double cvr.otus.service.QuestionService.checkQuestion(int,String))")
    public void around(ProceedingJoinPoint joinPoint) {
        String params = Arrays.toString(joinPoint.getArgs());
        Object proceed = null;
        try {
            proceed = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            log(throwable.getMessage());
        }
        log("running : " + joinPoint.getSignature().getName());
        log("params  : " + params);
        log("retVal  : " + proceed);
        log("-----------------------------------");
    }

    private void log(String logMessage) {
        LOGGER.info(logMessage);
    }
}
