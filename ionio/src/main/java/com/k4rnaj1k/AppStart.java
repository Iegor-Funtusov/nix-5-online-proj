package com.k4rnaj1k;

import org.slf4j.LoggerFactory;

public class AppStart {
    public static void main(String[] args) {
        BookStoreController bookStoreController = new BookStoreController();
        LoggerFactory.getLogger("info").info("Book store starting.");
        bookStoreController.start();
    }
}
