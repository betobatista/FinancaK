package com.betobatista.financak.ui.activity

import android.os.Bundle
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
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this).chama(
            tipo,
            object : TransacaoDelegate {
                override fun delegate(transacao: Transacoes) {
                    atualizaTransacao(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }



    private fun atualizaTransacao(transacao: Transacoes) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTrasacoesAdapter(transacoes, this)
        lista_transacoes_listview.setOnItemClickListener { parent, view, position, id ->
            val transacao = transacoes[position]
            AlteraTransacaoDialog(window.decorView as ViewGroup, this)
                .chama(transacao, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacoes) {
                        atualizaTransacao(transacao)
                    }
                })
        }
    }


}