package com.web.flux.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
// import com.web.flux.dao.ClientAuthonDao;
// import com.web.flux.dao.ClientDao;
import com.web.flux.entity.ClientAuthoritiesEntity;
import com.web.flux.entity.ClientServerEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/asyn")
public class AsynController {

    // @Autowired
    // private ClientDao clientDao;
    // @Autowired
    // private ClientAuthonDao clientAuthonDao;

    @GetMapping("/test")
    @ResponseBody
    public Mono<JSONObject> test(@RequestParam("infor") String infor) {
        Mono<String> monoStr = Mono.just(infor);
        Mono<JSONObject> monoJson = monoStr.flatMap(str -> {
            return strToJSON(str);
        });
        return monoJson;
    }

    // "application/stream+json"
    // "text/event-stream"
    @GetMapping(value = "/testFlux", produces = "text/event-stream")
    @ResponseBody
    public Flux<JSONObject> testFlux(@RequestParam("infor") String infor) {
        System.out.println(infor);
        ArrayList<String> list = new ArrayList<String>();
        list.add(1 + "::" + infor);
        list.add(5 + "::" + infor);
        list.add(6 + "::" + infor);
        list.add(7 + "::" + infor);
        list.add(8 + "::" + infor);
        Flux<String> monoStr = Flux.fromIterable(list);
        Flux<JSONObject> monoJson = monoStr.flatMap(str -> {
            return strToJSON(str);
        });
        return monoJson;
    }

    @GetMapping(value = "/findAll", produces = "text/event-stream")
    @ResponseBody
    public Flux<ClientServerEntity> findAll() {
        List<ClientServerEntity> list = new ArrayList();
        List<ClientAuthoritiesEntity> list2 = new ArrayList();
        ClientServerEntity clientServerEntity = new ClientServerEntity();
        clientServerEntity.setClientId("1");
        clientServerEntity.setClientServerId(1);
        list.add(clientServerEntity);
        ClientServerEntity clientServerEntity2 = new ClientServerEntity();
        clientServerEntity2.setClientId("2");
        clientServerEntity2.setClientServerId(2);
        list.add(clientServerEntity2);
        ClientServerEntity clientServerEntity4 = new ClientServerEntity();
        clientServerEntity4.setClientId("2");
        clientServerEntity4.setClientServerId(2);
        list.add(clientServerEntity4);
        ClientServerEntity clientServerEntity3 = new ClientServerEntity();
        clientServerEntity3.setClientId("3");
        clientServerEntity3.setClientServerId(3);
        list.add(clientServerEntity3);

        ClientAuthoritiesEntity clientAuthoritiesEntity = new ClientAuthoritiesEntity();
        clientAuthoritiesEntity.setClientServerId(1L);
        clientAuthoritiesEntity.setAuthorities("amdin");
        list2.add(clientAuthoritiesEntity);
        ClientAuthoritiesEntity clientAuthoritiesEntity2 = new ClientAuthoritiesEntity();
        clientAuthoritiesEntity2.setClientServerId(1L);
        clientAuthoritiesEntity2.setAuthorities("root");
        list2.add(clientAuthoritiesEntity2);
        ClientAuthoritiesEntity clientAuthoritiesEntity3 = new ClientAuthoritiesEntity();
        clientAuthoritiesEntity3.setClientServerId(2L);
        clientAuthoritiesEntity3.setAuthorities("anyone");
        list2.add(clientAuthoritiesEntity3);

        Flux<ClientServerEntity> cFlux = Flux.fromIterable(list);
        Flux<ClientAuthoritiesEntity> flux = Flux.fromIterable(list2);

        // 分组
        Flux<GroupedFlux<Long, ClientAuthoritiesEntity>> fluxBy = flux.groupBy(aut -> aut.getClientServerId());
        // 合并每个key的数据
        fluxBy.subscribe(mapper -> {
            // 获取GroupedFlux分组后的数据 List<T>形式
            mapper.collectList().subscribe(map -> {
                List<String> rolesList = new ArrayList<String>();
                for (ClientAuthoritiesEntity c : map) {
                    rolesList.add(c.getAuthorities());
                }
                
                cFlux.subscribe(e -> {
                    if(e.getClientServerId().equals(mapper.key().intValue())){
                        e.setRoles(rolesList);
                    }
                });
            });
        });
        return cFlux;
    }

    private Mono<JSONObject> strToJSON(String str) {
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
