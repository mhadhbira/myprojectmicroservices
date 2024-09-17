package com.first.microservices.currency_exchange_service.entities;  
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity 
@Table(name="Exchange_Value")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExchangeValue   
{  

@Id  
@Column(name="id")  
private Long id;  
@Column(name="fromvalue")
private String from; 
@Column(name="tovalue")
private String to; 
@Column(name="conversionmultiple")
private BigDecimal conversionMultiple;  
@Column(name="port")
private int port;  
 
}  