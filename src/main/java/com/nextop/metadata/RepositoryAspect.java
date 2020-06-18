package com.nextop.metadata;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nextop.metadata.annonation.Classifier;
import com.nextop.metadata.annonation.Column;
import com.nextop.metadata.entity.Classification;
import com.nextop.metadata.entity.Field;
import com.nextop.metadata.repository.ClassificationRepository;
import com.nextop.metadata.repository.FieldRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    private IdWorker idWorker = new IdWorker();
    @Resource
    private ClassificationRepository classificationRepository;
    @Resource
    private FieldRepository fieldRepository;

    @Pointcut("execution(public * com.nextop.metadata.repository.*.*(..))")
    public void point() {
    }

    @Pointcut("execution(* com.nextop.metadata.repository..*.select*(..)) " +
            "|| execution(* com.nextop.metadata.repository..*.find*(..)))" +
            "|| execution(* com.nextop.metadata.repository..*.list*(..)))" +
            "|| execution(* com.nextop.metadata.repository..*.get*(..)))" +
            "|| execution(* com.nextop.metadata.repository..*.fetch*(..)))")
    public void read() {
    }

    @Pointcut("execution(* com.nextop.metadata.repository..*.insert*(..)) " +
            "|| execution(* com.nextop.metadata.repository..*.save*(..)))" +
            "|| execution(* com.nextop.metadata.repository..*.add*(..)))" +
            "|| execution(* com.nextop.metadata.repository..*.put*(..)))")
    public void create() {
    }

    @Pointcut("execution(* com.nextop.metadata.repository..*.update*(..)) " +
            "|| execution(* com.nextop.metadata.repository..*.modify*(..)))")
    public void update() {
    }

    @Pointcut("execution(* com.nextop.metadata.repository..*.delete*(..)) " +
            "|| execution(* com.nextop.metadata.repository..*.remove*(..)))")
    public void delete() {
    }

    @Before(value = "create()")
    public void doBefore(JoinPoint joinPoint) throws ClassNotFoundException {
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" + joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());

        System.out.println(joinPoint.getSignature().getDeclaringType().getInterfaces()[0].getGenericInterfaces()[0]);
        Type type;
        try {
            type = joinPoint.getSignature().getDeclaringType().getInterfaces()[0].getGenericInterfaces()[0];
            if (!(type instanceof ParameterizedType)) {
                return;
            }
        } catch (Exception exception) {
            return;
        }

        Class<?> clazz = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        if(!AnnotationParser.isMetadata(clazz)) {
            return;
        }

        //获取参数实例
        Object[] args = joinPoint.getArgs();
        if (args.length > 1) {
            return;
        }

        Object object = args[0];
        if(!AnnotationParser.isMetadata(object.getClass())) {
            return;
        }

        TableName tableName = object.getClass().getAnnotation(TableName.class);
        String name = "".equals(tableName.value()) ? object.getClass().getName() : tableName.value();

        //获取classification
        Classifier classifier = object.getClass().getAnnotation(Classifier.class);
        name = "".equals(classifier.name()) ? name : classifier.name();

        Classification classification = new Classification();
        classification.setName(name);
        classification.setComment(name);
        classification.setCreateTime(Calendar.getInstance().getTimeInMillis());
        Long id =idWorker.nextId();
        classification.setId(id);

        classificationRepository.save(classification);

        List<Field> fieldList = new ArrayList<>();

        java.lang.reflect.Field[] fields = object.getClass().getDeclaredFields();
        for (java.lang.reflect.Field field : fields ) {
            field.setAccessible(true);
            Column columnAnnotation = field.getAnnotation(Column.class); //获取指定类型注解
            if (columnAnnotation == null) {
                continue;
            }

            try {

                List<Field> columnList =  (List<Field>)field.get(object);
                fieldList.addAll(columnList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        fieldList.forEach(item -> {
            item.setClassId(id);
        });

        fieldRepository.saveBatch(fieldList);
    }

    @After(value = "read()")
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
