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

class UpdateDialog(context : Context, updateDialogInterface : UpdateDialogInterface) : Dialog(context) {

    // 액티비티에서 인터페이스를 받아옴
    private var updateDialogInterface: UpdateDialogInterface = updateDialogInterface

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
                Toast.makeText(context, "수정할 내용을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }

            else{
                updateDialogInterface.onOkButtonClicked(content)
                dismiss()
            }
        }

        cancelButton.setOnClickListener { dismiss()}
    }
}