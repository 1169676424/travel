package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

/**
 * @author ply
 * @date 2021/4/10 - 8:53
 */
public interface RouteImgDao {

    List<RouteImg> findByRid(String rid);
}
