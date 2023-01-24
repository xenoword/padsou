package com.example.padsou.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.padsou.ui.theme.Purple200
import com.example.padsou.ui.theme.Shapes

@Composable
fun Input (name: String, onValueChange: (String) -> Unit, placeholder: String,
           lineCount: Int = 1, kbType: KeyboardType = KeyboardType.Text)
{
        var isSingleLine: Boolean = false
        var kbTransformation: VisualTransformation = VisualTransformation.None

        if (kbType == KeyboardType.Password) {
                kbTransformation = PasswordVisualTransformation()
        }
        if (lineCount == 1) {
                isSingleLine = true
        }

        Row(horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                        visualTransformation = kbTransformation,
                        singleLine = isSingleLine,
                        keyboardOptions = KeyboardOptions(keyboardType = kbType),
                        maxLines = lineCount,
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Purple200,
                                unfocusedBorderColor = Color.Transparent,
                                backgroundColor = Color.White),
                        shape = RoundedCornerShape(20.dp),
                        textStyle = androidx.compose.ui.text.TextStyle.Default,
                        modifier = Modifier
                                .height(((lineCount-1)*17).dp + 55.dp)
                                .fillMaxWidth(),
                        value = name,
                        onValueChange = onValueChange,
                        placeholder = { Text(text = placeholder, color = Color(0xFFA6A6A6)) },
                )
        }
}