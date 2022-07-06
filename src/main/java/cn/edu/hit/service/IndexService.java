package cn.edu.hit.service;

import cn.edu.hit.po.CategoryExt;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IndexService {
    List<CategoryExt> getCategory();
}
