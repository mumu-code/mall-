package cn.edu.hit.service;

import cn.edu.hit.dao.IndexDao;
import cn.edu.hit.po.CategoryExt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexDao indexDao;
    @Override
    public List<CategoryExt> getCategory(){

       List<CategoryExt> list =  indexDao.getCategory();
       return list;
    }
}
