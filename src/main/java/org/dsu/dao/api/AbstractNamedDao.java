package org.dsu.dao.api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.dsu.domain.api.NamedEntity;

public abstract class AbstractNamedDao<I extends NamedEntity> extends AbstractCrudDao<I> implements NamedDao<I> {

	@SuppressWarnings("unchecked")
	@Override
	public List<I> findByPage(PageProp page, SortProp sort, String findingValue) {
		if (page == null || page.getFirstResult() < 0 || page.getMaxResult() < 0) {
			return new ArrayList<>();
		}
		
		if(StringUtils.isEmpty(findingValue)) {
			return findByPage(page, sort);
		}
		
		String sqlQuery = " SELECT o FROM " + getPersistentClass().getName() + " o \n" +
				"WHERE o.name LIKE :value ";
		if (sort != null && StringUtils.isNotBlank(sort.getColumnName())) {
			sqlQuery += "\nORDER BY o." + sort.getColumnName() + (sort.isAsc() ? " ASC " : " DESC ");
		}
		
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("value", "%" + findingValue + "%");
		query.setFirstResult(page.getFirstResult());
		query.setMaxResults(page.getMaxResult());
		List<I> results = query.getResultList();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	public long countByName(String findingValue) {
		if(StringUtils.isEmpty(findingValue)) {
			return count();
		}
		
		String sqlQuery = " SELECT count(*) FROM " + getPersistentClass().getName() + " o \n" +
				"WHERE o.name LIKE :value ";
		Query query = entityManager.createQuery(sqlQuery);
		query.setParameter("value", "%" + findingValue + "%");
		List<Long> res = query.getResultList();
		return res.iterator().next().longValue();
	}

}
