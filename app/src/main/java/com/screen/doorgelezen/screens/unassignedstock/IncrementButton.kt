package com.screen.doorgelezen.screens.unassignedstock

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.screen.doorgelezen.R
import kotlin.math.min

@Composable
fun IncrementButton(
    onChange: (selected: Int) -> Unit,
    modifier: Modifier = Modifier,
    selected: Int = 0,
    maxSelected: Int? = null
) {
    if (selected <= 0) {
        AddButton(
            modifier = modifier,
            enabled = maxSelected == null || maxSelected > 0
        ) {
            onChange(1)
        }
    } else {
        Incrementer(onChange, selected, maxSelected, modifier)
    }
}

@Composable
fun AddButton(modifier: Modifier, enabled: Boolean = true, onClick: () -> Unit) {
    val addString = stringResource(id = R.string.add)
    TextButton(onClick = onClick, modifier = modifier, enabled = enabled) {
        Icon(
            Icons.Filled.Add,
            contentDescription = addString,
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = addString.uppercase())
    }
}

@Composable
fun Incrementer(
    onChange: (selected: Int) -> Unit,
    selected: Int,
    maxSelected: Int?,
    modifier: Modifier
) {
    val thresholdReached = maxSelected != null && selected >= maxSelected
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        TextButton(onClick = { onChange(selected - 1) }) {
            Icon(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = stringResource(R.string.remove),
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
        Text(selected.toString())
        TextButton(
            enabled = !thresholdReached,
            onClick = {
                val newSelected = selected + 1
                onChange(min(newSelected, maxSelected ?: newSelected))
            }
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = stringResource(R.string.add),
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }
}