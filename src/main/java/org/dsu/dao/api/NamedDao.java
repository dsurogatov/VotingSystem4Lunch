package org.dsu.dao.api;

import java.util.List;

import org.dsu.domain.api.NamedEntity;

public interface NamedDao<I extends NamedEntity> extends CrudDao<I> {
	
	List<I> findByPage(PageProp page, SortProp sort, String findingValue);
	long countByName(String findingValue);

}
