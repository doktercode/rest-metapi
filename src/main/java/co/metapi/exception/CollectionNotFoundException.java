package co.metapi.exception;

public class CollectionNotFoundException extends Exception {

	private static final long serialVersionUID = 7928955102221707519L;
	
	public CollectionNotFoundException(String collectionName){
		super("Collection "+collectionName+" not found");
	}

}
