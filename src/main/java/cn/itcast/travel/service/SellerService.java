package cn.itcast.travel.service;

import cn.itcast.travel.domain.Seller;

/**
 * @author ply
 * @date 2021/4/10 - 8:56
 */
public interface SellerService {

    Seller findBySid(Integer sid);
}
