package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {
CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 在数据库中查找所有旅游条目
     * @return
     */
    @Override
    public List<Category> findAllCategory() {
        //现在Redis中查找
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> tuples = jedis.zrangeWithScores("category", 0, -1);
        List<Category> list = null;
        if(tuples == null){
            System.out.println("从数据库中查找旅游类别...");
            //说明Redis中没有数据
            //调用dao层中的方法在数据库中去找
            list =  categoryDao.findAllCategory();
            //将list存到Redis中
            for(int i = 0; i < list.size();i++){
                jedis.zadd("category",list.get(i).getCid(),list.get(i).getCname());
            }
        }else{
            System.out.println("从Redis缓存中查找旅游类别...");
            //说明Redis中有数据,需要将Set<Tuple> tuples中的数据取出来存到list集合中
            list = new ArrayList<Category>();
            for (Tuple tuple : tuples) {
                Category category = new Category();
                category.setCid((int)tuple.getScore());
                category.setCname(tuple.getElement());
                list.add(category);
            }
        }

        return list;
    }


    /**
     * 在tab_category表中,根据cid查出旅游类别
     * @param cid
     * @return
     */
    @Override
    public Category findCategory(int cid) {
        Category category = categoryDao.findOneCategory(cid);
        return category ;
    }

}
