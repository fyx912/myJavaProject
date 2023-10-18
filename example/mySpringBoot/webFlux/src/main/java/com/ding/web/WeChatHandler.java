package com.ding.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author tintin
 * @version V1.0
 * @Description
 * @@copyright
 * @ClassName WeChatHandler
 * @date 2020-12-04 17:49
 */
@RestController
@RequestMapping("wechat")
public class WeChatHandler {

    /**
     *  绑定微信公众号
     * @param request
     * @return
     */
//    public Mono<ServerResponse> weChatOfficialAccountBind(ServerRequest serverRequest){
//        Mono<JSONObject> body = serverRequest.bodyToMono(JSONObject.class);
//        System.out.println(body);
//        return ok().contentType(MediaType.TEXT_PLAIN).body(
//                Mono.just("Now is " + new SimpleDateFormat("HH:mm:ss").format(new Date()))
//                ,String.class);
//    }
    @PostMapping("officialAccount/bind")
    public Mono<String> weChatOfficialAccountBind(@RequestBody String data){
        System.out.println(data);
        return  Mono.just("ok");
    }
}
