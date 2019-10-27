package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    /**
     * 在数据库中查找所有旅游条目
     *
     * @return
     */
    @Override
    public List<Category> findAllCategory() {

        //定义sql
        String sql = "select * from tab_category";
        //执行sql
        List<Category> list = template.query(sql, new BeanPropertyRowMapper<>(Category.class));

        return list;
    }


    /**
     * 在tab_category表中,根据cid查出旅游类别
     * @param cid
     * @return
     */
    @Override
    public Category findOneCategory(int cid) {
        Category category = null;
        try {
            String sql = "select * from tab_category where cid = ? ";
             category = template.queryForObject(sql, new BeanPropertyRowMapper<>(Category.class), cid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return category;
    }
}
