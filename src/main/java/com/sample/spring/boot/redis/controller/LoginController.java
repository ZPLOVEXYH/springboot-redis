package com.sample.spring.boot.redis.controller;


import com.sample.spring.boot.redis.bean.ReturnData;
import com.sample.spring.boot.redis.bean.StatusCode;
import com.sample.spring.boot.redis.domain.User;
import com.sample.spring.boot.redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;

@RestController
@RequestMapping(value = "/api/user")
public class LoginController
{
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/login")
    public ReturnData login(HttpServletRequest request, String account, String password) {
        User user = userService.findUserByAccountAndPassword(account, password);
        if (user != null)
        {
            HttpSession session = request.getSession();

            session.setAttribute("loginUserId", user.getUserId());
            redisTemplate.opsForValue().set("loginUser:" + user.getUserId(), session.getId());

            return new ReturnData(StatusCode.REQUEST_SUCCESS.getValue(), user, "登录成功！");
        }
        else
        {
            return new ReturnData(StatusCode.ACCOUNT_OR_PASSWORD_ERROR.getValue(), "账户名或密码错误！");
        }
    }

    @RequestMapping(value = "/getUserInfo")
    public ReturnData get(long userId) {
        User user = userService.findUserByUserId(userId);
        if (user != null)
        {
            return new ReturnData(StatusCode.REQUEST_SUCCESS.getValue(), user, "查询成功！");
        }
        else
        {
            return new ReturnData(StatusCode.USER_NOT_EXIST.getValue(), "用户不存在！");
        }
    }

    /**
     * base64编码处理
     * @param base64Value
     * @return
     */
    public static String base64Decode(String base64Value) {
        try {
            byte[] decodedCookieBytes = Base64.getDecoder().decode(base64Value);
            return new String(decodedCookieBytes);
        } catch (Exception var3) {
            return null;
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(base64Decode("YjE4NmIyMGQtOTI4OS00OTI4LWI0MWUtNTkwODhhMzMxZTFi"));

        String encodeStr = URLEncoder.encode("熊袁慈", "utf-8");
        System.out.println("urlencode加密之后的字符串为：" + encodeStr);

        String decodeStr = URLDecoder.decode("%E7%86%8A%E8%A2%81%E6%85%88", "utf-8");
        System.out.println(decodeStr);
    }
}
