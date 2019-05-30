package com.kwdz.blog.svc.common.aop;

import com.kwdz.blog.api.common.aop.Encry;
import com.kwdz.blog.api.common.util.AESUtil;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.lang.reflect.Field;

/**
 * remote user 实体持久化之前的拦截
 *
 * @author YT.Hu
 */
@Slf4j
public class JpaEntityListen {

    /**
     * 在保存之前调用
     */
    @PrePersist
    @PreUpdate
    public void preSave(Object source) {
        for (Field field : source.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(source);
                if (field.isAnnotationPresent(Encry.class)) {
                    boolean saveEncode = field.getAnnotation(Encry.class).saveEncode();
                    if(saveEncode){
                        //  把加密后内容替换进去
                        field.set(source, AESUtil.encrypt((String) fieldValue, AESUtil.KEY));
                    }
                }
            } catch (IllegalAccessException ex) {
                return;
            }
        }
    }

    /**
     * 在保存之后调用
     */
    @PostUpdate
    @PostPersist
    public void savePersist(Object source) {
        log.info("@PostPersist：" + source);
    }

    /**
     * 在Entity被映射之后调用
     */
    @PostLoad
    public void postLoad(Object source) {
        for (Field field : source.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object fieldValue = field.get(source);
                if (field.isAnnotationPresent(Encry.class)) {
                    boolean loadDecode = field.getAnnotation(Encry.class).loadDecode();
                    if(loadDecode){
                        //  把加密后内容替换进去
                        field.set(source, AESUtil.decrypt((String) fieldValue, AESUtil.KEY));
                    }
                }
            } catch (IllegalAccessException ex) {
                return;
            }
        }
    }
}