package com.web.flux.dao;

import com.web.flux.entity.ClientAuthoritiesEntity;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
@Component
public interface ClientAuthonDao extends ReactiveCrudRepository<ClientAuthoritiesEntity, Long>  {
    
}
