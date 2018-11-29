package org.yakimov.denis.countriesservice.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.models.Status;
import org.yakimov.denis.countriesservice.support.Constants;
import org.yakimov.denis.countriesservice.support.RequestDtoDeserializer;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class HttpRequester {
    private static final RequestConfig CONFIG = RequestConfig.custom().setConnectTimeout(Constants.CONNECT_TIMEOUT).setConnectionRequestTimeout(Constants.CONNECT_TIMEOUT).setSocketTimeout(Constants.CONNECT_TIMEOUT).build();

    private HttpGet get;
    private ObjectMapper mapper = new ObjectMapper();
    private CloseableHttpClient httpClient;

    @Value("service.country-url")
    private String countryServiceUrl;


    @PostConstruct
    private void init(){
        httpClient =  HttpClients.custom().setDefaultRequestConfig(CONFIG).build();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(RequestDto.class, new RequestDtoDeserializer());
        mapper.registerModule(module);
        get = new HttpGet(countryServiceUrl);
    }


    public List<RequestDto> processRequest(Collection<String> data){
        return data.stream().map(this::request).collect(Collectors.toList());
    }


    private RequestDto request(String ISOCode){
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String body = EntityUtils.toString(response.getEntity(), Constants.UTF_8);
            return mapper.readValue(body, RequestDto.class);
        } catch (IOException e) {
            return new RequestDto(String.format(Constants.ERROR, e.getLocalizedMessage()), null, ISOCode, Status.ERROR);
        }
    }
}
