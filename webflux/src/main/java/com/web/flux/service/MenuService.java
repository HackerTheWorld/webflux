package com.web.flux.service;

import com.web.flux.entity.MenuEntity;
import com.web.flux.vo.MenuVo;
import reactor.core.publisher.Flux;

import java.util.List;


public interface MenuService {

    Flux<MenuVo> selectMenu(Long menuId, String menuName, String menuType, Long parentId, String roleId, Integer status, Integer needChild);

    Flux<MenuEntity> findAll();
}

