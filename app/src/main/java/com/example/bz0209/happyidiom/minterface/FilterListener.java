package com.example.bz0209.happyidiom.minterface;

import com.example.bz0209.happyidiom.entity.Animal;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */

public interface FilterListener {
    void getFilterData(List<Animal> list);// 获取过滤后的数据

}
