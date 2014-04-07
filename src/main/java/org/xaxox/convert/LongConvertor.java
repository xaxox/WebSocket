package org.xaxox.convert;


public class LongConvertor implements Convertor<Long> {

    @Override
    public Long convert(Object o) throws ConversionException {
        Class<?> valueClass = o.getClass();

        if (Long.class.equals(valueClass)) {
            return (Long) o;
        } else {
            try {
                return Long.valueOf((String) o);
            } catch (NumberFormatException e) {
                throw new ConversionException("Could not parse the value = " + o, e);
            }
        }
    }
}
