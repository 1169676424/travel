package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ply
 * @date 2021/4/9 - 15:56
 */
@Service
public class RouteServiceImpl implements RouteService {

    @Resource
    private RouteDao routeDao;

    @Override
    public PageBean<Route> pageQuery(String cid, int currentPage, int pageSize) {
        //封装PageBean
        PageBean<Route> routePageBean = new PageBean<>();
        //设置当前页码
        routePageBean.setCurrentPage(currentPage);
        //设置每页显示条数
        routePageBean.setPageSize(pageSize);

        //1、查询总条数
        int i = routeDao.totalCount(cid);
        if (i <= 0) {
            routePageBean.setTotalCount(0);
            routePageBean.setList(new ArrayList<>());
            routePageBean.setTotalPage(0);
            return routePageBean;
        }

        //2、分页查询
        Map<String, Object> map = new HashMap<>();
        map.put("cid",cid);
        map.put("start",(currentPage - 1) * pageSize);//开始的记录数
        map.put("pageSize",pageSize);

        List<Route> byPage = routeDao.findByPage(map);
        routePageBean.setTotalCount(i);
        routePageBean.setList(byPage);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = i % pageSize == 0 ? i / pageSize : i / pageSize + 1;
        routePageBean.setTotalPage(totalPage);
        return routePageBean;
    }

    @Override
    public Route findOne(String rid) {
        Route one = routeDao.findOne(rid);
        return one;
    }

    @Override
    public void updateCount(String rid) {
        routeDao.updateCount(rid);
    }

    @Override
    public List<Route> findList() {
        List<Route> routeList = routeDao.findList();
        return routeList;
    }
}
