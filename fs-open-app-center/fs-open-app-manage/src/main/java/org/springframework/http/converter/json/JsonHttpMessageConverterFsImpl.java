package org.springframework.http.converter.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonHttpMessageConverterFsImpl extends org.springframework.http.converter.json.GsonHttpMessageConverter {

    public JsonHttpMessageConverterFsImpl() {
        super();
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(java.util.Date.class, new JsonSerializer<Date>() {
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.getTime());
            }

        }).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.sql.Date.class, new JsonSerializer<Date>() {
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
                return new JsonPrimitive(src.getTime());
            }
        }).setDateFormat(DateFormat.LONG);
        gb.registerTypeAdapter(java.util.Date.class, new JsonDeserializer<Date>() {
            final DateFormat df =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
                final JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonElement;
                if (jsonPrimitive.isNumber()) {
                    return new Date(jsonElement.getAsLong());
                } else if (jsonPrimitive.isString()) {
                    synchronized (df) {
                        try {
                            return df.parse(jsonElement.getAsString());
                        } catch (ParseException e) {
                            throw new JsonParseException(e);
                        }
                    }
                } else {
                    throw new JsonParseException(jsonElement.toString() + " is illegal date");
                }
            }
        });
        Gson gson = gb.create();
        setGson(gson);
    }
}
