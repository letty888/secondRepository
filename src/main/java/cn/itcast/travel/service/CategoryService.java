package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryService {

    /**
     * 在数据库中查找所有旅游条目
     * @return
     */
    List<Category> findAllCategory();

    /**
     * 在tab_category表中,根据cid查出旅游类别
     * @param cid
     * @return
     */
    Category findCategory(int cid);
}
