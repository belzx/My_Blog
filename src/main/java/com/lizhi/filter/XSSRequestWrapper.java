package com.lizhi.filter;

import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 */
public class XSSRequestWrapper extends HttpServletRequestWrapper {

    public XSSRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 利用转义解决
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        //获取所有参数值的集合
        String[] results = this.getParameterMap().get(name);
        if (results != null && results.length > 0) {
            int length = results.length;
            for (int i = 0; i < length; i++) {
                //过滤参数值
                results[i] = HtmlUtils.htmlEscape(results[i]);
            }
            return results;
        }
        return null;
    }
}