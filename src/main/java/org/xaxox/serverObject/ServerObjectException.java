package org.xaxox.serverObject;


public class ServerObjectException extends Exception {

    public ServerObjectException() {
        super();
    }

    public ServerObjectException(String message) {
        super(message);
    }

    public ServerObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerObjectException(Throwable cause) {
        super(cause);
    }

    protected ServerObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
