package com.web.flux.controller;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.web.flux.dao.MenuDao;
import com.web.flux.entity.MenuEntity;

import com.web.flux.service.MenuService;
import com.web.flux.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/asyn")
public class AsynController {

    @Autowired
    private MenuService menuService;

    @GetMapping(value = "/selectMenu",produces = "application/stream+json")
    public Flux<MenuVo> selectMeun(@RequestParam(name = "menuId", required = false) final Long menuId,
                                   @RequestParam(name = "menuName", required = false) final String menuName,
                                   @RequestParam(name = "menuType", required = false) final String menuType,
                                   @RequestParam(name = "parentId", required = false) final Long parentId,
                                   @RequestParam(name = "roleId", required = false) final String roleId,
                                   @RequestParam(name = "status", required = false) final Integer status,
                                   @RequestParam(name = "needChild", required = false, defaultValue = "1") final Integer needChild){


        return menuService.selectMenu(menuId, menuName, menuType, parentId, roleId, status, needChild);
    }

    @GetMapping(value = "/findAll",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<MenuVo> findAll(){
        return menuService.findAll();
    }





}
