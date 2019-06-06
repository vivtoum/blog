package com.kwdz.blog.web.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.common.util.FastJson;
import com.kwdz.blog.web.common.tree.BootstrapTree;
import com.kwdz.blog.web.common.tree.ResignationMeeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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

    public static List<BootstrapTree> toTree() throws FileNotFoundException {

        List<BootstrapTree> list = new ArrayList<BootstrapTree>();
        log.info("读取resignation_meeting.json");
        JSONArray.parseArray(readJsonFile("classpath:data/resignation_meeting.json")).forEach(a -> {
            ResignationMeeting meeting = FastJson.parse(JSONObject.toJSONString(a), ResignationMeeting.class);
            BootstrapTree toTree = new BootstrapTree();
            toTree.setText(meeting.getTitle());
            if (meeting.getItems().size() > 0) {
                toTree.setSelectable(false);
            }
            list.add(toTree);
        });
        return list;
    }

    public static void main(String[] args) throws FileNotFoundException {
        toTree();
    }

}
