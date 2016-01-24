package org.dsu.service.api;

import java.util.List;

import org.dsu.dao.api.NamedDao;
import org.dsu.dao.api.PageProp;
import org.dsu.dao.api.SortProp;
import org.dsu.domain.api.NamedEntity;
import org.dsu.dto.api.BaseNamedDTO;
import org.dsu.dto.converter.ConverterUtils;
import org.dsu.json.PageJson;
import org.springframework.util.Assert;

public abstract class AbstractNamedService<I extends BaseNamedDTO, E extends NamedEntity> extends AbstractCrudService<I, E> implements NamedService<I> {

	protected NamedDao<E> getNamedDao() {
		return (NamedDao<E>) getDao();
	}
	
	@Override
	public List<I> findByPage(PageJson page) {
		Assert.notNull(page);
		
		return ConverterUtils.toDTOList(getNamedDao().findByPage(
				new PageProp(page.getStart(), page.getSize()), 
				new SortProp(page.getSortingField(), page.isSortAsc()), 
				page.getFindingValue())
				);
	}
	
	@Override
	public long countByName(String findingValue) {
		return getNamedDao().countByName(findingValue);
	}
	
}
