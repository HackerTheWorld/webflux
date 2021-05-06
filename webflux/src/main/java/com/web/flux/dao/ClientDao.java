package com.web.flux.dao;

import com.web.flux.entity.ClientServerEntity;

import org.springframework.data.repository.reactive.ReactiveSortingRepository;

import reactor.core.publisher.Flux;

public interface ClientDao extends ReactiveSortingRepository<ClientServerEntity, Long> {

    Flux<ClientServerEntity> findAllByClientId(String clientId);
}
