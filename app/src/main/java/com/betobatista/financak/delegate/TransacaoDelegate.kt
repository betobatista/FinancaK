package com.betobatista.financak.delegate

import com.betobatista.financak.model.Transacoes

interface TransacaoDelegate {
    fun delegate(transacao : Transacoes)
}