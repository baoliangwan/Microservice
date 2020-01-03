package com.aspentech.springboot;

import java.math.BigDecimal;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CurrencyConversionController {
    
	@Autowired
	private CurrencyExchangeServiceProxy proxy;
	private Logger logger = LogManager.getLogger(this.getClass());

  @GetMapping("/currencyconverterfeign/from/{from}/to/{to}/quantity/{quantity}")
  public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
      @PathVariable BigDecimal quantity) {

	  CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);

	    logger.info("{}", response);

	    return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
	        quantity.multiply(response.getConversionMultiple()), response.getPort());
	  }
}