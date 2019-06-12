package com.kwdz.blog.web.controller.application;

import com.kwdz.blog.api.application.feign.BpmApplicationFeign;
import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.controller.BaseController;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.common.util.DateUtil;
import com.kwdz.blog.api.common.util.IpUtil;
import com.kwdz.blog.api.common.util.RSAUtils;
import com.kwdz.blog.api.common.util.RsaSecurityParameter;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.web.common.config.RsaDecodeRequestBodyAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author YT.Hu
 * @version 0.0.1
 * @date 2019/5/29 21:41
 */
@Slf4j
@RestController
@RequestMapping("/apply/")
public class BpmApplicationController extends BaseController<BpmApplicationVo> {

    @Autowired
    private BpmApplicationFeign bpmApplicationFeign;

    @RsaSecurityParameter
    @ResponseBody
    @PostMapping("resignation")
    public ResultModel resignation(@RequestBody BpmApplicationVo data, HttpServletRequest request) {
        RemoteUserVo remoteUserVo = (RemoteUserVo) request.getSession().getAttribute("user");

        data.setCreateTime(new Date());
        data.setOperatorIp(IpUtil.getIpAddr(request));
        data.setOperatorId(remoteUserVo.getEmployeeNo());
        ResultModel<BpmApplicationVo> resultModel = bpmApplicationFeign.save(data);
        if (resultModel.isFlag()) {
            return ResultModel.of("提交完成！单号：" + resultModel.getData().getId());
        } else {
            return ResultModel.of("提交失败！");
        }
    }

    @RsaSecurityParameter
    @ResponseBody
    @PostMapping("resignationMeeting")
    public ResultModel resignationMeeting(@RequestBody BpmApplicationVo data, HttpServletRequest request) {
        RemoteUserVo remoteUserVo = (RemoteUserVo) request.getSession().getAttribute("user");

        data.setCreateTime(new Date());
        data.setOperatorIp(IpUtil.getIpAddr(request));
        data.setOperatorId(remoteUserVo.getEmployeeNo());
        return null;
    }

    @ResponseBody
    @RsaSecurityParameter
    @GetMapping("detail/{id}")
    public BpmApplicationVo detail(@PathVariable(value = "id") String id) {
        return bpmApplicationFeign.get(id).getData();
    }
}
