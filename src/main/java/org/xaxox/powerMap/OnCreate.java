package org.xaxox.powerMap;


public @interface OnCreate {

    boolean onlyClientChanges() default false;

    String place() default "";
}
