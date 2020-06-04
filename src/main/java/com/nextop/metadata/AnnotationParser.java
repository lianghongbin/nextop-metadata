package com.nextop.metadata;

import com.baomidou.mybatisplus.annotation.TableName;
import com.nextop.metadata.annonation.Classifier;
import com.nextop.metadata.annonation.Column;
import com.nextop.metadata.entity.Classification;
import com.nextop.metadata.entity.Field;

import java.util.List;

/**
 * Description:
 *
 * @author: eric.liang
 * @date: 6/1/20
 * @update:
 */
public class AnnotationParser {

    private Classification classification;
    private List<Field> columns;

    private AnnotationParser() {
        this.classification = new Classification();
    }

    public void parse(Object obj) {
        boolean hasAnnotation = obj.getClass().isAnnotationPresent(Classifier.class);

        if ( !hasAnnotation ) {
            return;
        }

        String tableName = obj.getClass().getName();

        Classifier classifierAnnotation = (Classifier) obj.getClass().getAnnotation(Classifier.class);
        hasAnnotation = obj.getClass().isAnnotationPresent(TableName.class);
        if (hasAnnotation) {
            TableName tableAnnotation = (TableName) obj.getClass().getAnnotation(TableName.class);
            tableName = tableAnnotation.value();
        }

        tableName = classifierAnnotation.name() == "" ? tableName : classifierAnnotation.name();

        classification.setName(tableName);

        hasAnnotation = obj.getClass().isAnnotationPresent(Column.class);
        if (!hasAnnotation) {
            return;
        }

        java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
        for (java.lang.reflect.Field field : fields ) {
            field.setAccessible(true);
            Column columnAnnotation = field.getAnnotation(Column.class); //获取指定类型注解
            if (columnAnnotation == null) {
                continue;
            }

            try {

                List<Field> columnList =  (List<Field>)field.get(obj);
                columns.addAll(columnList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isMetadata(Class clazz) {
        return clazz.isAnnotationPresent(Classifier.class);
    }
}
