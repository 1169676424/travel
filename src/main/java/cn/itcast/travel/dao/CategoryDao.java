package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author ply
 * @date 2021/4/9 - 11:10
 */
public interface CategoryDao {

    List<Category> findAll();
}
