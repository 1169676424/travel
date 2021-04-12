package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author ply
 * @date 2021/4/9 - 15:57
 */
public interface RouteDao {

    int totalCount(@Param(value = "cid") String cid);

    List<Route> findByPage(Map map);

    Route findOne(@Param(value = "rid") String rid);

    void updateCount(@Param(value = "rid") String rid);

    List<Route> findList();
}
