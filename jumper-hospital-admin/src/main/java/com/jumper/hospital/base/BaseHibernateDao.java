package com.jumper.hospital.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.jumper.hospital.utils.ReflectionUtils;


public abstract class BaseHibernateDao<T, PK extends Serializable> extends HibernateDaoSupport{
	
	protected transient final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected Class<T> entityClass;
	
	public BaseHibernateDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Resource
	public void setSessionFactoryOverride(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory);  
	}
	
	public BaseHibernateDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	protected Class<T> getEntityClass() {
		return entityClass;
	}

	/**
	 * 保存新增或修改的对象.
	 */
	public T save(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().merge(entity);
		logger.debug("save entity: {}", entity);
		return entity;
	}
	/**
	 * 删除对象.
	 * @param entity 对象必须是session中的对象或含id属�?的transient对象.
	 */
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

	/**
	 * 按id删除对象.
	 */
	public void delete(final PK id) {
		Assert.notNull(id, "id不能为空");
		delete(get(id));
		logger.debug("delete entity {},id is {}", entityClass.getSimpleName(), id);
	}

	/**
	 * 按id获取对象.
	 */
	@SuppressWarnings("unchecked")
	public T get(final PK id) {
		Assert.notNull(id, "id不能为空");
		return (T)getSession().get(entityClass, id);
	}
	
	/**
	 *	获取全部对象.
	 */
	public List<T> getAll() {
		return find();
	}
	
	/**
	 * 获取全部对象,支持排序.
	 * @param orderBy 排序字段
	 * @param isAsc 是否升序
	 */
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

	/**
	 * 按属性查找对象列,匹配方式为相�?
	 */
	public List<T> findBy(String propertyName, Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return find(criterion);
	}

	/**
	 * 按属性查找唯�?��配数�?匹配方式为相相等
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueBy(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).uniqueResult();
	}
	
	/**
	 * 按属性查找唯�?���?匹配方式为相�?
	 */
	@SuppressWarnings("unchecked")
	public T findUniqueBy(final String propertyName, final Object value,LockMode lockMode) {
		Assert.hasText(propertyName, "propertyName不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) createCriteria(criterion).setLockMode(lockMode).uniqueResult();
	}

	/**
	 * 按id列表获取对象.
	 */
	public List<T> findByIds(List<PK> ids) {
		return find(Restrictions.in(getIdName(), ids));
	}

	/**
	 * 按HQL查询对象列表.
	 * @param values 数量可变的参数数组对�?按顺序绑�?
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * @param values 命名参数,按名称绑�?
	 */
	@SuppressWarnings("unchecked")
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param values 数量可变的参�?按顺序绑�?
	 */
	@SuppressWarnings("unchecked")
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * @param values 命名参数,按名称绑�?
	 */
	@SuppressWarnings("unchecked")
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 */
	public int batchExecute(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL进行批量修改/删除操作.
	 * @return 更新记录
	 */
	public int batchExecute(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
	 * @param values 数量可变的参�?按顺序绑�?
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
	 * @param values 命名参数,按名称绑�?
	 */
	public Query createQuery(final String queryString, final Map<String, ?> values) {
		Assert.hasText(queryString, "queryString不能为空");
		Query query = getSession().createQuery(queryString);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}

	/**
	 * 按Criteria查询对象列表.
	 * @param criterions 数量可变的Criterion.
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * @param criterions 数量可变的Criterion.
	 */
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

	/**
	 * 初始化对象的懒加�?
	 * 使用load()方法得到的仅是对象Proxy, 在传到View层前对Proxy属�?进行初始�?
	 * 只初始化entity的直接属姓但不会初始化延迟加载的关联集合和属�?
	 * 如需初始化关联属性可实现新的函数执行:
	 * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
	 * Hibernate.initialize(user.getDescription())，初始化User的直接属性和延迟加载的Description属�?.
	 */
	public void initProxyProperty(Object proxyProperty) {
		Hibernate.initialize(proxyProperty);
	}

	/**
	 * Flush当前Session.
	 */
	public void flush() {
		getSession().flush();
	}

	/**
	 * 为Query添加distinct transformer.
	 */
	public Query distinct(Query query) {
		query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return query;
	}

	/**
	 * 为Criteria添加distinct transformer.
	 */
	public Criteria distinct(Criteria criteria) {
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return criteria;
	}

	/**
	 * 取得对象的主键名.
	 */
	public String getIdName() {
		ClassMetadata meta = getSessionFactory().getClassMetadata(entityClass);
		return meta.getIdentifierPropertyName();
	}
	
	/**不管查出多少条记录，都只返回唯一记录，并且不报告违反唯一约束*/
	@SuppressWarnings("unchecked")
	public T findUniqueIsSafe(final String queryString, final Object... values) throws DataAccessException {
		Query queryObject = getSession().createQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		queryObject.setMaxResults(1);
		List<T> result = queryObject.list();
		if (result.size() == 0) {
			return null;
		}
		return result.get(0);
	}
}
