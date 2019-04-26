package com.huskyshare.backend.exception;

public class JedisUncloseException extends Exception {
    @Override
    public void printStackTrace() {
        super.printStackTrace();
        System.err.println("\nHave to use finally block to close Jedis client");
    }
}
