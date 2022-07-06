package cn.edu.hit.controller;

import cn.edu.hit.po.CategoryExt;
import cn.edu.hit.po.Product;
import cn.edu.hit.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index")
public class IndexController {
   @Autowired
    IndexService indexService;

    //serveletContxt  域对象

    @Autowired
    ServletContext servletContext;
    //分级目录
    @RequestMapping("/toindex")
    public String toindex(Model model){
        List<CategoryExt> category = indexService.getCategory();
        model.addAttribute("category",category);
       /* Map<Integer, List<Product>> product = indexService.getProduct(category);
        model.addAttribute("product",product);*/
        return "index";
    }
}
