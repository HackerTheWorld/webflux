package com.web.flux.service.impl;

import com.web.flux.dao.MenuDao;
import com.web.flux.entity.MenuEntity;
import com.web.flux.entity.RoleEntity;
import com.web.flux.service.MenuService;
import com.web.flux.vo.MenuVo;
import io.r2dbc.mssql.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import java.util.Map;


@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private DatabaseClient client;

    @Override
    public Flux<MenuEntity> findAll() {
        return menuDao.findAll();
//        return me.map(mapEle -> {
//            MenuVo menuVo = new MenuVo();
//            BeanCopier beanCopier = BeanCopier.create(MenuEntity.class,MenuVo.class,false);
//            beanCopier.copy(mapEle,menuVo,null);
//            return menuVo;
//        });
    }

    @Override
    public Flux<MenuVo> selectMenu(Long menuId, String menuName, String menuType, Long parentId, String roleId, Integer status, Integer needChild) {
        Flux<Map<String, Object>> data = client.sql(createdSelectMenuSQL(menuId, menuName, menuType, parentId, roleId, status).toString())
                 .fetch()
                 .all();
        Flux<GroupedFlux<MenuVo, Map<String,Object>>> groupFlux =
                data.groupBy(map -> {
                    MenuVo menuVo = new MenuVo();
                    BeanMap beanMap = BeanMap.create(menuVo);
                    beanMap.putAll(map);
                    return menuVo;
                });
       Flux<MenuVo> f = groupFlux.map(map ->{
            map.map(mapEle -> {
               MenuVo menuVo = map.key();
               RoleEntity roleEntity = new RoleEntity();
               BeanMap beanMap = BeanMap.create(roleEntity);
               beanMap.putAll(mapEle);
               if(roleEntity.getRoleId() != null){
                   menuVo.setRoles(roleEntity);
               }
               return null;
           });
            return map.key();
          }
        );
        if(needChild.intValue() == 1){
            return f;
        }else {
            //todo
            f.subscribe(s -> {

            });
            return f;
        }
    }

    private StringBuilder createdSelectMenuSQL(Long menuId, String menuName, String menuType, Long parentId, String roleId, Integer status){
        StringBuilder sb = new StringBuilder("select\n")
                .append("t1.menu_id menuId,t1.menu_name menuName,t1.parent_id parentId,\n")
                .append("t1.menu_type menuType, t1.is_cache isCache, t1.order_number orderNumber,\n")
                .append("t1.parent_path parentPath, t1.is_frame isFrame,\n")
                .append("t1.visible,t1.path,t1.status,\n")
                .append("t3.role_id roleId,t3.role_name roleName,t3.created_time createdTime,t3.status\n")
                .append("from\n")
                .append("(select * from menu t\n")
                .append("where 1=1 ");
        if(!StringUtils.isEmpty(menuName)){
            sb.append("and t.menu_name = '")
                    .append(menuName)
                    .append("' ");
        }
        if(!StringUtils.isEmpty(menuType)){
            sb.append("and t.menu_type = '")
                    .append(menuType)
                    .append("' ");
        }
        if(!StringUtils.isEmpty(parentId)){
            sb.append("and t.parent_id = '")
                    .append(parentId)
                    .append("' ");
        }
        if(!StringUtils.isEmpty(status)){
            sb.append("and t.status = '")
                    .append(status)
                    .append("' ");
        }
        if(!StringUtils.isEmpty(menuId)){
            sb.append("and t.menu_id = '")
                    .append(menuId)
                    .append("' ");
        }
        sb.append(") t1\n")
                .append("left join role_menu t2 on t2.menu_id = t1.menu_id\n")
                .append("left join role t3 on t2.role_id = t3.role_id\n")
                .append("where 1=1\n");
        if(!StringUtils.isEmpty(roleId)){
            sb.append("and t3.role_id = '")
                    .append(roleId)
                    .append("' ");
        }
        sb.append("order by t1.menu_id desc");
        return sb;
    }

}

