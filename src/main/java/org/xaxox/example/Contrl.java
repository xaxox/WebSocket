package org.xaxox.example;

import org.xaxox.powerMap.Init;
import org.xaxox.powerMap.OnCreate;
import org.xaxox.powerMap.PowerMap;
import org.xaxox.powerMap.PowerMapListener;

import java.util.ArrayList;
import java.util.List;

@PowerMapListener
public class Contrl {


    @Init
    public void init(PowerMap powerMap){

        ArrayList<People> peoples = new ArrayList<>();

        peoples.add(new People());
        peoples.add(new People());
        peoples.add(new People());
        peoples.add(new People());


        powerMap.put("friends", peoples);
        powerMap.put("me", new People());
    }

    @OnCreate(place = "me")
    public void onCreateMe(People people){

    }

    @OnCreate(place = "friends")
    public void onCreateFriend(List<People> peoples){

    }





}
