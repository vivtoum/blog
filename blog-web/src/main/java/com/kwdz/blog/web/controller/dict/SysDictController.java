package com.kwdz.blog.web.controller.dict;

import com.kwdz.blog.api.common.controller.BaseController;
import com.kwdz.blog.api.common.util.tree.NodeInfo;
import com.kwdz.blog.api.common.util.tree.TreeHandler;
import com.kwdz.blog.api.dict.feign.SysDictFeign;
import com.kwdz.blog.api.dict.vo.SysDictVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author YT.Hu
 * @version 0.0.1
 * @date 2019/5/29 21:41
 */
@Slf4j
@RestController
@RequestMapping("/dict/")
public class SysDictController extends BaseController<SysDictVo> {

    @Autowired
    private SysDictFeign sysDictFeign;

    @GetMapping("test")
    public void test() {
        List<SysDictVo> list = sysDictFeign.list(new SysDictVo()).getData();
        TreeHandler<SysDictVo> tree = new TreeHandler<>("-1");
        NodeInfo<SysDictVo> root;
        try {
            root = tree.parseArray(list);
            NodeInfo<SysDictVo> childTree = tree.queryTree("4582");
            System.out.println(childTree.getParentIds());
            List<NodeInfo<SysDictVo>> nodes = tree.getNodes();
            TreeHandler handler = tree.splitById("13", "");
            handler.getNodeMap();
        } catch (Exception ex) {

        }


    }
}
