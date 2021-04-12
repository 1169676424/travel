package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.service.RouteImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ply
 * @date 2021/4/10 - 8:52
 */
@Service
public class RouteImgServiceImpl implements RouteImgService {

    @Resource
    private RouteImgDao routeImgDao;

    @Override
    public List<RouteImg> findByRid(String rid) {
        List<RouteImg> byRid = routeImgDao.findByRid(rid);
        return byRid;
    }
}
