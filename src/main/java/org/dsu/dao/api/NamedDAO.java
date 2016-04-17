package org.dsu.dao.api;

import java.util.List;

import org.dsu.domain.api.NamedEntity;

public interface NamedDAO<I extends NamedEntity> extends CrudDAO<I> {
	
	List<I> findByPage(PageProp page, SortProp sort, String findingValue);
	long countByName(String findingValue);

}
