package org.xaxox.convert;


import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ConversionService {

    private final static Convertor<Integer> integerConvertor =  new IntegerConvertor();
    private final static Convertor<Long> longConvertor =  new LongConvertor();

    private static final Map<Type,Convertor> convertorMap = new HashMap<Type,Convertor>(){{
        put(String.class, new StringConvertor());
        put(Integer.class, integerConvertor);
        put(int.class, integerConvertor);
        put(Long.class, longConvertor);
        put(long.class, longConvertor);
    }};


    public Object convert(Type type, Object value) throws ConversionException {
        Convertor convertor = getConvertor(type);
        return convertor.convert(value);
    }

    public Convertor getConvertor(Type type) throws ConversionException {
        Convertor convertor = convertorMap.get(type);

        if(convertor == null){
            throw new ConversionException("A convertor for this type doesn't exist");
        }

        return convertor;
    }


}
