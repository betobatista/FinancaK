package com.betobatista.financak.dao

import com.betobatista.financak.model.Transacoes

class TransacaoDAO {

    val transacoes: List<Transacoes> = Companion.transacoes
    companion object{
        private val transacoes: MutableList<Transacoes> = mutableListOf()
    }

    fun adiciona(transacao: Transacoes){
        Companion.transacoes.add(transacao)
    }

    fun altera(transacao: Transacoes, posicao: Int){
        Companion.transacoes[posicao] = transacao
    }

    fun remove(posicao: Int){
        Companion.transacoes.removeAt(posicao)
    }
}