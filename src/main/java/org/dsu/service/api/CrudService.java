/**
 * 
 */
package org.dsu.service.api;

import java.util.List;

import org.dsu.json.PageJson;

/**
 * @author nescafe
 * Define base service method for crud's dao
 */
public interface CrudService<I> {

	I findById(Long id);
    List<I> findByPage(PageJson page);
    long count();
    I create(I instance);
    I update(I instance);
    void delete(Long id);
    
}
