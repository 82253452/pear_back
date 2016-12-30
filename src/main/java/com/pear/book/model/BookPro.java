package com.pear.book.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2016/12/29.
 */
@Configuration
@ConfigurationProperties(locations = "classpath:book.properties")
public class BookPro {
    private static String hostUrl;
    private static String key;
    private static String keyC;
    private static String bookContent;
    private static String bookList;
    private static String bookCat;
    private static String appid;
    private static String secret;

    public static String getHostUrl() {
        return hostUrl;
    }

    public static void setHostUrl(String hostUrl) {
        BookPro.hostUrl = hostUrl;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        BookPro.key = key;
    }

    public static String getKeyC() {
        return keyC;
    }

    public static void setKeyC(String keyC) {
        BookPro.keyC = keyC;
    }

    public static String getBookContent() {
        return bookContent;
    }

    public static void setBookContent(String bookContent) {
        BookPro.bookContent = bookContent;
    }

    public static String getBookList() {
        return bookList;
    }

    public static void setBookList(String bookList) {
        BookPro.bookList = bookList;
    }

    public static String getBookCat() {
        return bookCat;
    }

    public static void setBookCat(String bookCat) {
        BookPro.bookCat = bookCat;
    }

    public static String getAppid() {
        return appid;
    }

    public static void setAppid(String appid) {
        BookPro.appid = appid;
    }

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        BookPro.secret = secret;
    }
}
