/**
 * 
 */
package org.dsu.dao.api;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * @author nescafe Abstract class for the base implementation of CrudDao
 */
@Repository
public abstract class AbstractCrudDAO<I> implements CrudDAO<I> {

	//private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCrudDao.class);

	@PersistenceContext
	protected EntityManager entityManager;

	private Class<I> persistentClass;

	@SuppressWarnings("unchecked")
	protected Class<I> getPersistentClass() {
		if (persistentClass == null) {
			this.persistentClass = (Class<I>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return persistentClass;
	}

	@Override
	public I findById(Long id) {
		I instance = (I) entityManager.find(getPersistentClass(), id);
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<I> findByPage(PageProp page, SortProp sort) {
		if (page == null || page.getFirstResult() < 0 || page.getMaxResult() < 0) {
			return new ArrayList<>();
		}
		
		String sqlQuery = " SELECT o FROM " + getPersistentClass().getName() + " o ";
		if (sort != null && StringUtils.isNotBlank(sort.getColumnName())) {
			sqlQuery += " ORDER BY o." + sort.getColumnName() + (sort.isAsc() ? " ASC " : " DESC ");
		}
		
		Query query = entityManager.createQuery(sqlQuery);
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getMaxResult());
		List<I> results = query.getResultList();
		return results;
	}

	@Override
	public I save(I transientInstance) {
		transientInstance = entityManager.merge(transientInstance);
		flush();
		return transientInstance;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long count() {
		List<Long> res = entityManager.createQuery("select count(*) from " + getPersistentClass().getName() + " as clazz").getResultList();
		return res.iterator().next().longValue();
	}

	@Override
	public void delete(Long id) {
		I instance = findById(id);
		if (instance != null) {
			entityManager.remove(instance);
		}
	}
	
	@Override
	public void flush() {
		entityManager.flush();
	}
}
