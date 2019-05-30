package com.kwdz.blog.svc.job;

import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.svc.remoteuser.entity.RemoteUserEntity;
import com.kwdz.blog.svc.remoteuser.service.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/24 17:23
 */
@Slf4j
@Component
public class JobUtil {

    @Autowired
    private RemoteUserService remoteUserService;


    private static List<RemoteUserEntity> getUser(String path) throws FileNotFoundException {
        List<RemoteUserEntity> list = new ArrayList<>();
        File file = ResourceUtils.getFile(path);
        FileInputStream fis = null;
        InputStreamReader isr = null;
        //  用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        BufferedReader br = null;
        try {
            String str = "";
            fis = new FileInputStream(file);
            // 从文件系统中的某个文件中获取字节
            // InputStreamReader 是字节流通向字符流的桥梁,
            isr = new InputStreamReader(fis);
            // 从字符输入流中读取文件中的内容,封装了一个new
            br = new BufferedReader(isr);
            // InputStreamReader的对象
            while ((str = br.readLine()) != null) {
                //截取得到的一行数据
                String[] parms = str.split("\\|");
                //跳过第一行
                if ("account_type".equalsIgnoreCase(parms[0])) {
                    continue;
                } else {
                    RemoteUserEntity user = new RemoteUserEntity();

                    Field[] f = RemoteUserEntity.class.getDeclaredFields();
                    //给test对象赋值
                    for (int i = 0; i < f.length; i++) {
                        //获取属相名
                        String attributeName = f[i].getName();
                        //将属性名的首字母变为大写，为执行set/get方法做准备
                        String methodName = attributeName.substring(0, 1).toUpperCase() + attributeName.substring(1);
                        try {
                            //获取Test类当前属性的setXXX方法（私有和公有方法）
                            /*Method setMethod=Test.class.getDeclaredMethod("set"+methodName);*/
                            //获取Test类当前属性的setXXX方法（只能获取公有方法）
                            Method setMethod = RemoteUserEntity.class.getMethod("set" + methodName, String.class);
                            //执行该set方法
                            setMethod.invoke(user, parms[i]);
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            log.error(e.getMessage());
                        }
                    }
                    Collections.addAll(list, user);
                }
            }
        } catch (FileNotFoundException e) {
            log.error("找不到指定文件");
        } catch (IOException e) {
            log.error("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException | NullPointerException e) {
                log.error(e.getMessage());
            }
        }
        return list;
    }

    public void refreshUser() throws FileNotFoundException {
        remoteUserService.truncate();
        List<RemoteUserEntity> list = JobUtil.getUser("classpath:Interface.txt");
        List<RemoteUserVo> userVoList = remoteUserService.saveList(FastCopy.copyList(list, RemoteUserVo.class)).getData();
        if (userVoList.size() > 0)
            log.info(JobUtil.getUser("classpath:Interface.txt").toString());
    }
}
