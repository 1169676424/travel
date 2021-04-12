package cn.itcast.travel.controller;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteImgService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.SellerService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ply
 * @date 2021/4/9 - 15:56
 */
@RestController
@RequestMapping("/route")
public class RouteController {

    @Resource
    private RouteService routeService;

    @Resource
    private RouteImgService routeImgService;

    @Resource
    private SellerService sellerService;

    @RequestMapping(value = "/pageQuery",method = RequestMethod.GET)
    public PageBean<Route> pageQuery(HttpServletResponse response, HttpServletRequest request){
        //1.接收参数
        String cid = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String size = request.getParameter("size");

        //2.处理参数
        int currentPage = 1;//当前页,如果不传递，默认查询第一页
        if (!StringUtils.isEmpty(currentPageStr)){
            currentPage = Integer.parseInt(currentPageStr) <= 0 ? 1 : Integer.parseInt(currentPageStr);
        }

        int pageSize = 5;//每页显示条数，如果不传递，默认每页显示5条记录
        if (!StringUtils.isEmpty(size)){
            pageSize = Integer.parseInt(size) <= 0 ? 5 : Integer.parseInt(size);
        }

        //3. 调用service查询PageBean对象
        PageBean<Route> routePageBean = routeService.pageQuery(cid, currentPage, pageSize);
        return routePageBean;
    }

    @RequestMapping(value = "/findOne",method = RequestMethod.GET)
    public Route findOne(String rid){
        //查询路线的基本信息
        Route route = routeService.findOne(rid);

        //查询路线的风景图
        List<RouteImg> byRid = routeImgService.findByRid(rid);
        route.setRouteImgList(byRid);

        //查询商家信息
        Integer sid = route.getSid();
        Seller bySid = sellerService.findBySid(sid);
        route.setSeller(bySid);

        //更新本线路的查询次数
        routeService.updateCount(rid);
        return route;
    }

    //查询查看次数最多的5条数据
    @RequestMapping(value = "/findList",method = RequestMethod.GET)
    public List<Route> findList() {
        List<Route> routeList = routeService.findList();
        return routeList;
    }
}
