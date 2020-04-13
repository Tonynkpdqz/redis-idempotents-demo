package com.nkpdqz.redisidempotentsdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private RedisService redisService;

    public String createToken(){
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();
        redisService.setEx("token",id,10000L);
        return id;
    }

    public boolean checkToken(HttpServletRequest request){
        String req_token = request.getHeader("token");
        if (req_token!=null && req_token.length()!=0
            && redisService.exists("token")){
            return redisService.remove("token");
        }else {
            return false;
        }
    }
}
