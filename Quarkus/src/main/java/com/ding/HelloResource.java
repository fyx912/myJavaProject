package com.ding;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>  </p>
 *
 * @author Tintin
 * @date 2023-11-05 22:23:57
 **/
@Path("/hello")
public class HelloResource {
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(@PathParam("name") String name){
        return "你好 " + name;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> hello(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("message","success");
        return map;
    }
}
