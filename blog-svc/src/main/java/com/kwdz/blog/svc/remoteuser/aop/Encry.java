package com.kwdz.blog.svc.remoteuser.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/27 11:48
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Encry {

    boolean saveEncode() default true;

    boolean loadDecode() default false;

}
