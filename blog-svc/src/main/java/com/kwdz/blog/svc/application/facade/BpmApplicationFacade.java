package com.kwdz.blog.svc.application.facade;

import com.kwdz.blog.api.application.vo.BpmApplicationVo;
import com.kwdz.blog.svc.application.entity.BpmApplicationEntity;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 0.0.1
 * @author huyt
 * @date 2019/4/15 0:53
 */

@RestController
@RequestMapping("/apply/")
@Slf4j
public class BpmApplicationFacade extends CommonFacade<BpmApplicationVo, BpmApplicationEntity> {

}
