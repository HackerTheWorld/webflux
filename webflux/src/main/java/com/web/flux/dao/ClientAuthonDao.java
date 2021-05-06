package com.web.flux.dao;

import com.web.flux.entity.ClientAuthoritiesEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ClientAuthonDao extends ReactiveCrudRepository<ClientAuthoritiesEntity, Long>  {
    
}
