package org.yakimov.denis.countriesservice.support;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;

import java.util.function.Consumer;

public class MessagesConsumer implements Consumer<JsonNode> {
    @Getter
    private String message = null;

    @Override
    public void accept(JsonNode jsonNode) {
        if (message!=null) {
            message = jsonNode.asText();
        }
    }
}
