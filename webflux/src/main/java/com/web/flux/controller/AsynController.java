package com.web.flux.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.web.flux.entity.MenuEntity;
import com.web.flux.service.MenuService;
import com.web.flux.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/asyn")
public class AsynController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/selectMenu",produces = {"application/stream+json",MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<MenuVo> selectMeun(@RequestParam(name = "menuId", required = false) final Long menuId,
                                   @RequestParam(name = "menuName", required = false) final String menuName,
                                   @RequestParam(name = "menuType", required = false) final String menuType,
                                   @RequestParam(name = "parentId", required = false) final Long parentId,
                                   @RequestParam(name = "roleId", required = false) final String roleId,
                                   @RequestParam(name = "status", required = false) final Integer status,
                                   @RequestParam(name = "needChild", required = false, defaultValue = "1") final Integer needChild){
        return menuService.selectMenu(menuId, menuName, menuType, parentId, roleId, status, needChild);
    }

    @GetMapping(value = "/findAll")
    public  Mono<JSONObject> findAll() throws InterruptedException {

        Mono<List<MenuEntity>> menuVoFlux = menuService.findAll().collectList();
        Mono<JSONObject> jsonObjectMono = menuVoFlux.flatMap(s -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("sucess",true);
            jsonObject.put("mess","sucess");
            jsonObject.put("data",s);
            System.out.println("listener map::"+jsonObject);
            return Mono.just(jsonObject);
        });
       return jsonObjectMono;
    }

    @GetMapping(value = "/test")
    public Mono<JSONObject> test(){
        MenuVo menuVo1 = new MenuVo();
        menuVo1.setMenuName("aaa");
        MenuVo menuVo2 = new MenuVo();
        menuVo2.setMenuName("bbb");
        MenuVo menuVo3 = new MenuVo();
        menuVo3.setMenuName("ccc");

        Flux<MenuVo> strs = Flux.just(menuVo1,menuVo2,menuVo3);
        strs.subscribe(s -> System.out.println(s));
        Flux<MenuVo> strs2 = Flux.just();
        Flux.push(a -> a.complete());
        Flux.create(a -> a.next("aa")).subscribe(s -> System.out.println(s));
        return null;
    }

}
