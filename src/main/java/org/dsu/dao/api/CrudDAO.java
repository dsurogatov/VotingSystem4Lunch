package org.dsu.dao.api;

import java.util.List;

public interface CrudDAO<I> {

	I findById(Long id);
    List<I> findByPage(PageProp page, SortProp sort);
    long count();
    I save(I instance);
    void deleteRelations(Long id);
    void delete(Long id);
    void flush();
    //void exist(Long id);
}
