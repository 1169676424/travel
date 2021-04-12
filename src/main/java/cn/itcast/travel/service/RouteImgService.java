package cn.itcast.travel.service;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @author ply
 * @date 2021/4/10 - 8:51
 */
public interface RouteImgService {

    List<RouteImg> findByRid(String rid);
}
