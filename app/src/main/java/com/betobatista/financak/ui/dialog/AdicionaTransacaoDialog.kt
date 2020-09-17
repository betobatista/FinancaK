package com.betobatista.financak.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.betobatista.financak.R
import com.betobatista.financak.model.Tipo

class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

    override val tituloBotaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        if(tipo == Tipo.RECEITA){
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }

}