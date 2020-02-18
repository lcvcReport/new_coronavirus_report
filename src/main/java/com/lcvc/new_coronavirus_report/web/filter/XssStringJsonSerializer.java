package com.lcvc.new_coronavirus_report.web.filter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;
public class XssStringJsonSerializer extends JsonSerializer<String> {
    @Override
    public Class<String> handledType() {
        return String.class;
    }
    //将对象格式转化为JSON时，从服务端传递到客户端时调用
    @Override
    public void serialize(String value, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        if (value != null) {
            String encodedValue = StringEscapeUtils.escapeHtml4(value);
            jsonGenerator.writeString(encodedValue);
        }
    }
}