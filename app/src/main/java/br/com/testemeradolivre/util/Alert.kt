package br.com.testemeradolivre.util

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.content.DialogInterface.OnClickListener
import android.os.Build
import android.support.v7.app.AlertDialog

import java.util.ArrayList

@SuppressLint("ResourceType")
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
object Alert {
    /**
     * Enum criado para formalizar os diversos tipos de alertas que podem ser
     * mostrados.
     *
     */
    enum class AlertType private constructor(// numero inteiro que guarda o valor do icone que sera mostrado no
        // dialog
        val drawable: Int, val title: String
    ) {
        INFO(android.R.drawable.ic_dialog_info, "Informação"), WARN(
            android.R.drawable.ic_dialog_alert, "Alerta"
        ),
        SUCESS(
            android.R.drawable.checkbox_on_background, "Sucesso"
        ),
        ERROR(
            android.R.drawable.ic_delete, "Erro"
        )
    }

    /**
     * Mostra um dialog simples com um botao OK
     *
     * @param message
     * mensagem/conteudo que aparecera no dialog
     * @param context
     * contexto da aplicacao
     * @param alertType
     * tipo de alerta(INFO, WARN, SUCESS, ERROR)
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun showSimpleDialog(
        message: String, context: Context,
        alertType: AlertType?
    ) {

        val alertDialog = AlertDialog.Builder(context, 4)
        alertDialog.setMessage(message)
        alertDialog.setNeutralButton("OK", null)

        alertDialog.setInverseBackgroundForced(true)

        if (alertType == null) {
            alertDialog.setTitle(AlertType.INFO.title)
        } else {
            alertDialog.setIcon(alertType.drawable)
            alertDialog.setTitle(alertType.title)
        }
        alertDialog.show()
    }

    /**
     * Mostra um dialog simples com um botao OK, com acao ao clicar no OK
     *
     * @param message
     * mensagem/conteudo que aparecera no dialog
     * @param context
     * contexto da aplicacao
     * @param alertType
     * tipo de alerta(INFO, WARN, SUCESS, ERROR)
     * @param okButton
     * acao do botao ok
     */
    fun showSimpleDialog(
        message: String, context: Context,
        alertType: AlertType, okButton: OnClickListener
    ) {
        val alertDialog = AlertDialog.Builder(context, 4)
        alertDialog.setMessage(message)
        alertDialog.setCancelable(false)
        alertDialog.setNeutralButton("OK", okButton)
        alertDialog.show()
    }

    /**
     * Mostra um dialog com um botao Sim e um botao Nao, com acao no botao Sim e
     * outra acao no botao Nao
     *
     * @param message
     * mensagem/conteudo que aparecera no dialog
     * @param context
     * contexto da aplicacao
     * @param yesButton
     * acao do botao Sim
     * @param noButton
     * acao do botao Nao
     * @param alertType
     * tipo de alerta(INFO, WARN, SUCESS, ERROR)
     */
    fun showYesNoDialog(
        message: String, context: Context,
        yesButton: OnClickListener, noButton: OnClickListener,
        alertType: AlertType?
    ) {
        val alertDialog = AlertDialog.Builder(context, 4)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Sim", yesButton)
        alertDialog.setNegativeButton("Não", noButton)
        if (alertType == null) {
            alertDialog.setTitle(AlertType.INFO.title)
        } else {
            alertDialog.setIcon(alertType.drawable)
            alertDialog.setTitle(alertType.title)
        }
        alertDialog.show()
    }

    /**
     * Mostra um dialog com um botao Sim e um botao Nao, com acao somente no
     * botao Sim
     *
     * @param message
     * mensagem/conteudo que aparecera no dialog
     * @param context
     * contexto da aplicacao
     * @param yesButton
     * acao do botao Sim
     * @param alertType
     * tipo de alerta(INFO, WARN, SUCESS, ERROR)
     */
    fun showYesNoDialog(
        message: String, context: Context,
        yesButton: OnClickListener, alertType: AlertType?
    ) {
        val alertDialog = AlertDialog.Builder(context, 4)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Sim", yesButton)
        alertDialog.setNegativeButton("Não", null)
        if (alertType == null) {
            alertDialog.setTitle(AlertType.INFO.title)
        } else {
            alertDialog.setIcon(alertType.drawable)
            alertDialog.setTitle(alertType.title)
        }
        alertDialog.show()
    }

    /**
     * Mostra um dialog com um botao Ok e um botao Cancelar, com acao ao clicar
     * no botao Ok e outra acao ao clicar em Cancelar
     *
     * @param message
     * mensagem/conteudo que aparecera no dialog
     * @param context
     * contexto da aplicacao
     * @param okButton
     * acao do botao Ok
     * @param cancelButton
     * acao do botao Cancelar
     * @param alertType
     * tipo de alerta(INFO, WARN, SUCESS, ERROR)
     */
    fun showConfirmDialog(
        message: String, context: Context,
        okButton: OnClickListener, cancelButton: OnClickListener,
        alertType: AlertType?
    ) {
        val alertDialog = AlertDialog.Builder(context, 4)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", okButton)
        alertDialog.setNegativeButton("Cancelar", cancelButton)
        alertDialog.setCancelable(false)
        if (alertType == null) {
            alertDialog.setTitle(AlertType.INFO.title)
        } else {
            alertDialog.setIcon(alertType.drawable)
            alertDialog.setTitle(alertType.title)
        }
        alertDialog.show()
    }

    /**
     * Mostra um dialog com um botao Ok e um botao Cancelar, com acao somente no
     * botao Ok
     *
     * @param message
     * mensagem/conteudo que aparecera no dialog
     * @param context
     * contexto da aplicacao
     * @param okButton
     * acao do botao Ok
     * @param alertType
     * tipo de alerta(INFO, WARN, SUCESS, ERROR)
     */
    fun showConfirmDialog(
        message: String, context: Context,
        okButton: OnClickListener, alertType: AlertType?
    ) {
        val alertDialog = AlertDialog.Builder(context, 4)
        alertDialog.setMessage(message)
        alertDialog.setPositiveButton("Ok", okButton)
        alertDialog.setNegativeButton("Cancelar", null)
        if (alertType == null) {
            alertDialog.setTitle(AlertType.INFO.title)
        } else {
            alertDialog.setIcon(alertType.drawable)
            alertDialog.setTitle(alertType.title)
        }
        alertDialog.show()
    }

    fun showListaDialog(message: String, context: Context, items: Array<String>) {

        val itemsSelected = ArrayList<Int>()
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Filtrar por status")
        builder.setMultiChoiceItems(
            items, null
        ) { dialog, selectedItemId, isSelected ->
            if (isSelected) {
                itemsSelected.add(selectedItemId)
            } else if (itemsSelected.contains(selectedItemId)) {
                itemsSelected.remove(Integer.valueOf(selectedItemId))
            }
        }
            .setPositiveButton("OK") { dialog, id ->
                //Your logic when OK button is clicked
            }
            .setNegativeButton("Cancelar") { dialog, id -> }
        //        builder = builder.create();
        builder.show()

    }
}