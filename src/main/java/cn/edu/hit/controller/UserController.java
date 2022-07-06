package cn.edu.hit.controller;


import cn.edu.hit.po.User;
import cn.edu.hit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {


   @Autowired
    UserService userService;
   @RequestMapping("/toregister")
    public String toregister(){
       //寻找页面,
       return "register";
   }

   @RequestMapping("/changeName")
   @ResponseBody
   public String changeName(String name){
       //注意判断名字
       int i = userService.changeName(name);
       if(i == 0){
           return "ok";
       }
       else{
           return "no";
       }
   }
    @RequestMapping("/doregister")
//    @ResponseBody
    public String doregister(User user, Model model){

        //后端验证   trim()去左右空格
        if (user.getUsername()==null || user.getUsername().trim()==""){
            model.addAttribute("error","注册失败");
//            return "no";
            return "register";
        }
        //验证密码
        String pattern="^(?=.*[a-zA-Z])(?=.*[\\d])(?=.*[\\W])[a-zA-Z\\d\\W]{6,16}$";
        if (!user.getPassword().matches(pattern)){
            model.addAttribute("error","注册失败");
            return "register";
        }
        //验证手机号
        String phontPatter="^1[3,5,7,8][\\d]{9}$";
        if (!user.getPhone().matches(phontPatter)){
            model.addAttribute("error","注册失败");
            return "register";
        }
        //添加
        userService.addUser(user);
//        return "ok";
        //重定向  跳转登录页
//        return "login";
        return "redirect:http://localhost:8080/shop_war_exploded/user/tologin";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/dologin")
    public String dologin(User user, String checkbox, HttpSession httpSession, HttpServletResponse httpServletResponse,Model model){
       User user1 = userService.login(user);
       if(user1 == null)
       {
           //用户错误
           model.addAttribute("error","用户名或密码错误");
           return "login";
       }
       else{
           httpSession.setAttribute("user",user1);
           //是否勾选记住密码
           if("on".equals(checkbox)){
               Cookie cookie1 = new Cookie("name",user1.getUsername());
               Cookie cookie2 = new Cookie("pass",user1.getPassword());
               cookie1.setPath("/");//根目录下
               cookie2.setPath("/");
               cookie1.setMaxAge(60*60*24*7);//密码保存7天
               cookie2.setMaxAge(60*60*24*7);//密码保存7天
               httpServletResponse.addCookie(cookie1);
               httpServletResponse.addCookie(cookie2);


           }
           else {
               Cookie cookie1=new Cookie("name",user1.getUsername());
               Cookie cookie2=new Cookie("pass",user1.getPassword());
               cookie1.setPath("/");
               cookie2.setPath("/");
               cookie1.setMaxAge(0);
               cookie2.setMaxAge(0);
               httpServletResponse.addCookie(cookie1);
               httpServletResponse.addCookie(cookie2);
           }
       }
        return "redirect:http://localhost:8080/shop_war_exploded/index/toindex";

    }


  /* @RequestMapping("/doregister")
    public String doregister(User user, Model model){
       //用户名，去除空格，trim去除两端空格
       if(user.getName() == null||user.getName().trim()=="")
       {
           model.addAttribute("error","注册失败");
           return "register";
       }

       String pattern = "^(?=.*[a-zA-Z])(?=.*[\\d])(?=.*[\\W])[a-zA-Z\\d\\W]{6,16}$";
       if(!user.getPassword().matches(pattern)){
           model.addAttribute("error","注册失败");
           return "register";
       }

       String phonePatter = "^1[3.5.7.8][\\d]{9}$";
       if(!user.getPhone().matches(phonePatter)){
           model.addAttribute("error","注册失败");
           return "register";
       }
       userService.addUser(user);
       //去登录，重定向

       return "redirect:http://localhost:8080/shop_war_exploded/user/tologin";



   }
   @RequestMapping("/tologin")
    public String login(){
       return "login";
   }*/

   /*  @RequestMapping("/test")
    public String test(){
        System.out.println(123456);
        userService.test();
        return null;
    }*/
}