package one.yate.spring.cloud.gateway.enhance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.cloud.netflix.ribbon.RibbonHttpResponse;

@Aspect
public class RibbonHttpResponseAspect {
    @Pointcut("execution(* org.springframework.cloud.netflix.zuul.filters.route.support.AbstractRibbonCommand.run(..))")
    public void discoverException() {
        System.out.println("test");
    }

    @Around("discoverException()")
    public Object proceAndSwitch(ProceedingJoinPoint joinPoint) {
        Object[] ps = joinPoint.getArgs();
        try {
            Object r = joinPoint.proceed(ps);
            if (r != null) {
                RibbonHttpResponse target = (RibbonHttpResponse) joinPoint.getTarget();

            }
            return r;
        } catch (Throwable throwable) {

        }
        return -1;
    }
}
