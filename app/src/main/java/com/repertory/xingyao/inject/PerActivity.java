package com.repertory.xingyao.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @Title:PerActivity
 * @Package:com.repertory.xingyao.inject
 * @Description: 自定义Activity注解实现局部单例(标识注入器只实例化一次的类型,不继承。)
 * 进入@Singleton源码发现与下面效果是一样的。区别是，这种写法更你能让自己明义；
 * @Auther:YJH
 * @Email:yuannunhua@gmail.com
 * @Date:2018/10/1816:09
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
