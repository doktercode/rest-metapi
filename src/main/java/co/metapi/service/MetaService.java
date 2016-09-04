package co.metapi.service;

import java.util.List;
import java.util.Map;

public interface MetaService {

	@SuppressWarnings("rawtypes")
	public List<Map> findAll(String collection) throws Exception;
	@SuppressWarnings("rawtypes")
	public Map get(String collection,String id) throws Exception;
	@SuppressWarnings("rawtypes")
	public void create(String collection, Map object) throws Exception;
	@SuppressWarnings("rawtypes")
	public void update(String collection, String id, Map object) throws Exception;
	public void delete(String collection, String id) throws Exception;
	@SuppressWarnings("rawtypes")
	public boolean exist(String collection, Map object) throws Exception;
	@SuppressWarnings("rawtypes")
	public List<Map> query(String collection, Map query) throws Exception;
	
}
