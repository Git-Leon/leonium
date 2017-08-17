package com.zipcodewilmington.selenium.tools;

/**
 * Created by leon on 8/17/17.
 */

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Created by leon on 5/19/17.
 * @ATTENTION_TO_STUDENTS You are FORBIDDEN from modifying this class
 */
public class ReflectionUtils {
    /**
     * @param object object whose fields to access
     * @param fieldClass type of field to retrieve
     * @return Stream of values
     */
    public static <T, E> Stream<E> getFieldValues(T object, Class<E> fieldClass) {
        ArrayList<E> fields = new ArrayList<E>();
        for (Field f : object.getClass().getDeclaredFields()) {
            if (f.getType().equals(fieldClass)) {
                try {
                    boolean defaultAccess = f.isAccessible();
                    E obj = fieldClass.cast(f.get(object));

                    f.setAccessible(true);
                    fields.add(obj);
                    f.setAccessible(defaultAccess);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return fields.stream();
    }
}