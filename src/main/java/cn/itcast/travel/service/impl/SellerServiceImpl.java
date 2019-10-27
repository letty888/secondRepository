package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.SellerService;

public class SellerServiceImpl implements SellerService {

    SellerDao sellerDao = new SellerDaoImpl();

    /**
     * 在tab_seller表中根据sid查出此条旅游线路对应的商家
     * @param sid
     * @return
     */
    @Override
    public Seller findSeller(int sid) {
        Seller seller = sellerDao.findSeller(sid);
        return seller;
    }
}
