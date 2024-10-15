package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.textResult)

        val buttonIds = listOf(
            R.id.btnBS, R.id.btnCE, R.id.btnC,
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btnCongTru,
            R.id.btnChia, R.id.btnNhan, R.id.btnTru,
            R.id.btnCong, R.id.btnBang
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener(this)
        }


    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)
            R.id.btnCong -> setOperation(1)
            R.id.btnTru -> setOperation(2)
            R.id.btnNhan -> setOperation(3)
            R.id.btnChia -> setOperation(4)
            R.id.btnCongTru -> toggleSign()
            R.id.btnBang -> calculate()
            R.id.btnC -> reset()
            R.id.btnCE -> clearEntry()
            R.id.btnBS -> backspace()
        }

    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            textResult.text = "$op2"
        }
    }

    private fun setOperation(operation: Int) {
        op = operation
        state = 2
    }

    private fun toggleSign() {
        if (state == 1) {
            op1 = -op1
            textResult.text = op1.toString()
        } else {
            op2 = -op2
            textResult.text = op2.toString()
        }
    }

    private fun calculate() {
        val result = when (op) {
            1 -> op1 + op2
            2 -> op1 - op2
            3 -> op1 * op2
            4 -> op1/op2
            else -> op1
        }

        textResult.text = result.toString()

        reset()
        op1 = result
    }

    private fun reset() {
        state = 1
        op1 = 0
        op2 = 0
        op = 0
    }

    private fun clearEntry() {
        if (state == 1) {
            op1 = 0
        } else {
            op2 = 0
        }
        textResult.text = "0"
    }

    private fun backspace() {
        if (state == 1) {
            op1 = (op1 / 10)
            textResult.text = op1.toString()
        } else {
            op2 = (op2 / 10)
            textResult.text = op2.toString()
        }
    }
}