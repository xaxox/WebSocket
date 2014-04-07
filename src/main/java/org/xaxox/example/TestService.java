package org.xaxox.example;


import org.xaxox.serverObject.MethodControl;
import org.xaxox.serverObject.ServerControl;

@ServerControl
public class TestService {

    @MethodControl()
    public String main(int a, int b, String c){
        return  "Pizda" + a + b + c;
    }

    @MethodControl()
    public Integer test(Integer a){
        return  a*a*a;
    }
}
