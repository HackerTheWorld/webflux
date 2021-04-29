package com.web.flux.controller;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/asyn")
public class AsynController {
    
    @GetMapping("/test")
    @ResponseBody
    public Mono<JSONObject> test(@RequestParam("infor") String infor){
        Mono<String> monoStr = Mono.just(infor);
        Mono<JSONObject> monoJson = monoStr.flatMap(str -> {
            return strToJSON(str);
        });
        return monoJson;
    }

    //"application/stream+json"
    //"text/event-stream"
    @GetMapping(value = "/testFlux",produces = "text/event-stream")
    @ResponseBody
    public Flux<JSONObject> testFlux(@RequestParam("infor") String infor){
        System.out.println(infor);
        ArrayList<String> list = new ArrayList<String>();
        list.add(1+"::"+infor);
        list.add(5+"::"+infor);
        list.add(6+"::"+infor);
        list.add(7+"::"+infor);
        list.add(8+"::"+infor);
        Flux<String> monoStr = Flux.fromIterable(list);
        Flux<JSONObject> monoJson = monoStr.flatMap(str -> {
            return strToJSON(str);
        });
        return monoJson;
    }

    private Mono<JSONObject> strToJSON(String str){
        System.out.println(str);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("infor", str);
        jsonObject.put("type", "one");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Mono.just(jsonObject);
    }

}
