package com.healiostestproject.android

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.widget.ProgressBar
import android.widget.TextView

class IndeterminateTransparentProgressDialog private constructor(context: Context) : Dialog(context, R.style.ThemeProgressDialog) {
    companion object {

        @JvmOverloads
        fun show(context: Context, indeterminate: Boolean = false, cancelable: Boolean = false, cancelListener: DialogInterface.OnCancelListener? = null, message: String): IndeterminateTransparentProgressDialog {
            val dialog = IndeterminateTransparentProgressDialog(context)
            dialog.setCancelable(cancelable)
            dialog.setOnCancelListener(cancelListener)
            /* The next line will add the ProgressBar to the dialog. */

            val contentContainer = LayoutInflater.from(context).inflate(R.layout.progress_with_message, null)
            val messageView = contentContainer.findViewById<TextView>(R.id.message)
            messageView.text = message

            dialog.addContentView(contentContainer, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
            dialog.show()

            return dialog
        }

        @JvmOverloads
        fun show(context: Context, indeterminate: Boolean = false, cancelable: Boolean = false, cancelListener: DialogInterface.OnCancelListener? = null): IndeterminateTransparentProgressDialog {
            val dialog = IndeterminateTransparentProgressDialog(context)
            dialog.setCancelable(cancelable)
            dialog.setOnCancelListener(cancelListener)
            /* The next line will add the ProgressBar to the dialog. */
            val contentContainer = LayoutInflater.from(context).inflate(R.layout.progress_with_message, null)
            val messageView = contentContainer.findViewById<TextView>(R.id.message)
            messageView.visibility = View.GONE

            dialog.addContentView(contentContainer, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT))
            dialog.show()

            return dialog
        }
    }
}
