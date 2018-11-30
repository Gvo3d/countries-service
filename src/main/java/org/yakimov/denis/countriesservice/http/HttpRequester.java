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
import org.yakimov.denis.countriesservice.dtos.FileDto;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.models.Status;
import org.yakimov.denis.countriesservice.support.Constants;
import org.yakimov.denis.countriesservice.support.DataProcessor;
import org.yakimov.denis.countriesservice.support.RequestDtoDeserializer;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Component
public class HttpRequester {
    private static final Logger LOGGER = Logger.getLogger(HttpRequester.class);
    private static final RequestConfig CONFIG = RequestConfig.custom().setConnectTimeout(Constants.CONNECT_TIMEOUT).setConnectionRequestTimeout(Constants.CONNECT_TIMEOUT).setSocketTimeout(Constants.CONNECT_TIMEOUT).build();

    private ObjectMapper mapper = new ObjectMapper();
    private CloseableHttpClient httpClient;

    @Value("${service.countryurl}")
    private String countryServiceUrl;


    @PostConstruct
    private void init(){
        httpClient =  HttpClients.custom().setDefaultRequestConfig(CONFIG).build();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(RequestDto.class, new RequestDtoDeserializer());
        mapper.registerModule(module);
    }


    public Map<RequestDto, FileDto> processRequest(List<FileDto> data){
        Map<RequestDto, FileDto> result = new HashMap<>();

        for (FileDto current: data){
            for (String currentIsoCode: DataProcessor.getIsoCodesFromFileData(current)){
                Map.Entry<RequestDto, FileDto> requestData = this.request(currentIsoCode, current);
                result.put(requestData.getKey(), requestData.getValue());
            }
        }

        return result;
    }


    private Map.Entry<RequestDto, FileDto> request(String isoCode, FileDto data){
        try {
            String url = countryServiceUrl+isoCode;
            HttpGet get = new HttpGet(url);
            LOGGER.info(String.format(Constants.REQUESTING, url));
            CloseableHttpResponse response = httpClient.execute(get);
            String body = EntityUtils.toString(response.getEntity(), Constants.UTF_8);
            return new AbstractMap.SimpleImmutableEntry<>(mapper.readValue(body, RequestDto.class), data);
        } catch (IOException e) {
            return new AbstractMap.SimpleImmutableEntry<>(new RequestDto(String.format(Constants.ERROR, e.getLocalizedMessage()), null, isoCode, Status.ERROR), data);
        }
    }
}
