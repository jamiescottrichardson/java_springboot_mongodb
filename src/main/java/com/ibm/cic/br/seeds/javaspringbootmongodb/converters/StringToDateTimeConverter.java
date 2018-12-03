package com.ibm.cic.br.seeds.javaspringbootmongodb.converters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@ReadingConverter
public class StringToDateTimeConverter implements Converter<String, Date> {

    private static final Logger logger = LogManager.getLogger(StringToDateTimeConverter.class.getName());

    @Override
    public Date convert(String source) {
        Date date = new Date(Long.MIN_VALUE);
        if (Objects.nonNull(source)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = formatter.parse(source);
            } catch (java.text.ParseException pex) {
                System.err.println(String.format("Error: %s", pex.getMessage()));
                logger.error("Error: %s", pex.getMessage());
            }
        }
        return date;
    }
}