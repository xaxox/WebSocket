package org.xaxox.convert;


public class IntegerConvertor implements Convertor<Integer> {


    @Override
    public Integer convert(Object o) throws ConversionException {

        Class<?> valueClass = o.getClass();

        if (Integer.class.equals(valueClass)) {
            return (Integer) o;
        } else if (Long.class.equals(valueClass)) {
            return ((Long)o).intValue();
        } else {
            try {
                return Integer.valueOf((String) o);
            } catch (NumberFormatException e) {
                throw new ConversionException("Could not parse the value = " + o, e);
            }
        }
    }
}
