//package com.pear.zidian;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.cache.Cache;
//import com.google.common.cache.CacheBuilder;
//import com.google.common.collect.ImmutableMap;
//import com.pear.util.AES;
//import com.pear.util.HttpUtils;
//import com.pear.zidian.model.JuhePro;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.lang3.RandomStringUtils;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;
//import java.security.*;
//import java.util.Arrays;
//import java.util.Map;
//import java.util.concurrent.Callable;
//import java.util.concurrent.ExecutionException;
//
///**
// * Created by admin on 2016/12/24.
// */
//@RestController
//public class WxAuthController {
//    private static Cache<String, String> cache = CacheBuilder.newBuilder().build();
//
//    @RequestMapping(value = "/api/v1/wx/getSession", method = RequestMethod.GET, produces = "application/json")
//    public ImmutableMap createSssion(String wxCode) {
//        Map<String, Object> wxSessionMap = getWxSession(wxCode);
//        if (null == wxSessionMap) {
//            return ImmutableMap.of("errorCode", "50010");
//        }
//        //获取异常
//        if (wxSessionMap.containsKey("errcode")) {
//            return ImmutableMap.of("errorCode", "50020");
//        }
//        String wxOpenId = (String) wxSessionMap.get("openid");
//        String wxSessionKey = (String) wxSessionMap.get("session_key");
//        Long expires = Long.valueOf(String.valueOf(wxSessionMap.get("expires_in")));
//        String thirdSession = create3rdSession(wxOpenId, wxSessionKey);
//
//        return ImmutableMap.of("sessionId", thirdSession);
//    }
//
//    public ImmutableMap<String, String> checkUserInfo(String rawData, String signature, String sessionId) throws ExecutionException {
//        String wxSessionObj = cache.get(sessionId, new Callable<String>() {
//            public String call() {
//                return "";
//            }
//        });
//        if(null == wxSessionObj){
//            return ImmutableMap.of("errorCode", "40008");
//        }
//        String sessionKey = wxSessionObj.split("#")[0];
//        StringBuffer sb = new StringBuffer(rawData);
//        sb.append(sessionKey);
//
//        byte[] encryData = DigestUtils.sha1(sb.toString());
//        byte[] signatureData = signature.getBytes();
//        Boolean checkStatus = Arrays.equals(encryData, signatureData);
//        return ImmutableMap.of("data", checkStatus.toString());
//    }
//    public ImmutableMap decodeUserInfo(String encryptedData, String iv, String sessionId) throws ExecutionException {
//        Object wxSessionObj = cache.get(sessionId, new Callable<String>() {
//            public String call() {
//                return "";
//            }
//        });
//        if(null == wxSessionObj){
//            return ImmutableMap.of("errorCode", 50020);
//        }
//        String wxSessionStr = (String)wxSessionObj;
//        String sessionKey = wxSessionStr.split("#")[0];
//        try {
//            AES aes = new AES();
//            byte[] resultByte = aes.decrypt(org.apache.commons.codec.binary.Base64.decodeBase64(encryptedData), org.apache.commons.codec.binary.Base64.decodeBase64(sessionKey), org.apache.commons.codec.binary.Base64.decodeBase64(iv));
//            if(null != resultByte && resultByte.length > 0){
//                String userInfo = new String(resultByte, "UTF-8");
//                return ImmutableMap.of("data", userInfo);
//            }
//        } catch (InvalidAlgorithmParameterException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return ImmutableMap.of("errorCode", 50021);
//    }
//
//    public Map<String, Object> getWxSession(String wxCode) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("appid=").append(JuhePro.getAppid());
//        sb.append("&secret=").append(JuhePro.getSecret());
//        sb.append("&js_code=").append(wxCode);
//        sb.append("&grant_type=").append(JuhePro.getGrantType());
//        String res = HttpUtils.get(JuhePro.getSessionHost() + "?" + sb.toString());
//        if (res == null || res.equals("")) {
//            return null;
//        }
//        return JSON.parseObject(res, Map.class);
//    }
//
//    /**
//     * 缓存微信openId和session_key
//     *
//     * @param wxOpenId     微信用户唯一标识
//     * @param wxSessionKey 微信服务器会话密钥
//     * @param expires      会话有效期, 以秒为单位, 例如2592000代表会话有效期为30天
//     * @return
//     */
//    public String create3rdSession(String wxOpenId, String wxSessionKey) {
//        String thirdSessionKey = RandomStringUtils.randomAlphanumeric(64);
//        StringBuffer sb = new StringBuffer();
//        sb.append(wxSessionKey).append("#").append(wxOpenId);
//        cache.put(thirdSessionKey, sb.toString());
//        return thirdSessionKey;
//    }
//}
