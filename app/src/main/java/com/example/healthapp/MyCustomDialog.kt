package com.example.healthapp

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthapp.R

class MyCustomDialog(context : Context, myInterface: MyCustomDialogInterface) : Dialog(context) {

    private var myCustomDialogInterface: MyCustomDialogInterface = myInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialog)

        var okButton : Button = findViewById(R.id.okButton)
        var cancelButton : Button = findViewById(R.id.cancelButton)
        var recordEditView : EditText = findViewById(R.id.recordEditView)

        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        okButton.setOnClickListener {
            val content = recordEditView.text.toString()

            if ( TextUtils.isEmpty(content)){
                Toast.makeText(context, "기록하세요", Toast.LENGTH_SHORT).show()
            }

            else{
                myCustomDialogInterface.onOkButtonClicked(content)
                dismiss()
            }
        }

        cancelButton.setOnClickListener { dismiss()}
    }
}