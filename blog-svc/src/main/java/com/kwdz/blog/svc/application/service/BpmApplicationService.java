package com.kwdz.blog.svc.application.service;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.svc.application.entity.BpmApplicationEntity;
import com.kwdz.blog.svc.common.service.CommonService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:53
 */
public interface BpmApplicationService extends CommonService<BpmApplicationVo, BpmApplicationEntity> {

    ResultModel applyResignation(BpmApplicationVo data, HttpServletRequest request);
}
