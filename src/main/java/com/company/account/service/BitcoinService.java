package com.company.account.service;

import com.company.account.dto.AvgPriceDto;
import com.company.account.dto.converter.AvgPriceDtoConverter;
import com.company.account.model.AvgPrice;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BitcoinService {
    private final RestTemplate restTemplate;
    private final AvgPriceDtoConverter converter;

    public BitcoinService(RestTemplate restTemplate, AvgPriceDtoConverter converter) {
        this.restTemplate = restTemplate;
        this.converter = converter;
    }

    public AvgPriceDto getAvgPrice(String symbol){
        AvgPrice avgPrice = restTemplate.getForObject("https://api1.binance.com/api/v3/avgPrice?symbol="+symbol, AvgPrice.class);
        avgPrice.setSymbol(symbol);

        return converter.convert(avgPrice);
    }
}
