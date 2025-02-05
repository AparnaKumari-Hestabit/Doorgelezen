package com.screen.doorgelezen.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.screen.doorgelezen.R
import com.screen.doorgelezen.data.CookieJar

fun printDebug(value:String){
    Log.d("DEBUG", "printDebug: $value")
}

fun printError(value:String){
    Log.e("ERROR", "printDebug: $value")
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    return email.matches(emailRegex.toRegex())
}

fun raiseToast(context: Context,message : String, length : Int){
    if(context != null)
    {
        Toast.makeText(context, message, length).show()
    }
}

@Composable
fun LogoutDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(Icons.Default.Logout, stringResource(id = R.string.log_out))
                Text(stringResource(id = R.string.log_out))
            }
        },
        text = { Text(stringResource(id = R.string.log_out_confirm)) },
        onDismissRequest = {
            onCancel()
        },
        confirmButton = {
            TextButton({onConfirm()}) {
                Text(stringResource(id = R.string.log_out))
            }
        },
        dismissButton = {
            TextButton({onCancel()}) {
                Text(stringResource(R.string.cancel))
            }
        },
    )
}