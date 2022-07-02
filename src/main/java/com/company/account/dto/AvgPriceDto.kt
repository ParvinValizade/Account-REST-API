package com.company.account.dto

import java.math.BigDecimal

data class AvgPriceDto(
    val mins:Int,
    val price: BigDecimal,
    val symbol: String
)
