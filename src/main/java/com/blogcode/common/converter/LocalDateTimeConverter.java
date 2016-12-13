package com.blogcode.common.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by jojoldu@gmail.com on 2016-12-13
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp>{

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute == null? null : Timestamp.valueOf(attribute);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData == null? null : dbData.toLocalDateTime();
    }
}
