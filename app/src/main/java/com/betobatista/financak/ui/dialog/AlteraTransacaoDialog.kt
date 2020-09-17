package com.betobatista.financak.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.betobatista.financak.R
import com.betobatista.financak.extension.formataParaBrasileiro
import com.betobatista.financak.model.Tipo
import com.betobatista.financak.model.Transacoes

class AlteraTransacaoDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

    fun chama(transacoes: Transacoes, delegate: (transacao: Transacoes) -> Unit) {
        val tipo = transacoes.tipo
        super.chama(tipo, delegate)
        inicializaCampos(transacoes)
    }

    private fun inicializaCampos(
        transacoes: Transacoes
    ) {
        val tipo = transacoes.tipo
        inicializaCampoValor(transacoes)
        inicializaCampoData(transacoes)
        inicializaCampoCategoria(tipo, transacoes)
    }

    private fun inicializaCampoCategoria(
        tipo: Tipo,
        transacoes: Transacoes
    ) {
        val categoriasRetornadas = context.resources.getStringArray(categoriaPor(tipo))
        val posicaoCategoria = categoriasRetornadas.indexOf(transacoes.categoria)
        campoCategoria.setSelection(posicaoCategoria, true)
    }

    private fun inicializaCampoData(transacoes: Transacoes) {
        campoData.setText(transacoes.data.formataParaBrasileiro())
    }

    private fun inicializaCampoValor(transacoes: Transacoes) {
        campoValor.setText(transacoes.valor.toString())
    }

    override val tituloBotaoPositivo: String
        get() = "Alterar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }
}