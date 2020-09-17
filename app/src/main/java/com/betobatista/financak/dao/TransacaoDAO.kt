package com.betobatista.financak.dao

import com.betobatista.financak.model.Transacoes

class TransacaoDAO {

    val transacoes: MutableList<Transacoes> = mutableListOf()

    fun adiciona(transacao: Transacoes){
        transacoes.add(transacao)
    }

    fun altera(transacao: Transacoes, posicao: Int){
        transacoes[posicao] = transacao
    }

    fun remove(posicao: Int){
        transacoes.removeAt(posicao)
    }
}