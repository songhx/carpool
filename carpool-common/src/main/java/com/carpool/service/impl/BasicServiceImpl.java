package com.carpool.service.impl;

import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.carpool.page.PageVo;
import com.carpool.service.BasicService;
import com.carpool.service.IBasicMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * 通用服务层抽象类
 * @author bjlisuxiao
 * @since 2017/3/13 17:21
 */
public abstract class BasicServiceImpl<T> implements BasicService<T> {

    @Autowired
    protected Mapper<T> mapper;
    @Autowired(required = false)
    protected IBasicMapper<T> basicMapper;

    @Override
    public T queryOne(T record) {
        return mapper.selectOne(record);
    }

    @Override
    public List<T> queryAll(T record) {
        return mapper.select(record);
    }

    @Override
    public int queryCount(T record) {
        return mapper.selectCount(record);
    }

    @Override
    public T queryByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int addSelective(T record) {
        return mapper.insertSelective(record);
    }

    @Override
    public int delete(T record) {
        return mapper.delete(record);
    }

    @Override
    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
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
    public int queryCountByExample(T example) {
        return mapper.selectCountByExample(example);
    }

    @Override
    public List<T> queryByExample(T example) {
        return mapper.selectByExample(example);
    }

    /**
     * 根据参数及分页信息查询列表
     * @param page 分页信息
     * @param params 查询参数
     * @return PageVo 返回对象
     */
    public <K, V> PageVo selectPageByMap(PageVo page, Map<K, V> params) {
        PageHelper.startPage(page.getStart(), page.getLimit(), true, false); //设置分页

        List result = basicMapper.selectPageByMap(params);
        PageInfo pageInfo = new PageInfo(result);

        page.setTotal(pageInfo.getTotal());
        page.setStart(pageInfo.getPageNum());
        page.setLimit(pageInfo.getPageSize());
        page.setList(result);
        page.setRs(1);//默认查询结果成功
        return page;
    }

}
