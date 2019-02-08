package com.lin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.common.JsonData;
import com.lin.entity.User;
import com.lin.mapper.UserMapper;
import com.lin.service.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/**
 * Created by linziyu on 2019/1/27.
 */

@Controller
@RequestMapping(value = "/u")
@Slf4j
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserMapper userMapper;

    @Resource
    private LoginService loginService;

    @Resource
    private RegisterService registerService;

    @Resource
    private UserService userService;

    @Resource
    private LogService logService;

    @Resource
    private UserLogService userLogService;

    //主页
    @RequestMapping(value = "/index")
    public String index(Model model,HttpSession session)
    {
        String current_user_name  = (String) session.getAttribute("current_user_name");

        model.addAttribute("current_user_name",current_user_name);
        return "user/index1";
    }


    //加载登录页面
    @RequestMapping(value = "/loginpage")
    public String welcome(){
        return "user/login5";
    }


    //加载更新用户资料界面
    @RequestMapping(value = "/edit_user")
    public String edit_user(){
        return "user/edit_user";
    }

    //更新用户操作
    @RequestMapping(value = "/update_user")
    @ResponseBody
    public  JsonData update(User user,HttpSession session){
        Integer old_id = (Integer) session.getAttribute("old_id");
        String current_user_name = (String) session.getAttribute("current_user_name");
        log.info("{}","current name:"+current_user_name);
        session.setAttribute("new_user_name",user.getName());
        log.info("{}","new user"+user.getName());
        JsonData data = userService.updateUser(user);
        log.info("{}","code"+data.getCode());
        String old_user_name = (String) session.getAttribute("oldName");
        String old_user_pwd = (String) session.getAttribute("oldPwd");
        if (data.getCode() == 200){
            userLogService.saveUserLog(old_id,user.getId(),current_user_name,old_user_name,old_user_pwd);
        }
        return data;
    }

    //加载注册页面
    @RequestMapping(value = "/registerpage")
    public String to_regiser(){
        return "user/register5";
    }

    //注册操作
    @RequestMapping(value = "/register")
    @ResponseBody
    public JsonData register(User register_user,HttpSession session){
            logService.saveRegisterLog(register_user.getName());
            String hhh = (String) session.getAttribute("current_user_name");
            return registerService.saveUser(register_user,hhh);
    }

    //获取更改前用户
    @RequestMapping(value = "/getOldUser")
    @ResponseBody
    public JsonData getOldName(Integer id,HttpSession session){
        log.info("{}",id);
        User user = userMapper.findById(id);
        session.setAttribute("old_id",id);
        session.setAttribute("oldName",user.getName());
        session.setAttribute("oldPwd",user.getPassword());
        if(user == null){
            return new JsonData(701,"用户不存在");
        }
        session.setAttribute("old_name",user.getName());
        return new JsonData(200,"存在");
    }



    @RequestMapping(value = "/logout")
    @ResponseBody
    public JsonData logout(HttpSession session){
        session.invalidate();
        return new JsonData(200,"退出成功");
    }


    //删除用户
    @RequestMapping(value = "/deleteUser")
    @ResponseBody
    public  JsonData deleteUser(User user){
        return  userService.deleteUser(user);
    }

    //登录操作
    @RequestMapping(value = "/login")
    @ResponseBody
    public JsonData login(User user,HttpSession httpSession) throws ParseException {
        logService.saveLoginLog(user.getName());
        httpSession.setAttribute("current_user_name",user.getName());
        return loginService.login(user);

    }


    @RequestMapping(value = "/success")
    public ModelAndView success(HttpSession session){
        log.info("{}",session.getAttribute("current_user_name")+"*****");
        return new ModelAndView("redirect:all");//定向跳转
    }



    @RequestMapping(value = "/all")
    //初始时设置为第一页 5条数据
    public String list(Model model, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {

        //引入分页查询，使用PageHelper分页功能在查询之前传入当前页，然后多少记录
        PageHelper.startPage(pageNum, pageSize);
        //startPage后紧跟的这个查询就是分页查询
        List<User> users = userMapper.getAllUsers();
        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
        PageInfo pageInfo = new PageInfo<User>(users, 5);

        model.addAttribute("pageInfo", pageInfo);

        //获得当前页
        model.addAttribute("pageNum", pageInfo.getPageNum());
        //获得一页显示的条数
        model.addAttribute("pageSize", pageInfo.getPageSize());
        //是否是第一页
        model.addAttribute("isFirstPage", pageInfo.isIsFirstPage());
        //获得总页数
        model.addAttribute("totalPages", pageInfo.getPages());
        //是否是最后一页
        model.addAttribute("isLastPage", pageInfo.isIsLastPage());
        return "user/index";
    }




}
