package com.kwdz.blog.svc.menu.service;

import com.kwdz.blog.api.common.util.FastCopy;
import com.kwdz.blog.api.common.util.FastJson;
import com.kwdz.blog.api.common.util.RedisUtil;
import com.kwdz.blog.api.menu.vo.SysMenuVo;
import com.kwdz.blog.svc.common.service.CommonServiceImpl;
import com.kwdz.blog.svc.menu.dao.MenuDao;
import com.kwdz.blog.svc.menu.entity.SysMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends CommonServiceImpl<SysMenuVo, SysMenuEntity> implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 按等级获取菜单
     *
     * @param level     登录人等级
     * @param staffCode 员工编号
     * @return 菜单树的json结构
     */
    @Override
    public String getTree(String staffCode, String level) {
        //  从redis中查找key
        if (redisUtil.hasKey(staffCode + ":" + level + ":MENU")) {
            //  读取redis缓存
            return FastJson.toJsonStr(redisUtil.get(staffCode + "_" + level + "_MENU"));
        } else {
            List<SysMenuEntity> menus = menuDao.findAll();
            //  获取顶端节点，即获取父节点 = -1 的节点
            List<SysMenuEntity> top = menus.stream().filter(x -> StringUtils.isEmpty(x.getParentId()) || x.getParentId().equals("-1")).collect(Collectors.toList());
            Map<String, List<SysMenuEntity>> allMap = menus.stream().collect(Collectors.groupingBy(SysMenuEntity::getParentId));
            //  执行递归
            List<SysMenuVo> list = this.treeMenuData(top, allMap);
            //  缓存倒redis 10分钟
            redisUtil.set(staffCode + "_" + level + "_MENU", list, 10 * 60L);
            return FastJson.toJsonStr(list);
        }
    }


    /**
     * 递归生成菜单树的json结构
     *
     * @param top    顶端菜单
     * @param allMap 分组菜单
     * @return list类的树状结构
     */
    private List<SysMenuVo> treeMenuData(List<SysMenuEntity> top, Map<String, List<SysMenuEntity>> allMap) {
        //  遍历
        List<SysMenuVo> topList = FastCopy.copyList(top, SysMenuVo.class);
        topList.forEach(entity -> {
            if (allMap.get(entity.getId()) != null) {
                List<SysMenuVo> temp = FastCopy.copyList(allMap.get(entity.getId()), SysMenuVo.class);
                if (temp != null && !temp.isEmpty()) {
                    entity.setChildMenus(temp);
                }
            }
        });
        return topList;
    }
}
