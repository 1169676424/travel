package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @author ply
 * @date 2021/4/9 - 11:07
 */
public interface CategoryService {
    List<Category> findAll();
}
