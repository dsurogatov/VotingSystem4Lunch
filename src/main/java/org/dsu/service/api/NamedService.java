package org.dsu.service.api;

public interface NamedService<I> extends CrudService<I> {

	long countByName(String findingValue);
}
