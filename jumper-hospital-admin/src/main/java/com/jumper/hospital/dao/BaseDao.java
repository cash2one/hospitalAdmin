package com.jumper.hospital.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

import com.jumper.hospital.base.Page;

public interface BaseDao<T, PK extends Serializable> {
	/** 不会立即保存对象，取不到ID **/
	public T save(final T entity);
	/** 立即保存对象，会返回对象保存在数据库以后的记录，可以取得ID **/
	public T persist(final T entity);
	
	public void delete(final T entity);
	
	public void update(final T entity);

	public void delete(final PK id);

	public T get(final PK id);
	
	public List<T> getAll();
	
	/**
	 * 获取全部对象,支持排序.
	 * @param orderBy 排序字段
	 * @param isAsc 是否升序
	 */
	public List<T> getAll(String orderBy, boolean isAsc);

	/**
	 * 按属性查找对象列,匹配方式为相等
	 */
	public List<T> findBy(String propertyName, Object value);

	/**
	 * 按属性查找唯一匹配数据,匹配方式为相相等
	 */
	public T findUniqueBy(final String propertyName, final Object value);
	
	/**
	 * 按id列表获取对象.
	 */
	public List<T> findByIds(List<PK> ids);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String hql, final Object... values);

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录
	 */
	public int batchExecute(final String hql, final Map<String, ?> values);
	
	public int batchExecute(final String hql, final Integer[] ids);

	/**
	 * 按Criteria查询对象列表.
	 * @param criterions 数量可变的Criterion.
	 */
	public List<T> find(final Criterion... criterions);

	/**
	 * 按Criteria查询唯一对象.
	 * @param criterions 数量可变的Criterion.
	 */
	public T findUnique(final Criterion... criterions);
	
	/**
	 * 根据Criterion条件创建Criteria.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions);

	/**
	 * 初始化对象的懒加载
	 * 使用load()方法得到的仅是对象Proxy, 在传到View层前对Proxy属性进行初始化
	 * 只初始化entity的直接属姓但不会初始化延迟加载的关联集合和属性
	 * 如需初始化关联属性可实现新的函数执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
	 */
	public void initProxyProperty(Object proxyProperty);

	/**
	 * Flush当前Session.
	 */
	public void flush();

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName();
	
	/**
	 * 按Criteria分页查询.
	 * @param page 分页参数.
	 * @param criterions 数量可变的Criterion.
	 * @return 分页查询结果.附带结果列表及所有查询时的参数
	 */
	public Page<T> findPage(final Page<T> page, final Criterion... criterions);

	/**
	 * new method by criteria  to select database
	 * @param page
	 * @param c
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Page<T> findPageByCriteria(final Page page, Criteria c);

	@SuppressWarnings("rawtypes")
	public List executeNativeSql(String sql);

    public int executeNonQuerySql(String sql);

    public int executeCountSql(String sql);

}
