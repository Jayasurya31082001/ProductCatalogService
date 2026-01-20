package dev.jayasurya.productcatalogservice.Service;

import dev.jayasurya.productcatalogservice.Client.FakeStoreAPIClient;
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

    private FakeStoreAPIClient fakeStoreAPIClient;

public  FakeStoreProductService(FakeStoreAPIClient fakeStoreAPIClient) {
        this.fakeStoreAPIClient = fakeStoreAPIClient;
    }



    @Override
    public Product getProductById(Long id) {

        ResponseEntity<FakeStoreDTO> fakeStoreDTOResponseEntity = fakeStoreAPIClient.getRequest("https://fakestoreapi.com/products/{id}",
                FakeStoreDTO.class,
                id);

        if (fakeStoreAPIClient.validateResponse(fakeStoreDTOResponseEntity)) {
            FakeStoreDTO fakeStoreDTO = fakeStoreDTOResponseEntity.getBody();
            return fakeStoreDTO.toProductEntity();
        }
        return  null;
    }


    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreDTO[]> productResponseEntity =
        fakeStoreAPIClient.getRequest("https://fakestoreapi.com/products",
                FakeStoreDTO[].class
        );



        if(fakeStoreAPIClient.validateResponse(productResponseEntity)){
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

          FakeStoreDTO fakeStoreDTO = product.toFakeStoreDTO();
            ResponseEntity<FakeStoreDTO> responseEntity = fakeStoreAPIClient.postRequest("https://fakestoreapi.com/products",
                     fakeStoreDTO,
                    FakeStoreDTO.class
            );
            if(fakeStoreAPIClient.validateResponse(responseEntity)){
                FakeStoreDTO createdFakeStoreDTO = responseEntity.getBody();
                return createdFakeStoreDTO.toProductEntity();
            }
            return null;
    }

    @Override
    public Product updateProductById(Long id, Product product) {
        FakeStoreDTO fakeStoreDTO = product.toFakeStoreDTO();
        ResponseEntity<FakeStoreDTO> responseEntity = fakeStoreAPIClient.putRequest("https://fakestoreapi.com/products/{id}",
                fakeStoreDTO,
                FakeStoreDTO.class,
                id
        );
        if(fakeStoreAPIClient.validateResponse(responseEntity)){
            FakeStoreDTO updatedFakeStoreDTO = responseEntity.getBody();
            return updatedFakeStoreDTO.toProductEntity();
        }
        return null;
    }

}
