package org.xaxox.powerMap;

public class Event {

    enum From {SERVER, CLIENT}
    enum Operation {CREATE, UPDATE, DELETE}

    private From from;
    private Operation operation;
    private Object data;


    public From getFrom() {
        return from;
    }

    public void setFrom(From from) {
        this.from = from;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
