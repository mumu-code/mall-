package cn.edu.hit.controller;

import cn.edu.hit.po.CategoryExt;
import cn.edu.hit.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    IndexService indexService;

    //serveletContxt  域对象
/*
    @Autowired
    ServletContext servletContext;*/
    @RequestMapping("/toindex")
    //分级目录
    public String toindex(Model model){
     //选择集合处理
        //一级对象，
     /*   List<CategoryExt> list = new ArrayList<>();*/
        List<CategoryExt> category = indexService.getCategory();

        //发送到前台
        model.addAttribute("category",category);


       return "index";
    }
}
