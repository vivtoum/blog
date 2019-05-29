package com.kwdz.blog.web.common.util;

import com.kwdz.blog.api.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/29 11:39
 */
@Slf4j
public class HolidayUtil {

    public static List<String> getHoliday() throws FileNotFoundException {
        return getFromTxt("classpath:config/holiday.txt");
    }

    public static List<String> getWorkday() throws FileNotFoundException {
        return getFromTxt("classpath:config/workday.txt");
    }


    public static List<String> getFromTxt(String path) throws FileNotFoundException {
        List<String> result = new ArrayList<>();
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
                Collections.addAll(result, str);
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
        return result;
    }


    /**
     * 根据日期和天数算出最后的工作日
     *
     * @param workDays 工作日天数
     * @return 最后的工作日
     */
    public static String getLastWorkDate(int workDays) throws FileNotFoundException {
        List<String> holidays = getHoliday();
        List<String> workdays = getWorkday();
        String[] strs1 = holidays.toArray(new String[holidays.size()]);
        String[] strs2 = workdays.toArray(new String[workdays.size()]);
        String endDate = DateUtil.Date2String(DateUtil.plusDay(DateUtil.getCurrentCalendar().getTime(), workDays), "yyyy-MM-dd");
        while (Arrays.asList(strs1).contains(endDate) || (DateUtil.isWeekHoliday(endDate) && !Arrays.asList(strs2).contains(endDate))) {
            endDate = DateUtil.Date2String(DateUtil.plusDay(DateUtil.converStrToCalendar(endDate).getTime(), -1), DateUtil.FORMAT_DAY);
        }
        return endDate;
    }


}
