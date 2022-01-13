package com.carpool.service;

import com.carpool.service.impl.InProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 支持in查询接口
 * @author zhangzhenzhen
 * @since 2017/7/19 17:21
 */
public interface InMapper<T> {

    /**
     * 根据主键ids查询
     * @param list
     * @return
     */
    @SelectProvider(type = InProvider.class, method = "dynamicSQL")
    List<T> selectByInKey(List<T> list);

}