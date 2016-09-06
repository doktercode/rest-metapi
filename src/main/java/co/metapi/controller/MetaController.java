package co.metapi.controller;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.metapi.exception.CollectionNotFoundException;
import co.metapi.service.MetaService;

@Controller
@RequestMapping("/api")
public class MetaController {

	@Autowired
	private MetaService metaService;
	
	private static final Logger logger = Logger.getLogger(MetaController.class);

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{collection}", method = RequestMethod.GET)
	public ResponseEntity<List<Map>> listAll(@PathVariable String collection) {
		try {
			List<Map> data = metaService.findAll(collection);
			if (data == null)
				return new ResponseEntity<List<Map>>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<Map>>(data, HttpStatus.OK);
		} catch (Exception ex) {
			if(ex instanceof CollectionNotFoundException){
				return new ResponseEntity<List<Map>>(HttpStatus.NOT_FOUND);
			}else{
				logger.error("GET: /metapi/"+collection, ex);
				return new ResponseEntity<List<Map>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{collection}/{id}", method = RequestMethod.GET)
	public ResponseEntity<Map> get(@PathVariable String collection,@PathVariable String id) {
		try {
			Map data = metaService.get(collection, id);
			if (data == null) {
				return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Map>(data, HttpStatus.OK);
		} catch (Exception ex) {
			if(ex instanceof CollectionNotFoundException){
				return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
			}else{
				logger.error("GET: /metapi/"+collection+"/"+id, ex);
				return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{collection}", method = RequestMethod.POST)
	public ResponseEntity<Void> create(@PathVariable String collection, @RequestBody Map object) {
		try {
			if (metaService.exist(collection, object)) {
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			} else {
				metaService.create(collection, object);
				return new ResponseEntity<Void>(HttpStatus.CREATED);
			}
		} catch (Exception ex) {
			if(ex instanceof CollectionNotFoundException){
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}else{
				logger.error("POST: /metapi/"+collection, ex);
				return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{collection}/query", method = RequestMethod.POST)
	public ResponseEntity<List<Map>> query(@PathVariable String collection, @RequestBody Map query) {
		try {
			List<Map> data = metaService.query(collection, query);
			return new ResponseEntity<List<Map>>(data, HttpStatus.OK);
		} catch (Exception ex) {
			if(ex instanceof CollectionNotFoundException){
				return new ResponseEntity<List<Map>>(HttpStatus.NOT_FOUND);
			}else{
				logger.error("POST: /metapi/"+collection+"/query", ex);
				return new ResponseEntity<List<Map>>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{collection}/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Map> update(@PathVariable String collection, @PathVariable String id, @RequestBody Map object) {
		 try {
			 Map data = metaService.get(collection, id);
				if (data == null) {
					return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
				}
				metaService.update(collection, id, object);
				return new ResponseEntity<Map>(HttpStatus.OK);
			} catch (Exception ex) {
				if(ex instanceof CollectionNotFoundException){
					return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
				}else{
					logger.error("PUT: /metapi/"+collection+"/"+id, ex);
					return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/{collection}/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map> delete(@PathVariable String collection, @PathVariable String id) {
		 try {
			Map data = metaService.get(collection, id);
			if (data == null) {
				return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
			}
			metaService.delete(collection, id);
			return new ResponseEntity<Map>(data, HttpStatus.OK);
		} catch (Exception ex) {
			if(ex instanceof CollectionNotFoundException){
				return new ResponseEntity<Map>(HttpStatus.NOT_FOUND);
			}else{
				logger.error("DELETE: /metapi/"+collection+"/"+id, ex);
				return new ResponseEntity<Map>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

}
