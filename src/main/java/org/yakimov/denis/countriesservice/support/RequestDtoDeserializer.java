package org.yakimov.denis.countriesservice.support;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.yakimov.denis.countriesservice.dtos.RequestDto;
import org.yakimov.denis.countriesservice.models.Status;

import java.io.IOException;

public class RequestDtoDeserializer extends StdDeserializer<RequestDto> {

    private RequestDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    public RequestDtoDeserializer() {
        this(null);
    }

    private MessagesConsumer consumer = new MessagesConsumer();

    @Override
    public RequestDto deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        JsonNode response = node.get(Constants.RESTRESPONSE);
        JsonNode messages = response.get(Constants.MESSAGES);
        messages.elements().forEachRemaining(consumer);
        JsonNode data = response.get(Constants.RESULT);
        String name = null;
        String code = null;
        if (data != null) {
            name = data.get(Constants.NAME).asText();
            code = data.get(Constants.ALPHACODE).asText();
        }
        return new RequestDto(consumer.getMessage(), name, code, name != null ? Status.SUCCESS : Status.NO_SUCH_COUNTRY);
    }
}
