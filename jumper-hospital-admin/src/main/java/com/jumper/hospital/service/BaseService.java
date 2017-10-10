package com.jumper.hospital.service;

import java.util.List;

import org.hibernate.criterion.Criterion;

import com.jumper.hospital.base.Page;

public abstract interface BaseService<T, PK extends java.io.Serializable> {

	public List<T> findAll();

	public int bulkUpdate(String hql, Object[] values);

	public T get(PK id);
	
	public void delete(PK id);
	
	public void delete(T entity);
	
	public T save(final T entity);
	
	public T persist(T entity);

	public List<T> find(final Criterion... criterions);

	public Page<T> findPage(Page<T> page, Criterion... criterions);
	
	/**
	 * vo转换方法
	 * @param dataList 数据源
	 * @param O 转换后对象
	 * @return String 转换后的数据json格式
	 */
	@SuppressWarnings("rawtypes")
	public String convertVO(List<T> dataList, Class c);
	
	public void edit(final T entity);



}
