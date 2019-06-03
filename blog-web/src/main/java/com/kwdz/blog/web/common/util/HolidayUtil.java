package com.kwdz.blog.web.common.util;

import com.kwdz.blog.api.common.util.DateUtil;
import com.kwdz.blog.api.common.util.RSAUtils;
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

    public static void main(String[] args) {
        String a = "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";
        String privateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDBEN8iHcL9eD7+aEOJVVkXzW58kTPf83RXB2FaS7fyaXbYQysIinDL2yje6DViutzk6SovfwX8aVH6OEd/Rq3wzPIRoDDSwCJVOIWc/lHj/BWc6vjQfdOjtVUDka+qN2fSAzn818H6mEKxZeM65O1ypO6xGBeosb3ynpkm0XaqbvT/W0kjus1Ao9XSXerbCUU29I9+JaBl2irvlzvkKfaNFYbvlp269VsBEdD5VYNDdhDmBy3ZXWqBU0aHjNnr2FymSkhPBlc8Qo2wKnT5B2fCZpvwfJRjPajZfOGwtEB3EMB5D4j4xB101wjMhHVBuM0wBsndanE5FJdorgMss9HxAgMBAAECggEAdluSf0bBkERUUgC3Toz9FmOBVWpq4+NLMkM7AnEi7sCK9B+RVSYR6leL4cN3XvRxkQAENaPoloo2kHBKHJPG9LWfvQB4jert4VLKTql2qI6U7WL7cE2DHnx/BrYce/yFcDE8Lm5S4/7N/BUbTbKkgsDr97NuZaO5b/CcsTh+SBK0fzS4yUS+RAPT3+UVDOom5jGpeAq0gRed3qsDvPkq4jCLRg4cK+Sb5zicK1+rc56WZ6RHhDzf3U+mQG5p4aBG0GrbfGtFJ0bnyP7BagaDe6xhVUdH2eoJwtcj5Dq2dyG222kKfOpvaBKhCrVzc0wDgqToHgiNXSGfyIBDSrgotQKBgQD/MleH/X36YUAe8XTHb25xL+KKsH3GhH/a6BtGxyyRNF9Veo0heXwc90PW9uBB7L7JJWXnoblgB6IeUr4xCu4dBox1euk4ik4ght5dBpJ408NVbqzoz1itJ7LJBJeHktZK74F+ooRma6TZRfF5LSFBCOCeWbZVxt+G89MeDhrC+wKBgQDBrHWwU+7MLWliz4w84C6atGSERE3OwaO48G6HjNWwDb0CEPWDwwBEv7di1Q9Yf5c/wsD5FvhVNyeEIBt+yuSgNBEDstQ5CgAA6vB24cuCmpt4TGygRdwT1OezlM6A/8DfVyEIPakb5FkAf+ZVUBOBe3ovCdhKR+lMNeA3foxLAwKBgH0toXgIZQozN/O1tvW4+DC3L2WeayO8jMbBQdf3DSpwyS6xvZak87d1pqZEVP6hdXhPSuxTDQ5I0EIVjkuaM/Cy7KUX1Fyyot4bqelxttlj7pXygwhz5OiS54TpJrcO1OTPFPMcHtvqg2M8htVMyNoQ07V5BhKPScf1xCIjAwePAoGBALyRLrE0t1+Co4Y6b99hot6r3uZNHri9HqxVJDjELdZROgLGAlV8ykklpOcMbPmMmPXSqFKeQ8GshsQFAuBLqZg1uY8xUaILk39RQDlvlNcfs1nIh0bxXdrNQ588tmcxHFgYHChK6NkoVmO/I8NSyPsb/xXHbxi7tXGnTxyZEP2DAoGBAKuTY1IZ3v7mPHZm3GMt3KlVHvMcdvCHV+ekARvCTV5V3jc74AYcxpJbcV1D3bQpGOKpdG8mEcNew5KaXO7KaLSJEcoehhdY6z3Zq6ZoLXOHYqRIaXQRx9wBeTZks4mkXiMAWfQwNarGTU5c7db5n5PO5398mA3OhV+uqUy1cd3P";
        System.out.println(RSAUtils.decryptDataOnJava(a, privateKey));
    }
}
