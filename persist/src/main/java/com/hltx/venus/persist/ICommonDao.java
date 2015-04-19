package com.hltx.venus.persist;


import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;



public interface ICommonDao<T> {
	/**
	 * save 实体对象;
	 * @param entity 实体对象;
	 * @return 
	 */
	void save(T entity);

	void update(T entity);

	void update(String sql, Object...values); 

	void saveOrUpdate(T entity);
	
	void saveOrUpdateAll(Collection<T> entities) ;
	

	T findObjectById(Serializable id);
	

	void deleteObjectByIds(Serializable... ids);
	

	void deleteObjectByCollection(Collection<T> collection);
	
	
	T findObjectByHql(String whereSql,Object...params);

	
	Object findObjectBySql(String sql, Object...params);
	
	Collection<T> findCollectionByHql(String whereSql,
			final Object[] params, LinkedHashMap<String, String> orderMap);
	

	@SuppressWarnings("rawtypes")
	List findCollectionBySql(String sql, final Object...params);
}
