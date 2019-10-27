package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

public interface CategoryDao {

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
    Category findOneCategory(int cid);

    //这是Git测试代码
}
