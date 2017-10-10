package com.jumper.hospital.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.service.BaseService;
import com.jumper.hospital.utils.BeanUtils;

@Transactional
public abstract class BaseServiceImpl<T, PK extends java.io.Serializable> implements BaseService<T, PK> {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public abstract BaseDao<T, PK> getBaseDAO();

	public List<T> findAll() {
		return getBaseDAO().getAll();
	}

	public int bulkUpdate(String hql, Object[] values) {
		return getBaseDAO().batchExecute(hql, values);
	}

	public T get(PK id) {
		return getBaseDAO().get(id);
	}
	
	public void delete(PK id){
		getBaseDAO().delete(id);
	}
	
	public void delete(T entity){
		getBaseDAO().delete(entity);
	}
	
	public T save(final T entity) {
		return getBaseDAO().save(entity);
	}

	public List<T> find(final Criterion... criterions) {
		return getBaseDAO().find(criterions);
	}

	public Page<T> findPage(Page<T> page, Criterion... criterions) {
		return getBaseDAO().findPage(page, criterions);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String convertVO(List<T> dataList, Class c) {
		try {
			if(dataList == null || dataList.size() < 1){
				return "";
			}
			List list = new ArrayList();
			for(T t : dataList){
				BeanUtils.copy(t, c);
				list.add(c);
			}
			return JSONArray.toJSONString(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * list or set is empty
	 * @param list
	 * @return if not empty return true otherwise false
	 */
	public boolean isNotEmpty(Collection<T> list){
		if(list != null && !list.isEmpty()){
			return true;
		}
		return false;
	}
	
	public void edit(final T entity) {
		 getBaseDAO().update(entity);
	}

	public T persist(T entity) {
		return getBaseDAO().persist(entity);
	}
	
	
}
