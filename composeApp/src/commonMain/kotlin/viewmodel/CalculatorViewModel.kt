package viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.ProvidedValue
import androidx.lifecycle.ViewModel
import data.Operator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CalculatorViewModel : ViewModel()  {

    private val _display = MutableStateFlow("")
    val display: StateFlow<String> = _display

    private val _result = MutableStateFlow("")
    val result: StateFlow<String> = _result


    private var operand1: String = ""
    private var operand2: String = ""
    private var operator: Operator? = null

    fun onNumberClick(number: String) {
        if (operator == null) {
            operand1 += number
        } else {
            operand2 += number
        }

        updateDisplay()
    }

    fun onOperatorClick(op: Operator) {
        if (operand1.isNotEmpty()) {
            operator = op
        }
        updateDisplay()
    }

    fun onEqualsClick() {
        if (operand1.isNotEmpty() && operand2.isNotEmpty() && operator != null) {
            val calculateTotal = when (operator) {
                Operator.ADD -> operand1.toDouble() + operand2.toDouble()
                Operator.SUBTRACT -> operand1.toDouble() - operand2.toDouble()
                Operator.MULTIPLY-> operand1.toDouble() * operand2.toDouble()
                Operator.DIVIDE -> operand1.toDouble() / operand2.toDouble()
                Operator.MODULO -> operand1.toDouble() % operand2.toDouble()
                else -> 0.0
            }

            _result.value = if (calculateTotal == calculateTotal.toLong().toDouble()) {
                calculateTotal.toLong().toString()
            } else {
                calculateTotal.toString()
            }

            updateDisplay()
        }
    }

    fun onClearClick() {
        operand1 = ""
        operand2 = ""
        _result.value = ""
        operator = null
        updateDisplay()
    }

    fun onToggleSignClick() {
        if (operator == null) {
         operand1 = toggleSign(operand1)
        } else {
            operand2 = toggleSign(operand2)
        }
        updateDisplay()
    }

    fun onBackspaceClick() {
        when {
            operand2.isNotEmpty() -> {
                operand2 = operand2.dropLast(1)
            }

            operator != null -> {
                operator = null
            }

            operand1.isNotEmpty() -> {
                operand1 = operand1.dropLast(1)
            }
        }
        updateDisplay()
    }

    private fun toggleSign(value: String): String {
        return if (value.startsWith("-")) {
            value.substring(1)
        } else{
            "-$value"
        }
    }

    private fun updateDisplay() {
        _display.value = operand1 + (operator?.let {  " ${it.symbol} " } ?: "") + operand2
    }


}