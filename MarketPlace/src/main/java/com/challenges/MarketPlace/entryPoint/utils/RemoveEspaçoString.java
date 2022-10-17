package com.challenges.MarketPlace.entryPoint.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.fasterxml.jackson.core.JsonToken.VALUE_STRING;

@Component
public class RemoveEspaçoString extends JsonDeserializer<String> {


    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        return jsonParser.hasToken(VALUE_STRING)? jsonParser.getText().trim() : jsonParser.getText();

    }
}
