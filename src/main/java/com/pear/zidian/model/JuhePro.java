package com.pear.zidian.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2016/12/24.
 */
@Configuration
@ConfigurationProperties(locations = "classpath:juhe.properties")
public class JuhePro {
    private static String hostUrl;
    private static String key;
    private static String keyC;
    private static String query;
    private static String bs;
    private static String py;
    private static String buhz;
    private static String pyhz;
    private static String idhz;
    private static String cyQuery;
    private static String appid;
    private static String secret;
    private static String grantType;
    private static String sessionHost;

    public static String getSessionHost() {
        return sessionHost;
    }

    public static void setSessionHost(String sessionHost) {
        JuhePro.sessionHost = sessionHost;
    }

    public static String getGrantType() {
        return grantType;
    }

    public static void setGrantType(String grantType) {
        JuhePro.grantType = grantType;
    }

    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        JuhePro.secret = secret;
    }

    public static String getAppid() {
        return appid;
    }

    public static void setAppid(String appid) {
        JuhePro.appid = appid;
    }

    public static String getCyQuery() {
        return cyQuery;
    }

    public static void setCyQuery(String cyQuery) {
        JuhePro.cyQuery = cyQuery;
    }

    public static String getHostUrl() {
        return hostUrl;
    }

    public static void setHostUrl(String hostUrl) {
        JuhePro.hostUrl = hostUrl;
    }

    public static String getKey() {
        return key;
    }

    public static void setKey(String key) {
        JuhePro.key = key;
    }

    public static String getKeyC() {
        return keyC;
    }

    public static void setKeyC(String keyC) {
        JuhePro.keyC = keyC;
    }

    public static String getQuery() {
        return query;
    }

    public static void setQuery(String query) {
        JuhePro.query = query;
    }

    public static String getBs() {
        return bs;
    }

    public static void setBs(String bs) {
        JuhePro.bs = bs;
    }

    public static String getPy() {
        return py;
    }

    public static void setPy(String py) {
        JuhePro.py = py;
    }

    public static String getBuhz() {
        return buhz;
    }

    public static void setBuhz(String buhz) {
        JuhePro.buhz = buhz;
    }

    public static String getPyhz() {
        return pyhz;
    }

    public static void setPyhz(String pyhz) {
        JuhePro.pyhz = pyhz;
    }

    public static String getIdhz() {
        return idhz;
    }

    public static void setIdhz(String idhz) {
        JuhePro.idhz = idhz;
    }
}
