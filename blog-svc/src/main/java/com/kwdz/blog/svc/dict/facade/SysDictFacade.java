package com.kwdz.blog.svc.dict.facade;

import com.kwdz.blog.api.common.result.ResultModel;
import com.kwdz.blog.api.dict.vo.SysDictVo;
import com.kwdz.blog.svc.common.facade.CommonFacade;
import com.kwdz.blog.svc.dict.entity.SysDictEntity;
import com.kwdz.blog.svc.dict.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huyt
 * @version 0.0.1
 * @date 2019/4/15 0:53
 */

@RestController
@RequestMapping("/dict/")
@Slf4j
public class SysDictFacade extends CommonFacade<SysDictVo, SysDictEntity> {

    @Autowired
    private SysDictService sysDictService;


    @GetMapping("getType")
    public ResultModel<List<SysDictVo>> findByTypeName(String typeName) {
        return sysDictService.findByTypeName(typeName);
    }

}
