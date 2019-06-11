package com.kwdz.blog.web.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kwdz.blog.api.common.util.DateUtil;
import com.kwdz.blog.api.common.util.FastJson;
import com.kwdz.blog.api.common.util.IpUtil;
import com.kwdz.blog.api.menu.fegin.SysMenuFeign;
import com.kwdz.blog.api.menu.vo.SysMenuVo;
import com.kwdz.blog.api.remoteUser.vo.RemoteUserVo;
import com.kwdz.blog.web.common.util.HolidayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

import static com.kwdz.blog.web.common.util.JsonUtil.readJsonFile;
import static com.kwdz.blog.web.common.util.JsonUtil.toTree;

@Controller
@RequestMapping("/pages/")
public class PagesController {

    @Autowired
    private SysMenuFeign sysMenuFeign;

    @GetMapping("sb2")
    public ModelAndView sb2(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("sb2/index");
        HttpSession session = request.getSession(false);
        if (session != null) {
            RemoteUserVo currentUser = (RemoteUserVo) session.getAttribute("user");
            String staffNo = currentUser.getEmployeeNo();
            modelAndView.addObject("currentUser", currentUser);
            modelAndView.addObject("_menu", FastJson.parseList(sysMenuFeign.getTreeMenu(staffNo, ""), SysMenuVo.class));
        }
        return modelAndView;
    }

    /**
     * 首页
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping("index")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("currentUser", request.getSession().getAttribute("user"));
        // "test"是test.html的名，
        // "iframe_div"是test.html中需要刷新的部分标志,
        // 在标签里加入：th:fragment="iframe_div"
        return "sb2/home::iframe_div";
    }

    /**
     * 辞职申请页面
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping("resignation")
    public String resignation(Model model, HttpServletRequest request) throws FileNotFoundException {
        RemoteUserVo userVo = (RemoteUserVo) request.getSession().getAttribute("user");
        //  todo 判断员工属于实习/试用/还是正式员工，不同性质last working day不同
        String joinTime = HolidayUtil.getLastWorkDate(30);
        model.addAttribute("currentUser", userVo);
        model.addAttribute("last_day", joinTime);
        // "test"是test.html的名，
        // "iframe_div"是test.html中需要刷新的部分标志,
        // 在标签里加入：th:fragment="iframe_div"
        return "sb2/resignation::iframe_div";
    }


    /**
     * 辞职面谈页面
     *
     * @param model
     * @param request
     * @return
     */
    @GetMapping("resignation_meeting/{step}")
    public String resignationMeeting(Model model, HttpServletRequest request, @PathVariable("step") String step) throws FileNotFoundException {
        RemoteUserVo userVo = (RemoteUserVo) request.getSession().getAttribute("user");
        //  todo 判断员工属于实习/试用/还是正式员工，不同性质last working day不同
        String joinTime = HolidayUtil.getLastWorkDate(30);
        model.addAttribute("currentUser", userVo);
        model.addAttribute("_form", toTree(request));
        model.addAttribute("last_day", joinTime);
        model.addAttribute("_step", step);
        // "test"是test.html的名，
        // "iframe_div"是test.html中需要刷新的部分标志,
        // 在标签里加入：th:fragment="iframe_div"
        return "sb2/resignation_meeting::iframe_div";
    }

    @PostMapping(value = "/getIp")
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }
}
