package com.company.account.dto.converter;

import com.company.account.dto.AvgPriceDto;
import com.company.account.model.AvgPrice;
import org.springframework.stereotype.Component;

@Component
public class AvgPriceDtoConverter {
    public AvgPriceDto convert(AvgPrice from){
        return new AvgPriceDto(from.getMins(), from.getPrice(), from.getSymbol());
    }
}
