package com.example.padsou.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.padsou.ui.theme.Purple200

@Composable
fun SearchBar (name: String, onValueChange: (String) -> Unit, placeholder: String)
{
    Row(horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Purple200,
                        unfocusedBorderColor = Color.Transparent,
                        backgroundColor = Color.White),
                shape = RoundedCornerShape(20.dp),
                textStyle = TextStyle.Default,
                modifier = Modifier
                        .height(55.dp)
                        .fillMaxWidth(),
                value = name,
                onValueChange = onValueChange,
                placeholder = { Text(text = placeholder) },
                leadingIcon = { Icon(Icons.Rounded.Search,
                        null,
                tint = Color.LightGray)}
        )
    }
}