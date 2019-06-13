package com.kwdz.blog.svc.application.service;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.common.util.IpUtil;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.svc.application.entity.BpmApplicationEntity;
import com.kwdz.blog.svc.common.service.CommonService4RedisImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author YT.Hu
 * @version 1.0
 * @date 2019/5/30 11:56
 */
@Transactional
@Service
public class BpmApplicationServiceImpl extends CommonService4RedisImpl<BpmApplicationVo, BpmApplicationEntity> implements BpmApplicationService {

    @Override
    public ResultModel applyResignation(BpmApplicationVo data, HttpServletRequest request) {
        RemoteUserVo remoteUserVo = (RemoteUserVo) request.getSession().getAttribute("user");
        data.setCreateTime(new Date());
        data.setOperatorIp(IpUtil.getIpAddr(request));
        data.setOperatorId(remoteUserVo.getEmployeeNo());
        ResultModel<BpmApplicationVo> resultModel = save(data);
        if (resultModel.isFlag()) {
            return ResultModel.of("提交完成！单号：" + resultModel.getData().getId());
        } else {
            return ResultModel.of("提交失败！");
        }
    }
}
