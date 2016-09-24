package controllers;


import com.coder.a.TestJava;
import com.fasterxml.jackson.databind.node.POJONode;
import com.google.common.collect.Lists;
import com.google.inject.Singleton;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by konghang on 16/9/23.
 */
@Singleton
public class HelloWorldController extends Controller {

    /**
     * hello
     *
     * @return
     */
    public Result index(){
        return ok("hello world");
    }

    /**
     * json demo
     * @return
     */
    public Result json(){
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("json","this is a json demo");
        return ok().sendJson(new POJONode(hashMap));
    }

    /**
     * json array
     *
     * @return
     */
    public Result jsonArray(){
        Map<String,String> hashMap = new HashMap<>();
        hashMap.put("json","this is a json demo");
        List<Map<String,String>> list = Lists.newArrayList(hashMap);
        return ok().sendJson(new POJONode(list));
    }

    /**
     * cookie
     *
     * @return
     */
    public Result cookie(){
        Http.CookieBuilder builder = Http.Cookie.builder("test", "123");
        return ok().withCookies(builder.build());
    }

    /**
     * session save
     *
     * @return
     */
    public Result sessionSave(){
        session("session","sessionA");
        return ok();
    }

    public Result sessionRead(){
        return ok(session("session"));
    }

    public Result sayFromCommon(){
        TestJava.test();
        return ok("this is say from common");
    }
}
