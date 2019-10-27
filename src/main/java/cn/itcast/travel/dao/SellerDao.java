package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao {


    /**
     * 在tab_seller表中根据sid查出此条旅游线路对应的商家
     * @param sid
     * @return
     */
    Seller findSeller(int sid);
}
