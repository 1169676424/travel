package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @author ply
 * @date 2021/4/9 - 15:56
 */
public interface RouteService {

    PageBean<Route> pageQuery(String cid,int currentPage,int pageSize);

    Route findOne(String rid);

    void updateCount(String rid);

    List<Route> findList();
}
