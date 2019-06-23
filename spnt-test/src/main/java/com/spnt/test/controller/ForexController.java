package com.spnt.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forexvalue")
public class ForexController {

	@Value("${spring.application.name}")
	String appName;


	@GetMapping("/get-exchange-amount")
	public double getForexExchangeValue(@RequestParam String fromValue, @RequestParam String toValue) {
		double forexValue = 0;
		try {
			System.out.println("The webservice is called ");
			forexValue = getExchangeValue(fromValue, toValue);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return forexValue;
	}

	public double getExchangeValue(String fromValue, String toValue) {
		String exchangeRatio = null;
		String[][] exchangeArray =
			{
					{"INR","URO","82.7"},
					{"INR","RAD","5"}
			};

		for(int i =0; i<exchangeArray.length ; i++) {
			for(int j =0; j < exchangeArray.length;j++) {
				if((exchangeArray[i][j].equalsIgnoreCase(fromValue))
						&& (exchangeArray[i][j+1].equalsIgnoreCase(toValue))){
					exchangeRatio = exchangeArray[i][j+2];
					System.out.println(exchangeRatio);
				}
			}
		}

		return new Double(exchangeRatio);
	}

}
