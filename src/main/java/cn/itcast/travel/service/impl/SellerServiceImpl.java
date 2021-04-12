package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.SellerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ply
 * @date 2021/4/10 - 8:56
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Resource
    private SellerDao sellerDao;

    @Override
    public Seller findBySid(Integer sid) {
        Seller bySid = sellerDao.findBySid(sid);
        return bySid;
    }
}
