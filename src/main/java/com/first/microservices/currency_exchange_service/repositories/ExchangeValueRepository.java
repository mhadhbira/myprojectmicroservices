package com.first.microservices.currency_exchange_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.first.microservices.currency_exchange_service.entities.ExchangeValue;

@Repository
public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long>{
	
	@Query("select e from ExchangeValue e where e.from=?1 and e.to=?2")
	ExchangeValue findByFromAndTo(String from, String to);

}
