package com.web.flux.dao;

import com.web.flux.entity.MenuEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface MenuDao extends ReactiveCrudRepository<MenuEntity, Long> {

    
}
