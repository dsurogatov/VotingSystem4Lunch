package org.dsu.dao.menuitem;

import org.dsu.dao.api.AbstractCrudDAO;
import org.dsu.domain.model.MenuItem;
import org.springframework.stereotype.Repository;

@Repository
public class MenuItemDAOImpl extends AbstractCrudDAO<MenuItem> implements MenuItemDAO {

	@Override
	public void deleteRelations(Long id) {
		// do nothing
	}

}
