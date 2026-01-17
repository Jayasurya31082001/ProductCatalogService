package dev.jayasurya.productcatalogservice.Service;

import dev.jayasurya.productcatalogservice.DTO.FakeStoreDTO;
import dev.jayasurya.productcatalogservice.DTO.ProductDTO;
import dev.jayasurya.productcatalogservice.Model.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements  IProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {

        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
                FakeStoreDTO.class,
                id);

        if (fakeStoreDTOResponseEntity.hasBody() &&
                (fakeStoreDTOResponseEntity.getStatusCode().equals( HttpStatusCode.valueOf(200)) || fakeStoreDTOResponseEntity.getStatusCode().equals( HttpStatusCode.valueOf(201)))) {
            FakeStoreDTO fakeStoreDTO = fakeStoreDTOResponseEntity.getBody();
            return fakeStoreDTO.toProductEntity();
        }
        return  null;
    }


    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<List<FakeStoreDTO>> productResponseEntity =
                restTemplate.exchange("https://fakestoreapi.com/products",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<FakeStoreDTO>>(){}
                );


        if(productResponseEntity.hasBody() &&
                (productResponseEntity.getStatusCode().equals( HttpStatusCode.valueOf(200))||productResponseEntity.getStatusCode().equals( HttpStatusCode.valueOf(201)))){
            List<Product> products = new ArrayList<>();
            for(FakeStoreDTO productDTO: productResponseEntity.getBody()){
                products.add(productDTO.toProductEntity());
            }
            return products;
        }
        return null;

    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }
}
