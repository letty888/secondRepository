package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

import java.util.Date;

public interface FavoriteDao {

    /**
     * 根据rid和uid在tab_favorite表中查找对应的favorite对象
     * @param rid
     * @param
     * @return
     */
    Favorite findFavorite(int rid, int uid);

    /**
     * 添加收藏功能
     * @param rid
     * @param date
     * @param uid
     */
    void addFavorite(int rid, Date date, int uid);

    /**
     * 根据rid在tab_favorite表中查找对应的收藏次数
     * @param rid
     * @return
     */
    int findcount(int rid);
}
