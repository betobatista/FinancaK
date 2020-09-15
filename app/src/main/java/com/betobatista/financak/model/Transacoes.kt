package com.betobatista.financak.model

import java.math.BigDecimal
import java.util.*

class Transacoes(
    val valor: BigDecimal,
    val categoria: String = "Indefinida",
    val tipo: Tipo,
    val data: Calendar = Calendar.getInstance()
)