package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.IRoleService;
import com.itheima.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/role")
public class ControllerRole{
    @Autowired
    private IRoleService roleService;

    @RequestMapping("/findAll.do")
    public @ResponseBody List<Role> findAll(){
        List<Role> list = roleService.findAll();
        return list;
    }

    @RequestMapping("/save.do")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/pages/role-list.jsp";
    }

    @RequestMapping("/deleteRole.do")
    public @ResponseBody List<Role> deleteRole(String id){
        roleService.deleteRoleById(id);
        List<Role> list = roleService.findAll();
        return list;
    }

    @RequestMapping("/findById.do")
       public ModelAndView findById(String id){
           Role role = roleService.findByRoleId(id);
           ModelAndView mv = new ModelAndView();
           mv.addObject("role",role);
           mv.setViewName("role-show");
           return mv;
       }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id") String roleId) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("roleId", roleId);
        List<Permission> permissionList = roleService.findOtherPermissions(roleId);
        mv.addObject("permissionList",permissionList);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId" ,required = true)String roleId,@RequestParam(name = "ids")String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:/pages/role-list.jsp";

    }



}
