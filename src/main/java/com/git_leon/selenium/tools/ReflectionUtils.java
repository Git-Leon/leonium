package com.git_leon.selenium.tools;

/**
 * Created by leon on 8/17/17.
 */

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 5/19/17.
 */
public class ReflectionUtils {
    /**
     * @param object object whose fields to access
     * @param fieldClass type of field to retrieve
     * @return Stream of values
     */
    public static <ObjectToInspect, FieldType> List<FieldType> getFieldValues(ObjectToInspect object, Class<FieldType> fieldClass) {
     List<FieldType> fields = new ArrayList<>();
        for (Field f : object.getClass().getDeclaredFields()) {
            if (f.getType().equals(fieldClass)) {
                try {
                    boolean defaultAccess = f.isAccessible();
                    FieldType obj = fieldClass.cast(f.get(object));

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
        return fields;
    }
}