package com.kwdz.blog.svc.application.facade;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.svc.application.entity.BpmApplicationEntity;
import com.kwdz.blog.svc.application.service.BpmApplicationService;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huyt
 * @version 0.0.1
 * @date 2019/4/15 0:53
 */

@RestController
@RequestMapping("/apply/")
@Slf4j
public class BpmApplicationFacade extends CommonFacade<BpmApplicationVo, BpmApplicationEntity> {
    @Autowired
    private BpmApplicationService bpmApplicationService;

    @PostMapping("resignation")
    ResultModel applyResignation(@RequestBody BpmApplicationVo data, HttpServletRequest request) {
        return bpmApplicationService.applyResignation(data, request);
    }
}
