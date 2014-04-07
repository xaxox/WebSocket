package org.xaxox.convert;

public class StringConvertor implements Convertor<String>{

    @Override
    public String convert(Object o) throws ConversionException {
        Class<?> valueClass = o.getClass();
        if(String.class.equals(valueClass)){
            return (String)o;
        } else {
            return o.toString();
        }
    }
}
