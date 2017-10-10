package com.jumper.hospital.dao.impl;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.jumper.hospital.base.HibernateDaoSupport;
import com.jumper.hospital.base.Page;
import com.jumper.hospital.dao.BaseDao;
import com.jumper.hospital.utils.ReflectionUtils;

public abstract class BaseDaoImpl<T, PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {

	protected transient final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Class<T> entityClass;
	
	public BaseDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Resource
	public void setSessionFactoryOverride(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}
	
	public BaseDaoImpl() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	protected Class<T> getEntityClass() {
		return entityClass;
	}
	
	public T persist(final T entity){
		Assert.notNull(entity, "entity不能为空");
		getSession().save(entity);
		logger.debug("save entity: {}", entity);
		return entity;
	}

	public T save(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().merge(entity);
		logger.debug("save entity: {}", entity);
		return entity;
	}
	
	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
		logger.debug("delete entity: {}", entity);
	}
	
	public void update(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().merge(entity);
		logger.debug("merge entity: {}", entity);
	}

	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}

	@SuppressWarnings("unchecked")
	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T)getSession().get(entityClass, id);
	}
	
	public List<T> getAll() {
		return find();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(String orderBy, boolean isAsc) {
		Criteria c = createCriteria();
		if (isAsc) {
			c.addOrder(Order.asc(orderBy));
		} else {
			c.addOrder(Order.desc(orderBy));
		}
		return c.list();
	}

	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	@SuppressWarnings("unchecked")
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}

	public List<T> findByIds(List<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}
	
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}
	
	public int batchExecute(final String hql, final Integer[] ids) {
		return createQuery(hql, ids).executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	@SuppressWarnings("unchecked")
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}
	
	/**
	 * 根据Criterion条件创建Criteria.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param criterions 数量可变的Criterion.
	 */
	public Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	public void initProxyProperty(Object proxyProperty) {
		Hibernate.initialize(proxyProperty);
	}

	public void flush() {
		getSession().flush();
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param values 数量可变的参数,按顺序绑定
	 */
	public Query createQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param values 数量可变的参数,按顺序绑定
	 */
	public Query createQuery(final String queryString, final Integer[] ids) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		query.setParameterList("ids", ids);
		return query;
	}
	
	/**
	 * 根据查询sql与参数列表创建Query对象.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param values 数量可变的参数,按顺序绑定
	 */
	public SQLQuery createSqlQuery(final String queryString, final Object... values) {
		Assert.hasText(queryString, "queryString不能为空");
		SQLQuery query = getSession().createSQLQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}
	
	/**
	 * 执行count查询获得本次Criteria查询所获得的对象记录数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int countCriteriaResult(final Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		// 先把Projection、ResultTransformer、OrderBy取出�?清空三�?后再执行Count操作
		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List<CriteriaImpl.OrderEntry> orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl, "orderEntries");
			ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
			e.printStackTrace();
		}

		// 执行Count查询
		int totalCount = Integer.valueOf(c.setProjection(Projections.rowCount()).uniqueResult()+"");

		// 将之前的Projection,ResultTransformer和OrderBy条件重新设回
		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null) {
			c.setResultTransformer(transformer);
		}
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
			e.printStackTrace();
		}

		return totalCount;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPage(final Page<T> page, final Criterion... criterions) {
		Assert.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);

		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}

		setPageParameter(c, page);
		List result = c.list();
		page.setResult(result);
		return page;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPageByCriteria(final Page page, Criteria c) {
		Assert.notNull(page, "page不能为空");
		if (page.isAutoCount()) {
			int totalCount = countCriteriaResult(c);
			page.setTotalCount(totalCount);
		}
		try {
			setPageParameter(c, page);
			List result = c.list();
			page.setResult(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	/**
	 * 设置分页参数到Criteria对象,辅助函数.
	 */
	protected Criteria setPageParameter(final Criteria c, final Page<T> page) {
		c.setFirstResult(page.getFirst() - 1);
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数数组,排序字段与排序方向的个数不相");

			for (int i = 0; i < orderByArray.length; i++) {
				if (Page.ASC.equals(orderArray[i])) {
					c.addOrder(Order.asc(orderByArray[i]));
				} else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	@Override
    public int executeNonQuerySql(String sql)
    {
        return getSession().createSQLQuery(sql).executeUpdate();
    }

	/**
     * 执行本地sql语句获得标量数值列表
     */
    @SuppressWarnings("rawtypes")
	@Override
    public List executeNativeSql(String sql) {
        return getSession().createSQLQuery(sql).list();
    }

    /**
     * 执行count sql
     */
    @Override
    public int executeCountSql(String sql) {
        Assert.hasText(sql);
        BigInteger count =  (BigInteger) getSession().createSQLQuery(sql).uniqueResult();
        return count.intValue();
    }
}
