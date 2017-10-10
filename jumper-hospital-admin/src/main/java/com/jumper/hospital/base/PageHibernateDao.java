package com.jumper.hospital.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.util.Assert;

import com.jumper.hospital.utils.ReflectionUtils;

/**
 * 封装SpringSide扩展功能的Hibernat DAO泛型基类.
 * 扩展功能包括分页查询,按属性过滤条件列表查�?可在Service层直接使用也可以扩展泛型DAO子类使用,见两个构造函数的注释.
 * @param <T> DAO操作的对象类
 * @param <PK> 主键类型
 */
public class PageHibernateDao<T, PK extends Serializable> extends BaseHibernateDao<T, PK> {
	
	/**
	 * 用于Dao层子类使用的构�?函数. 通过子类的泛型定义取得对象类型Class. eg. public class UserDao extends
	 * HibernateDao<User, Long>{ }
	 */
	
	public PageHibernateDao(Class<T> entityClass) {
		super(entityClass);
	}
	
	
	public PageHibernateDao() {
	}


	/**
	 * 按Criteria分页查询.
	 * @param page 分页参数.
	 * @param criterions 数量可变的Criterion.
	 * @return 分页查询结果.附带结果列表及所有查询时的参�?
	 */
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

	
	/**
	 * 执行count查询获得本次Criteria查询�?��得的对象记录�?
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected int countCriteriaResult(final Criteria c) {
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


	/**
	 * 按HQL分页查询.
	 * @param page 分页参数.不支持其中的orderBy参数.
	 * @param hql hql语句.
	 * @param values 数量可变的查询参�?按顺序绑�?
	 * @return 分页查询结果, 附带结果列表及所有查询时的参�?
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page findPage(final Page page, final String hql, final Object... values) {
		Assert.notNull(page, "page不能为空");
		Query q = createQuery(hql, values);
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}
	/**
	 * 按HQL分页查询.
	 * @param page 分页参数.
	 * @param hql hql语句.
	 * @param values 命名参数,按名称绑�?
	 * @return 分页查询结果, 附带结果列表及所有查询时的参�?
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page<T> findPage(final Page<T> page, final String hql,final Map<String, ?> values) {
		Assert.notNull(page, "page不能为空");
		Query q = createQuery(hql, values);
		if (page.isAutoCount()) {
			long totalCount = countHqlResult(hql, values);
			page.setTotalCount(totalCount);
		}
		setPageParameter(q, page);
		List result = q.list();
		page.setResult(result);
		return page;
	}
	/**
	 * 设置分页参数到Query对象,辅助函数.
	 */
	protected Query setPageParameter(final Query q, final Page<T> page) {
		q.setFirstResult(page.getFirst() - 1);
		q.setMaxResults(page.getPageSize());
		return q;
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

	/**
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Object... values) {
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "FROM");
		fromHql = StringUtils.substringBefore(fromHql, "ORDER BY");

		String countHql = "select count(*) " + fromHql;
		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:" + countHql, e);
		}
	}

	/**
	 * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询.
	 */
	protected long countHqlResult(final String hql, final Map<String, ?> values) {
		String fromHql = hql;
		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;

		try {
			Long count = findUnique(countHql, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}
	
	/**
	 * new method by criteria  to select database
	 * @param page
	 * @param c
	 * @return
	 */
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
	 * 判断对象的属性在数据库内是否唯一
	 */
	public boolean isPropertyUnique(final String propertyName, final Object newValue, final Object oldValue) {
		if (newValue == null || newValue.equals(oldValue)) {
			return true;
		}
		Object object = findUniqueBy(propertyName, newValue);
		return (object == null);
	}
}
