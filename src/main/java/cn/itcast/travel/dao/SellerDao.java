package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @author ply
 * @date 2021/4/10 - 8:57
 */
public interface SellerDao {

    Seller findBySid(Integer sid);
}
