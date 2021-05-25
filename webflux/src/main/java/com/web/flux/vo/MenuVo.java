/* ====================================================================================================
 * Project Name     [flux]
 * File Name        [com.web.flux.vo.MenuVo.java]
 * Creation Date    [2021-05-22]
 *
 * Copyright© 2021 瑞声科技[AAC Technologies Holdings] All Rights Reserved
 *
 * ====================================================================================================
 * Change Log
 * ====================================================================================================
 * 2021-05-22     潘凌云      [Init] .
 * ==================================================================================================== */
package com.web.flux.vo;

import com.web.flux.entity.MenuEntity;
import com.web.flux.entity.RoleEntity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p></p>
 *
 * @author <a href="mailto:panlingyun@aactechnologies.com">潘凌云</a>
 * @version 1.0.0
 * @since jdk 1.8
 */
public class MenuVo extends MenuEntity {

    private Set<RoleEntity> roles = new HashSet<>();

    private Set<MenuVo> children = new HashSet<>();

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        final MenuVo menuVo = (MenuVo) o;
        return Objects.equals(this.roles, menuVo.roles) &&
                Objects.equals(this.children, menuVo.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.roles, this.children);
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(final RoleEntity role) {
        this.roles.add(role);
    }

    public Set<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(final Set<MenuVo> children) {
        this.children = children;
    }


}


