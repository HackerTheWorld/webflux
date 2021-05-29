/* ====================================================================================================
 * Project Name     [flux]
 * File Name        [com.web.flux.service.MenuService.java]
 * Creation Date    [2021-05-22]
 *
 * Copyright© 2021 瑞声科技[AAC Technologies Holdings] All Rights Reserved
 *
 * ====================================================================================================
 * Change Log
 * ====================================================================================================
 * 2021-05-22     潘凌云      [Init] .
 * ==================================================================================================== */
package com.web.flux.service;

import com.web.flux.entity.MenuEntity;
import com.web.flux.vo.MenuVo;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * <p></p>
 *
 * @author <a href="mailto:panlingyun@aactechnologies.com">潘凌云</a>
 * @version 1.0.0
 * @since jdk 1.8
 */
public interface MenuService {

    Flux<MenuVo> selectMenu(Long menuId, String menuName, String menuType, Long parentId, String roleId, Integer status, Integer needChild);

    Flux<MenuEntity> findAll();
}

