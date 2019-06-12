package com.kwdz.blog.web.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.common.util.FastJson;
import com.kwdz.blog.api.common.util.tree.NodeInfo;
import com.kwdz.blog.api.common.util.tree.TreeHandler;
import com.kwdz.blog.api.dict.feign.SysDictFeign;
import com.kwdz.blog.api.dict.vo.SysDictVo;
import com.kwdz.blog.web.common.tree.BootstrapTree;
import com.kwdz.blog.web.common.tree.ResignationMeeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysDictFeign sysDictFeign;

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

    public static List<BootstrapTree> toTree(NodeInfo<SysDictVo> root) throws FileNotFoundException {
        List<BootstrapTree> list = new ArrayList<>();
//        List<ResignationMeeting> list2 = getMeetingJson();
        log.info("读取resignation_meeting.json");
        root.getChildren().forEach(item -> {
            BootstrapTree a = new BootstrapTree();
            if (item.getChildren() != null && item.getChildren().size() > 0) {
                List<BootstrapTree> children = new ArrayList<>();
                item.getChildren().forEach(x -> {
                    BootstrapTree b = new BootstrapTree();
                    b.setId(x.getCont().getId());
                    b.setName(x.getCont().getTypeName() + "|" + x.getCont().getDictNo());
                    b.setText(x.getCont().getDictValue());
                    b.setSelectable(true);
                    children.add(b);
                });
                a.setNodes(children);
            } else {
                a.setNodes(null);
            }
            a.setSelectable(false);
            a.setName(item.getCont().getTypeName() + "|" + item.getCont().getDictNo());
            a.setText(item.getCont().getDictValue());
            a.setId(item.getCont().getId());
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
