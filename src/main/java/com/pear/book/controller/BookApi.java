package com.pear.book.controller;

import com.pear.book.model.BookPro;
import com.pear.util.HttpUtils;
import com.pear.zidian.controller.JuheApi;
import com.pear.zidian.model.JuhePro;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2016/12/29.
 */
@RestController
public class BookApi {
    private static Logger logger = Logger.getLogger(JuheApi.class);

    @RequestMapping("/{appid}/{version}/getBookList")
    @ResponseBody
    public String getBookList(String page, String keyword, String typeId) {
        if (StringUtils.isBlank(page)) {
            page = "1";
        }
        String parms = "?keyword=" + keyword + "&typeId=" + typeId + "&page=" + page + "&showapi_sign=" + BookPro.getKey() + "&showapi_appid=" + BookPro.getKeyC();
        String res = HttpUtils.get(BookPro.getHostUrl() + BookPro.getBookList() + parms);
        return res;
    }

    @RequestMapping("/{appid}/{version}/getBookCat")
    @ResponseBody
    public String getBookCat(String bookId) {
        String parms = "?bookId=" + bookId + "&showapi_sign=" + BookPro.getKey() + "&showapi_appid=" + BookPro.getKeyC();
        String res = HttpUtils.get(BookPro.getHostUrl() + BookPro.getBookCat() + parms);
        return res;
    }

    @RequestMapping("/{appid}/{version}/getBookConetent")
    @ResponseBody
    public String getBookConetent(String bookId, String cid) {
        String parms = "?bookId=" + bookId + "&cid=" + cid + "&showapi_sign=" + BookPro.getKey() + "&showapi_appid=" + BookPro.getKeyC();
        String res = HttpUtils.get(BookPro.getHostUrl() + BookPro.getBookContent() + parms);
        return res;
    }
}
