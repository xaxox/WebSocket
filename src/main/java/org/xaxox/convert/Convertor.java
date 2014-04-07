package org.xaxox.convert;


public interface Convertor<T> {

    T convert(Object o) throws ConversionException;
}
