package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

import java.util.Date;

public class FavoriteServiceImpl implements FavoriteService {

    FavoriteDao favoriteDao = new FavoriteDaoImpl();

    /**
     * 根据rid和uid在tab_favorite表中查找对应的favorite对象
     *
     * @param rid
     * @param
     * @return
     */
    @Override
    public boolean isFavorite(int rid,int uid) {
        Favorite favorite = favoriteDao.findFavorite(rid,uid);
        if (favorite == null) {
            //说明该用户以前没有收藏过该条旅游线路
            return false;
        } else {
            return true;
        }
    }


    /**
     * 添加收藏
     * @param rid
     * @param date
     * @param uid
     */
    @Override
    public void addFavorite(int rid, Date date, int uid) {
        favoriteDao.addFavorite(rid,date,uid);
    }

    /**
     * 根据rid在tab_favorite表中查找收藏次数
     * @param rid
     * @return
     */
    @Override
    public int favoriteCount(int rid) {
        int count = favoriteDao.findcount(rid);
        return count;
    }
}
