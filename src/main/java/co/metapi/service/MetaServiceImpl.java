package co.metapi.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.metapi.repository.MetaRepository;

@Service
public class MetaServiceImpl implements MetaService {
	
	@Autowired
	private MetaRepository metaRepository;

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> findAll(String collection) throws Exception {
		return metaRepository.findAll(collection);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public Map get(String collection, String id) throws Exception {
		return metaRepository.get(collection, id);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void create(String collection, Map object) throws Exception {
		 metaRepository.create(collection, object);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public void update(String collection, String id, Map object) throws Exception {
		metaRepository.update(collection, id, object);
	}

	@Override
	public void delete(String collection, String id) throws Exception {
		metaRepository.delete(collection, id);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean exist(String collection, Map object) throws Exception {
		return metaRepository.exist(collection, object);
	}

	@Override
	@SuppressWarnings("rawtypes")
	public List<Map> query(String collection, Map query) throws Exception {
		return metaRepository.query(collection,query);
	}

	

}
