package com.regaslzr.kprojstarter.common.dialog

import android.os.Bundle
import android.view.View
import com.regaslzr.kprojstarter.R
import com.regaslzr.kprojstarter.base.view.BaseDialog
import com.regaslzr.kprojstarter.common.extensions.logDebug
import kotlinx.android.synthetic.main.dialog_message.*

/**
 *
 * This is a dialog containing:
 * [1] a single line of text as the message
 * [2] an Okay button
 *
 * Pressing the Okay button is the only way to close/dismiss this dialog, apart from
 * the host explicitly calling dismiss() or safelyClose() functions.
 *
 * The message and the button text can be modified via updateArguments() call
 *
 * Sample Usage can be found in GenderizeNameView class
 */
class MessageDialog : BaseDialog() {

    override fun applyArguments() {
        logDebug(TAG, "applying arguments")

        arguments?.let {
            logDebug(TAG, "applyArguments() textView is: $messageDialog_textView_message")
            logDebug(TAG, "applyArguments() message is: ${arguments?.getString(ARG_MESSAGE) ?: DEFAULT_ARG_MESSAGE}")
            messageDialog_textView_message?.text = arguments?.getString(ARG_MESSAGE) ?: DEFAULT_ARG_MESSAGE
            messageDialog_button_action?.text = arguments?.getString(ARG_BUTTON_TEXT) ?: DEFAULT_ARG_BUTTON_TEXT
        }
    }

    override fun getLayoutResourceId(): Int = R.layout.dialog_message

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareElements()
        applyArguments()
    }

    fun updateArguments(message: String = DEFAULT_ARG_MESSAGE,
                        buttonText: String = DEFAULT_ARG_BUTTON_TEXT) {
        logDebug(TAG, "updateArguments() called. Message is: $message")

        val args = Bundle()
        args.putString(ARG_MESSAGE, message)
        args.putString(ARG_BUTTON_TEXT, buttonText)
        arguments = args
    }

    private fun prepareElements() {
        messageDialog_button_action.setOnClickListener {
            safelyClose()
        }
    }

    companion object {
        val TAG: String = MessageDialog::class.java.simpleName
        val ARG_BUTTON_TEXT= "$TAG.ButtonText"
        val ARG_MESSAGE = "$TAG.Message"

        private val DEFAULT_ARG_MESSAGE = "-"
        private val DEFAULT_ARG_BUTTON_TEXT = "Okay"
    }

}