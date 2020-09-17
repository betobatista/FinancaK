package com.betobatista.financak.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.betobatista.financak.R
import com.betobatista.financak.delegate.TransacaoDelegate
import com.betobatista.financak.extension.coverteParaCalendar
import com.betobatista.financak.extension.formataParaBrasileiro
import com.betobatista.financak.model.Tipo
import com.betobatista.financak.model.Transacoes
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class AdicionaTransacaoDialog(
    private val viewGroup: ViewGroup,
    private val context: Context
) : FormularioTransacaoDialog(context, viewGroup) {

}