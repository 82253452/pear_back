package com.pear.zidian.controller;

import com.pear.util.HttpUtils;
import com.pear.zidian.model.JuhePro;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by admin on 2016/12/23.
 */
@Controller
public class JuheApi {
    private static Logger logger = Logger.getLogger(JuheApi.class);

    @RequestMapping("/{appid}/{version}/queryPy")
    @ResponseBody
    private String queryPy(String word, String page, String pageSize) {
        if (StringUtils.isNotBlank(page)) {
            page = "1";
        }
        if (StringUtils.isNotBlank(pageSize)) {
            pageSize = "10";
        }
        String parms = "?key=" + JuhePro.getKey() + "&word=" + word + "&dtype=&page=" + page + "&isjijie=1&isxiangjie=1&pagesize=" + pageSize;
        String res = HttpUtils.get(JuhePro.getHostUrl() + JuhePro.getPyhz() + parms);
        System.out.println(res);
        return res;
    }

    @RequestMapping("/{appid}/{version}/query")
    @ResponseBody
    private String query(String word) {
        String parms = "?key=" + JuhePro.getKey();
        if(StringUtils.isNotEmpty(word)){
            parms+="&word="+word;
        }
        String res = HttpUtils.get(JuhePro.getHostUrl() + JuhePro.getQuery() + parms);
        System.out.println(res);
        return res;
    }
    @RequestMapping("/{appid}/{version}/cyQuery")
    @ResponseBody
    private String cyQuery(String word) {
        String parms = "?key=" + JuhePro.getKeyC();
        if(StringUtils.isNotEmpty(word)){
            parms+="&word="+word;
        }
        String res = HttpUtils.get(JuhePro.getHostUrl() + JuhePro.getCyQuery() + parms);
        System.out.println(res);
        return res;
    }

    @RequestMapping("/{appid}/{version}/querybs")
    @ResponseBody
    private String querybs(String word, String page, String pageSize) {
        if (StringUtils.isNotBlank(page)) {
            page = "1";
        }
        if (StringUtils.isNotBlank(pageSize)) {
            pageSize = "10";
        }
        String parms = "?key=" + JuhePro.getKey() + "&word=" + word + "&dtype=&page=" + page + "&isjijie=1&isxiangjie=1&pagesize=" + pageSize;
        String res = HttpUtils.get(JuhePro.getHostUrl() + JuhePro.getBuhz() + parms);
        System.out.println(res);
        return res;
    }
    @RequestMapping("/{appid}/{version}/queryid")
    @ResponseBody
    private String queryid(String word) {
        String parms = "?key=" + JuhePro.getKey() + "&word=" + word;
        String res = HttpUtils.get(JuhePro.getHostUrl() + JuhePro.getIdhz() + parms);
        System.out.println(res);
        return res;
    }
}
