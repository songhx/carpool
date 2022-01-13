package com.carpool.service.impl;

import com.github.abel533.mapper.Mapper;
import com.github.abel533.mapper.MySqlMapper;
import com.carpool.service.IBasicSetMapper;
import com.carpool.service.InMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 通用服务层抽象类
 * @author zhangzhenzhen
 * @since 2017/7/19 17:21
 */
public abstract class BasicSetServiceImpl<T> implements IBasicSetMapper<T> {
    @Autowired
    protected Mapper<T> mapper;
    @Autowired
    protected MySqlMapper<T> mySqlMapper;
    @Autowired
    protected InMapper<T> inMapper;
    @Override
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int delete(T record) {
        return mapper.delete(record);
    }

    @Override
    public int insert(T record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public T selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int selectCount(T record) {
        return mapper.selectCount(record);
    }

    @Override
    public List<T> select(T record) {
        return mapper.select(record);
    }

    @Override
    public T selectOne(T record) {
        return mapper.selectOne(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByExample(Object example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public int updateByExample(T record, Object example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByExampleSelective(T record, Object example) {
        return mapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Object example, RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example,rowBounds);
    }

    @Override
    public List<T> selectByRowBounds(T record, RowBounds rowBounds) {
        return mapper.selectByRowBounds(record,rowBounds);
    }
    @Override
    public int insertList(List<T> recordList) {
        if(CollectionUtils.isEmpty(recordList)) {
            return 0;
        }
        return mySqlMapper.insertList(recordList);
    }

    @Override
    public int InsertUseGeneratedKeysMapper(T t) {
        return mySqlMapper.InsertUseGeneratedKeysMapper(t);
    }

    @Override
    public List<T> selectByInKey(List<T> t)  {
        return inMapper.selectByInKey(t);
    }
}
