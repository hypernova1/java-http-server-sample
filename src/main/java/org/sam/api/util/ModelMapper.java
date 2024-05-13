package org.sam.api.util;

import org.sam.server.annotation.component.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Component
public class ModelMapper {

    private final CustomModelMapper customModelMapper;

    public ModelMapper(CustomModelMapper customModelMapper) {
        this.customModelMapper = customModelMapper;
    }

    public <T, U> U convert(T instance, Class<U> clazz) {
        U target = null;
        try {
            target = clazz.getDeclaredConstructor().newInstance();
            setValue(instance, target);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return target;
    }

    private <T, U> void setValue(T instance, U target) {
        Method[] declaredMethods = instance.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            if (declaredMethod.getName().startsWith("get")) {
                try {
                    String propertyName = declaredMethod.getName().replace("get", "");
                    Object value = declaredMethod.invoke(instance);
                    Method setter = target.getClass().getMethod("set" + propertyName, value.getClass());
                    setter.invoke(target, value);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ignored) {
                }
            }
        }
        if (customModelMapper != null) {
            try {
                Method map = customModelMapper.getClass().getMethod("map", instance.getClass(), target.getClass());
                map.invoke(customModelMapper, instance, target);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {
            }
        }
    }

}
