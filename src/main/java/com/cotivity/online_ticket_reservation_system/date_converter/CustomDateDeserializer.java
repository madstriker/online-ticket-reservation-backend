package com.cotivity.online_ticket_reservation_system.date_converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateDeserializer extends StdDeserializer<LocalDate> {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CustomDateDeserializer() {
        this(null);
    }

    public CustomDateDeserializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public LocalDate deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException {
        String date = jsonparser.getText();
        LocalDate dateTime = LocalDate.parse(date, formatter);
        return dateTime;
    }
}
