package com.carpool.service;

import com.github.abel533.mapper.Mapper;

import java.util.List;
import java.util.Map;

public interface IBasicMapper<T> extends Mapper<T> {

	public <K,V> List<T> selectPageByMap(Map<K, V> map);
	
}
