package org.dsu.service.api;

import java.util.List;

import org.dsu.common.ExceptionType;
import org.dsu.common.VotingSystemException;
import org.dsu.dao.api.CrudDao;
import org.dsu.dao.api.PageProp;
import org.dsu.dao.api.SortProp;
import org.dsu.domain.api.IdEntity;
import org.dsu.dto.api.IdDTO;
import org.dsu.dto.converter.ConverterUtils;
import org.dsu.json.PageJson;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional
public abstract class AbstractCrudService<I extends IdDTO, E extends IdEntity> implements CrudService<I> {
	
	protected abstract CrudDao<E> getDao();

	@Override
	public I findById(Long id) {
		Assert.notNull(id);
		
		I dto = ConverterUtils.toDTO(getDao().findById(id));
		if (dto == null) {
			throw new VotingSystemException(ExceptionType.ENTITY_NOT_FINDED);
		}
		return dto;
	}

	@Override
	public List<I> findByPage(PageJson page) {
		Assert.notNull(page);
		
		return ConverterUtils.toDTOList(getDao().findByPage(
				new PageProp(page.getStart(), page.getSize()), 
				new SortProp(page.getSortingField(), page.isSortAsc()))
				);
	}
	
	@Override
	public long count(){
		 return getDao().count();
	 }
	
	@Override
	public I create(I instance) {
		Assert.notNull(instance);
		Assert.isNull(instance.getId());
		
		I i = ConverterUtils.toDTO(getDao().save(ConverterUtils.toEntity(instance)));
		return i;
	}

	@Override
	public I update(I instance) {
		Assert.notNull(instance);
		Assert.notNull(instance.getId());
		
		return ConverterUtils.toDTO(getDao().save(ConverterUtils.toEntity(instance)));
	}

	@Override
	public void delete(Long id) {
		Assert.notNull(id);

		getDao().deleteRelations(id);
		getDao().delete(id);
	}
}
