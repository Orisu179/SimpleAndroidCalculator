package com.example.calculator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun CalculatorLayout(backgroundColor: Color, viewModel: CalculatorViewModel) {
    val buttonSize = 95.dp
    val buttonPadding = 8.dp
    val verticalPadding = 10.dp

    Column(
        modifier = Modifier.fillMaxSize(),
    )
    {
        FieldInput(textInput = viewModel.input.value, result = viewModel.returnResult(), {})
        Surface (
            modifier = Modifier.fillMaxSize(),
            color = backgroundColor
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Grid(
                    buttonSize,
                    buttonPadding,
                    verticalPadding) {
                    viewModel.concatNumber(it.toString())
                }
                OperationRow(buttonSize, buttonPadding, verticalPadding) {
                    viewModel.concatOperation(it)
                }
            }
        }
    }
}

@Composable
fun OperationRow(
    buttonSize: Dp,
    buttonPadding: Dp,
    verticalPadding: Dp,
    operationButtonAction: (operation: String) -> Unit) {
    val calIcons = arrayOf(
        "/",
        "*",
        "-",
        "+",
        "=",
    )
    Column (
        modifier = Modifier.padding(vertical = verticalPadding),
    ) {
        calIcons.forEach { OperationButtons(symbol = it, buttonSize, buttonPadding) { operationButtonAction(it) } }
    }
}

@Composable
fun FieldInput(textInput: String, result: String = "", topButton: () -> Unit){
    Card(
        modifier = Modifier.size(height = 300.dp, width = 400.dp)
    ) {
        Column (
            horizontalAlignment = Alignment.End,
            modifier = Modifier.fillMaxWidth(),
        ){
            Button(
                onClick = topButton,
                modifier = Modifier.padding(end = 15.dp, top = 5.dp)
            ){

            }
        }
        Box (
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ){
            Text(
                text = textInput,
                fontSize = 60.sp,
                modifier = Modifier.padding(bottom = 50.dp, end = 15.dp, top = 15.dp),
            )
            Text(
                text = result,
                fontSize = 30.sp,
                modifier = Modifier.padding(bottom = 15.dp, end = 15.dp, top = 15.dp)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Grid(buttonSize: Dp,
         buttonPadding: Dp,
         verticalPadding:Dp,
         numberButtonAction: (number: Number) -> Unit){
    val row = 3
    val firstRowIcons = arrayOf(
        Icons.Default.Delete,
        Icons.Default.Check,
        Icons.Default.ArrowBack
    )

    FlowRow(
        modifier = Modifier.padding(vertical = verticalPadding),
        horizontalArrangement = Arrangement.SpaceAround,
        maxItemsInEachRow = row
    ) {
        firstRowIcons.forEach {
            IconButton(icon = it, sizeValue = buttonSize, paddingValue = buttonPadding, onClick = {})
        }
        for (i in 1..9){
            NumberButton(number = i, sizeValue = buttonSize, paddingValue = buttonPadding, onClick = numberButtonAction)
        }
    }
}

@Composable
fun NumberButton(number: Number, sizeValue: Dp, paddingValue: Dp, onClick: (number: Number) -> Unit){
    Button(
        modifier = Modifier
            .size(size = sizeValue)
            .padding(all = paddingValue),
        onClick = { onClick(number) }
    ){
        Text(
            text = number.toString(),
            fontSize = 30.sp
        )
    }
}

@Composable
fun IconButton(icon: ImageVector, sizeValue: Dp, paddingValue: Dp, onClick: (name: String) -> Unit){
    Button(
        modifier = Modifier
            .size(size = sizeValue)
            .padding(all = paddingValue),
        onClick = { onClick(icon.name) }
    ) {
        Icon(icon, "")
    }
}

@Composable
fun OperationButtons(symbol: Any, buttonSize: Dp, buttonPadding: Dp, onClick: () -> Unit){
    Button(
        modifier = Modifier
            .size(buttonSize)
            .padding(buttonPadding),
        onClick = onClick
    ) {
        when(symbol) {
            is String -> Text(text = symbol, fontSize = 30.sp)
            is ImageVector -> Icon(imageVector = symbol, contentDescription = "")
        }
    }
}
