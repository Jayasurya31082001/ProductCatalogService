package dev.jayasurya.productcatalogservice.Client;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

import java.net.URI;

import static java.util.Objects.nonNull;

@Component
public class FakeStoreAPIClient {
    @Autowired
    private RestTemplate restTemplate;
    
    public FakeStoreAPIClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T> getRequest(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor, uriVariables);
    }

    public <T> ResponseEntity<T> postRequest(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.POST, requestCallback, responseExtractor, uriVariables);
    }

    public <T> ResponseEntity<T> putRequest(String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, HttpMethod.PUT, requestCallback, responseExtractor, uriVariables);
    }



    public boolean deleteRequest(String baseUrl, Object... uriVariables) {
        try {
            ResponseEntity<Void> resp = restTemplate.exchange(
                    baseUrl,
                    HttpMethod.DELETE,
                    HttpEntity.EMPTY,
                    Void.class,
                    uriVariables
            );


            return resp.getStatusCode().is2xxSuccessful();

        } catch (HttpClientErrorException.NotFound ex) {

            return false;

        } catch (HttpStatusCodeException ex) {

            return false;
        }
    }

    public boolean validateResponse(ResponseEntity responseEntity) {
        if(responseEntity.hasBody() &&
                (responseEntity.getStatusCode().equals( HttpStatusCode.valueOf(200)) ||responseEntity.getStatusCode().equals( HttpStatusCode.valueOf(201)))){
            return true;
        }
        return  false;
    }
    
    
}
