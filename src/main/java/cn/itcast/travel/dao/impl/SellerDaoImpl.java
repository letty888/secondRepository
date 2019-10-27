package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    /**
     * 在tab_seller表中根据sid查出此条旅游线路对应的商家
     * @param sid
     * @return
     */
    @Override
    public Seller findSeller(int sid) {
        Seller seller = null;
        try {
            String sql = "select * from tab_seller where sid = ?";
             seller = template.queryForObject(sql, new BeanPropertyRowMapper<>(Seller.class), sid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return seller;
    }
}
