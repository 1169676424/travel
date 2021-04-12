package cn.itcast.travel.controller;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ply
 * @date 2021/4/9 - 10:34
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping(value = "/findAll",method = RequestMethod.GET)
    public List<Category> findAll(){
        Jedis jedis = JedisUtil.getJedis();
        Set<Tuple> category = jedis.zrangeWithScores("category", 0, -1);

        List<Category> all = null;
        if (category == null || category.size() <= 0) {
            //redis中没有数据从mysql去查
            all = categoryService.findAll();

            //存储在redis中
            for (Category category1 : all){
                jedis.zadd("category",category1.getCid(),category1.getCname());
            }
        }else {
            //redis存在直接转换类型返回即可
             all = new ArrayList<>();
            for (Tuple tuple : category) {
                Category category1 = new Category();
                category1.setCid((int) tuple.getScore());
                category1.setCname(tuple.getElement());
                all.add(category1);
            }
        }
            JedisUtil.close(jedis);
            return all;
        }



}
