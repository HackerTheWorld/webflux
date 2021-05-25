package com.web.flux.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

@Table("menu")
public class MenuEntity {
    @Id
    private Long menuId;
    private String menuName;
    private Long parentId;
    private String menuType;
    private Integer isCache;
    private Integer isFrame;
    private Integer orderNumber;
    private Integer visible;
    private String parentPath;
    private String path;
    private Integer status;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {return true;}
        if (o == null || this.getClass() != o.getClass()) {return false;}
        final MenuEntity that = (MenuEntity) o;
        return Objects.equals(this.menuId, that.menuId) &&
                Objects.equals(this.menuName, that.menuName) &&
                Objects.equals(this.parentId, that.parentId) &&
                Objects.equals(this.menuType, that.menuType) &&
                Objects.equals(this.isCache, that.isCache) &&
                Objects.equals(this.isFrame, that.isFrame) &&
                Objects.equals(this.orderNumber, that.orderNumber) &&
                Objects.equals(this.visible, that.visible) &&
                Objects.equals(this.parentPath, that.parentPath) &&
                Objects.equals(this.path, that.path) &&
                Objects.equals(this.status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.menuId, this.menuName, this.parentId, this.menuType, this.isCache, this.isFrame, this.orderNumber, this.visible, this.parentPath, this.path, this.status);
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(final String parentPath) {
        this.parentPath = parentPath;
    }

    public void setMenuId(final Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(final String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(final Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(final String menuType) {
        this.menuType = menuType == null ? null : menuType.trim();
    }

    public Integer getIsCache() {
        return this.isCache;
    }

    public void setIsCache(final Integer isCache) {
        this.isCache = isCache;
    }

    public Integer getIsFrame() {
        return this.isFrame;
    }

    public void setIsFrame(final Integer isFrame) {
        this.isFrame = isFrame;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(final Integer status) {
        this.status = status;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(final Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(final Integer visible) {
        this.visible = visible;
    }

}