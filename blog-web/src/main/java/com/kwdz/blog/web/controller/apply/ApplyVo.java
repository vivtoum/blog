package com.kwdz.blog.web.controller.apply;

import lombok.Data;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * @author YT.Hu
 * @version 0.0.1
 * @date 2019/5/29 22:24
 */
@Data
public class ApplyVo implements Serializable {

    private static final long serialVersionUID = 86296481128651274L;

    private String bpm;

    private JSONObject formData;
}
