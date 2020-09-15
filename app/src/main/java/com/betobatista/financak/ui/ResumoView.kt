package com.betobatista.financak.ui

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import com.betobatista.financak.R
import com.betobatista.financak.extension.formataParaBrasileiro
import com.betobatista.financak.model.Resumo
import com.betobatista.financak.model.Transacoes
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    private val context: Context,
    private val view: View,
    transacoes: List<Transacoes>
) {

    private val resumo: Resumo = Resumo(transacoes)

    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza(){
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val receitaTotal = resumo.receita
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = receitaTotal.formataParaBrasileiro()
        }
    }

    private fun adicionaDespesa() {
        val despesaTotal = resumo.despesa
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = despesaTotal.formataParaBrasileiro()
        }
    }

    private fun adicionaTotal() {
        val resumoTotal = resumo.total
        val cor = corPor(resumoTotal)
        with(view.resumo_card_total) {
            setTextColor(cor)
            text = resumoTotal.formataParaBrasileiro()
        }
    }

    private fun corPor(valor: BigDecimal): Int {
        if (valor >= BigDecimal.ZERO) {
            return corReceita
        }
        return corDespesa
    }
}