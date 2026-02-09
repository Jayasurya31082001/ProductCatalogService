package dev.jayasurya.productcatalogservice.Exception;

public class ProductAlreadyExistException extends  Exception{
    public ProductAlreadyExistException(String message) {
        super(message);
    }
    public  ProductAlreadyExistException(){
        super();
    }
}
