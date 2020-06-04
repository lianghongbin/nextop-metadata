package com.nextop.metadata;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
@Aspect
@Component
public class RepositoryAspect {

    @Pointcut("execution(public * com.nextop.metadata.repository.*.*(..))")
    public void point() {
    }

    @Before(value = "point()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException { System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        //Class<?> clazz = (Class<?>) ((ParameterizedType)joinPoint.getSignature().getDeclaringType().getGenericInterfaces()[0]).getActualTypeArguments()[0];
        //boolean isMetadata = AnnotationParser.isMetadata(clazz);


        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());
        System.out.println("do before!");
    }

    @After(value = "point()")
    public void doAfter(JoinPoint joinPoint) {
        System.out.println("do after!");
    }

    @AfterReturning(value = "point()", returning = "ans")
    public void doAfterReturning(JoinPoint joinPoint, String ans) {
        System.out.println("do after return: " + ans);
    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            System.out.println("do in around before");
            return joinPoint.proceed();
        } finally {
            System.out.println("do in around over!");
        }
    }
}
