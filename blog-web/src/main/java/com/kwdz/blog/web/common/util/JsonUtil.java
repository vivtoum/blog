package com.kwdz.blog.web.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.common.util.FastJson;
import com.kwdz.blog.web.common.tree.BootstrapTree;
import com.kwdz.blog.web.common.tree.ResignationMeeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/6/6 11:24
 */
@Slf4j
public class JsonUtil {

    /**
     * 读取json文件，返回json串
     *
     * @param fileName
     * @return
     */
    public static String readJsonFile(String fileName) throws FileNotFoundException {
        String jsonStr = "";
        try {
            File jsonFile = ResourceUtils.getFile(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile), StandardCharsets.UTF_8);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<BootstrapTree> toTree(HttpServletRequest request) throws FileNotFoundException {
        List<BootstrapTree> list = new ArrayList<>();
        List<ResignationMeeting> list2 = getMeetingJson();
        log.info("读取resignation_meeting.json");
        list2.forEach(item -> {
            BootstrapTree a = new BootstrapTree();
            if (item.getItems() != null && item.getItems().size() > 0) {
                List<BootstrapTree> children = new ArrayList<>();
                item.getItems().forEach(x -> {
                    BootstrapTree b = new BootstrapTree();
                    b.setId(x.getId());
                    if (request.getLocale().equals(Locale.US)) {
                        b.setText(x.getNameEn());
                    } else {
                        b.setText(x.getName());
                    }
                    b.setSelectable(true);
                    children.add(b);
                });
                a.setNodes(children);
            } else {
                a.setNodes(null);
            }
            a.setSelectable(false);
            if (request.getLocale().equals(Locale.US)) {
                a.setText(item.getTitleEn());
            } else {
                a.setText(item.getTitle());
            }
            a.setId(item.getId());
            list.add(a);
        });
        return list;
    }

    private static List<ResignationMeeting> getMeetingJson() throws FileNotFoundException {
        JSONArray array = null;
        List<ResignationMeeting> list = new ArrayList<ResignationMeeting>();
        try {
            array = (JSONArray) JSONArray.parse(readJsonFile("classpath:data/resignation_meeting.json"));
        } catch (FileNotFoundException ex) {
        }
        array.forEach(item -> {
            try {
                list.add(JSONChange.jsonToObj(ResignationMeeting.class, FastJson.toJsonStr(item)));
            } catch (IOException ex) {
            }
        });
        return list;
    }
}
