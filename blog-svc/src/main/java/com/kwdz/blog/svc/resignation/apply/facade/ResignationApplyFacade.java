package com.kwdz.blog.svc.resignation.apply.facade;

import com.kwdz.blog.api.resignation.apply.vo.ResignationApplyVo;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import com.kwdz.blog.svc.resignation.apply.entity.ResignationApplyEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.0.1
 * @author huyt
 * @date 2019/4/15 0:53
 */

@RestController
@RequestMapping("/resignationApply/")
@Slf4j
public class ResignationApplyFacade extends CommonFacade<ResignationApplyVo, ResignationApplyEntity> {

}
