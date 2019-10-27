package cn.itcast.travel.service;

import java.util.Date;

public interface FavoriteService {

    /**
     * 根据rid和uid在tab_favorite表中查找对应的favorite对象
     * @param rid
     * @param
     * @return
     */
    boolean isFavorite(int rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param date
     * @param uid
     */
    void addFavorite(int rid, Date date, int uid);

    /**
     * 根据rid在tab_favorite表中查找收藏次数
     * @param rid
     * @return
     */
    int favoriteCount(int rid);
}
