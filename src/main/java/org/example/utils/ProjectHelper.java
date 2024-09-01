package org.example.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class ProjectHelper {

    public void merge(Object obj1,Object obj2) throws IllegalArgumentException, IllegalAccessException {
        if (obj1 == null || obj2 == null) {
            throw new IllegalArgumentException("Neither of the objects can be null");
        }

        Class<?> clazz = obj1.getClass();
        if (!clazz.equals(obj2.getClass())) {
            throw new IllegalArgumentException("Objects must be of the same class");
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            Object valueFromObj2 = field.get(obj2);
            if (valueFromObj2 != null) {
                field.set(obj1, valueFromObj2);
            }
        }
    }
}
