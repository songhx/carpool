package com.carpool.service;


import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.MySqlMapper;

/**
 *
 * @author bjzhangzhenzhen
 * @date 2017/7/19
 */
public interface IBasicSetMapper<T> extends Mapper<T>,MySqlMapper<T>,InMapper<T> {
}
