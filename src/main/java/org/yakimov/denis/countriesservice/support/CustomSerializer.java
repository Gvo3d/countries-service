package org.yakimov.denis.countriesservice.support;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.yakimov.denis.countriesservice.models.CountryContent;

import java.io.IOException;

public class CustomSerializer extends StdSerializer<CountryContent> {

    protected CustomSerializer(Class<CountryContent> t) {
        super(t);
    }

    public CustomSerializer(){
        this(null);
    }

    @Override
    public void serialize(CountryContent countryContent, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("countryName", countryContent.getCountryName());
        jsonGenerator.writeStringField("countryCode", countryContent.getCountryCode());
        jsonGenerator.writeStringField("fileName", countryContent.getFileName());
        jsonGenerator.writeStringField("status", countryContent.getStatus().toString());
        jsonGenerator.writeEndObject();
    }
}
