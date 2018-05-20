package com.combishackaton.app.common.util;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;

@Component
public class PropertyMapper {

    private final BeanUtilsBean beanUtilsBean;
    private static PropertyMapper instance;

    public PropertyMapper() {
        this.beanUtilsBean = new BeanUtilsBean() {
            @Override
            public void copyProperty(Object bean, String name, Object value) throws IllegalAccessException,
                    InvocationTargetException {
                if(value == null) {
                    return; //skip null values
                }
                super.copyProperty(bean, name, value);
            }
        };
    }

    @PostConstruct
    private void init() {
        instance = this;
    }

    public static PropertyMapper getInstance() {
        return instance;
    }

    public void map(Object source, Object target) throws InvocationTargetException, IllegalAccessException {
        beanUtilsBean.copyProperties(target, source);
    }
}
