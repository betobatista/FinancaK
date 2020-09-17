package com.betobatista.financak.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.betobatista.financak.R
import com.betobatista.financak.delegate.TransacaoDelegate
import com.betobatista.financak.model.Tipo
import com.betobatista.financak.model.Transacoes
import com.betobatista.financak.ui.ResumoView
import com.betobatista.financak.ui.adapter.ListaTrasacoesAdapter
import com.betobatista.financak.ui.dialog.AdicionaTransacaoDialog
import com.betobatista.financak.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTrasacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacoes> = mutableListOf()
    private val viewDaActivity by lazy {
        window.decorView
    }

    private val viewGroupDaActivity by lazy {
        viewDaActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes);

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogAdicao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogAdicao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(viewGroupDaActivity, this).chama(
            tipo,
            object : TransacaoDelegate {
                override fun delegate(transacao: Transacoes) {
                    adiciona(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun adiciona(transacao: Transacoes) {
        transacoes.add(transacao)
        atualizaTransacao()
    }

    private fun atualizaTransacao() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = viewDaActivity
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        val listaTrasacoesAdapter = ListaTrasacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTrasacoesAdapter
            setOnItemClickListener { parent, view, position, id ->
                val transacao = transacoes[position]
                chamaDialogAlteracao(transacao, position)
            }
        }
    }

    private fun chamaDialogAlteracao(
        transacao: Transacoes,
        position: Int
    ) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
            .chama(transacao, object : TransacaoDelegate {
                override fun delegate(transacao: Transacoes) {
                    altera(transacao, position)
                }
            })
    }

    private fun altera(transacao: Transacoes, position: Int) {
        transacoes[position] = transacao
        atualizaTransacao()
    }


}