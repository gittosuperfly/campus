package com.cai.campus.Interceptor;

import cn.jiguang.common.utils.StringUtils;
import com.cai.campus.model.Response;
import com.cai.campus.model.ResultCode;
import com.google.gson.GsonBuilder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 权限验证拦截器
 * 验证Header中的token值是否符合
 *
 * @author caiyufei
 * @since 2020-03-16 04:13:42
 */

public class AuthInspectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) throws Exception {

        String token = request.getHeader(WebConfig.TOKEN);

        if (StringUtils.isNotEmpty(token) && token.equals(WebConfig.TOKEN_VALUE)) {
            return true;
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            String responseData = new GsonBuilder()
                    .serializeNulls()
                    .setPrettyPrinting()
                    .create()
                    .toJson(Response.get(ResultCode.UNAUTHORIZED, "未授权"));
            PrintWriter responseWriter = response.getWriter();
            responseWriter.write(responseData);
            responseWriter.flush();
            responseWriter.close();
            return false;
        }

    }

}
