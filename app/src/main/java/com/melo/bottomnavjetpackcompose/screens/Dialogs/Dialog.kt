package com.melo.bottomnavjetpackcompose.screens.Dialogs

import androidx.compose.material.AlertDialog
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    dismissOn: Boolean?


) {
    AlertDialog(

        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText)
        },
        onDismissRequest = {
            onDismissRequest()
        },





        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {

                Text("Ok")
            }
        }   ,

        dismissButton = {
            if(dismissOn == true){
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {

                    Text("Fechar")


            }
        } }



    )
}
