package com.company.account.model

import java.math.BigDecimal

data class AvgPrice(
    val mins:Int?,
    val price: BigDecimal?,
    var symbol: String?
) {
    constructor():this(
        null,
        null,
        null
    )
}

