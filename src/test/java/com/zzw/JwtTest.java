package com.zzw;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    public void testGen(){
        Map<String,Object> claims=new HashMap<>();
        claims.put("id",1);
        claims.put("username","张三");
        //生成jwt的代码
        String token=JWT.create()
                .withClaim("user",claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis()+12*1000*60*60))//添加过期时间
                .sign(Algorithm.HMAC256("zzw"));//指定算法，配置密钥
        System.out.println(token);
    }
    @Test
    public void testParse(){
        //定义字符串，模拟用户传递的token
        String token="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
                "eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MjUyMzAxODJ9." +
                "lENIggYDa8YtNRaqsuHHggPqWWyR6Y94ChA4xtsNR8M";

        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256("zzw")).build();
        DecodedJWT decodedJWT=jwtVerifier.verify(token);//验证token,生成一个解析后的jwt对象
        Map<String, Claim> claim=decodedJWT.getClaims();
        System.out.println(claim.get("user"));
        //如果token过期，验证失败
        //如果密钥改变，验证失败
        //如果令牌被篡改，验证失败
    }
}
