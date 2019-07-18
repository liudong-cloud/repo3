package com.itheima.controller;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user")
public class ControllerUser {
    @Autowired
    private IUserService userService;

    @RequestMapping("/findAll.do")
    public @ResponseBody
    List<UserInfo> findAll() {
        List<UserInfo> list = userService.findAll();
        for (UserInfo userInfo : list) {
            System.out.println("userInfo = " + userInfo);
        }
        return list;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) {
        // System.out.println("userInfo = " + userInfo);
        userService.save(userInfo);
        return "redirect:/pages/user-list.jsp";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) {
        UserInfo userInfo = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id") String uid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("uid", uid);
        List<Role> roleList = userService.findOtherRoles(uid);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId", required = true) String userId, @RequestParam(name = "ids") String[] roleIds) {
        userService.addRoleToUser(userId, roleIds);
        return "redirect:/pages/user-list.jsp";

    }

}
