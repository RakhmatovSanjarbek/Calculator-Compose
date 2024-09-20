package sanjarbek.uz.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var expression: String by remember { mutableStateOf("0") }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    text = expression,
                    fontSize = when {
                        expression.length > 24 -> 24.sp
                        expression.length > 18 -> 32.sp
                        expression.length > 12 -> 40.sp
                        expression.length > 6 -> 48.sp
                        else -> 56.sp
                    },
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.color_white),
                    textAlign = TextAlign.End
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    MayButton(
                        onClick = {
                            if (expression != "0") {
                                expression = removeLastChar(expression)
                            }
                        },
                        text = "AC",
                        backgroundColorRes = R.color.color_gray5,
                        tintColor = R.color.color_black,
                    )
                    MayButton(
                        onClick = {
                            if (expression != "0") {
                                val intValue = expression.toIntOrNull()
                                val doubleValue = expression.toDoubleOrNull()

                                expression = when {
                                    intValue != null -> (intValue * -1).toString()
                                    doubleValue != null -> (doubleValue * -1).toString()
                                    else -> expression
                                }
                            }
                        },
                        text = "+/-",
                        backgroundColorRes = R.color.color_gray5,
                        tintColor = R.color.color_black
                    )
                    MayButton(
                        onClick = {
                            if (expression != "0") {
                                val numericExpression = expression.toDoubleOrNull()
                                if (numericExpression != null) {
                                    expression = (numericExpression / 100).toString()
                                }
                            }
                        },
                        text = "%",
                        backgroundColorRes = R.color.color_gray5,
                        tintColor = R.color.color_black
                    )
                    MayButton(
                        onClick = {
                            expression =
                                if (containsOperator(expression) && !endsWithOperator(expression)) {
                                    val result = calculateExpression(expression)
                                    "$result$it"
                                } else {
                                    addOperator(expression, it)
                                }
                        },
                        text = "÷",
                        backgroundColorRes = R.color.color_yellow,
                        tintColor = R.color.color_white
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "7",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "8",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "9",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression =
                                if (containsOperator(expression) && !endsWithOperator(expression)) {
                                    val result = calculateExpression(expression)
                                    "$result$it"
                                } else {
                                    addOperator(expression, it)
                                }
                        },
                        text = "x",
                        backgroundColorRes = R.color.color_yellow,
                        tintColor = R.color.color_white
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "4",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "5",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "6",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression =
                                if (containsOperator(expression) && !endsWithOperator(expression)) {
                                    val result = calculateExpression(expression)
                                    "$result$it"
                                } else {
                                    addOperator(expression, it)
                                }
                        },
                        text = "-",
                        backgroundColorRes = R.color.color_yellow,
                        tintColor = R.color.color_white
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "1",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "2",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression = addNumber(expression, it)
                        },
                        text = "3",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            expression =
                                if (containsOperator(expression) && !endsWithOperator(expression)) {
                                    val result = calculateExpression(expression)
                                    "$result$it"
                                } else {
                                    addOperator(expression, it)
                                }
                        },
                        text = "+",
                        backgroundColorRes = R.color.color_yellow,
                        tintColor = R.color.color_white
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            if (expression != "0") {
                                expression += 0
                            }
                        },
                        modifier = Modifier
                            .width(180.dp)
                            .height(84.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.color_black33)
                        ),
                        shape = RoundedCornerShape(42.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            Text(
                                text = "0",
                                color = colorResource(id = R.color.color_white),
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Start
                            )
                        }
                    }
                    MayButton(
                        onClick = {
                            if (!endsWithOperator(expression) && !doubleOperator(expression)) {
                                expression += it
                            }
                        },
                        text = ".",
                        backgroundColorRes = R.color.color_black33,
                        tintColor = R.color.color_white
                    )
                    MayButton(
                        onClick = {
                            if (!endsWithOperator(expression)) {
                                expression = calculateExpression(expression)
                            }
                        },
                        text = "=",
                        backgroundColorRes = R.color.color_yellow,
                        tintColor = R.color.color_white
                    )
                }
            }
        }
    }
}

@Composable
fun MayButton(
    onClick: (String) -> Unit,
    text: String,
    backgroundColorRes: Int,
    tintColor: Int,
) {
    Button(
        onClick = {
            onClick(text)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = backgroundColorRes)
        ),
        shape = RoundedCornerShape(42.dp),
        modifier = Modifier
            .width(84.dp)
            .height(84.dp),
    ) {
        Text(
            text = text,
            color = colorResource(id = tintColor),
            fontSize = if (text == "AC") 24.sp else if (text == "+/-") 22.sp else 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private fun addNumber(expression: String, number: String): String {
    return if (expression == "0") {
        number
    } else {
        expression + number
    }
}

private fun removeLastChar(str: String): String {
    return when {
        str.length == 1 -> "0"
        str.isNotEmpty() -> str.substring(0, str.length - 1)
        else -> "0"
    }
}

private fun calculateExpression(expression: String): String {
    return try {
        val parts = expression.split(Regex("(?<=[-+x÷])|(?=[-+x÷])"))
        if (parts.size < 3) return "Xato"
        val num1 = parts[0].replace(" ", "").toDoubleOrNull() ?: return "Xato"
        val operator = parts[1].trim()
        val num2 = parts[2].replace(" ", "").toDoubleOrNull() ?: return "Xato"

        when (operator) {
            "+" -> (num1 + num2).toString()
            "-" -> (num1 - num2).toString()
            "x" -> (num1 * num2).toString()
            "÷" -> if (num2 != 0.0) (num1 / num2).toString() else "Nolga bo'lish xatosi"
            else -> "Noma'lum operator"
        }
    } catch (e: Exception) {
        "Xato"
    }
}


private fun endsWithOperator(expression: String): Boolean {
    return  expression.isNotEmpty() && expression.last() in listOf(
        '+',
        '-',
        'x',
        '÷',
        '.'
    )
}

private fun addOperator(expression: String, operator: String): String {
    return if (endsWithOperator(expression)) {
        expression.dropLast(1) + operator
    } else {
        expression + operator
    }
}

private fun containsOperator(expression: String): Boolean {
    if (expression.isNotEmpty() && expression.first() == '-') {
        return false
    }
    return expression.any { it in listOf('+', '-', 'x', '÷') }
}

private fun doubleOperator(expression: String): Boolean {
    return expression.any { it in listOf('.') }
}