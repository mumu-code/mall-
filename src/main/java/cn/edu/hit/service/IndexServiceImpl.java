package cn.edu.hit.service;

import cn.edu.hit.dao.IndexDao;
import cn.edu.hit.po.CategoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexDao indexDao;
    @Override
    public List<CategoryExt> getCategory(){

       List<CategoryExt> list =  indexDao.getCategory();
       return list;
    }
}
