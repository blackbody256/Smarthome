package com.example.mysmarthome.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ButtonList() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        ButtonListItem(icon = Icons.Filled.Search, text = "Run discovery")
        Spacer(modifier = Modifier.height(8.dp))
        ButtonListItem(icon = Icons.Filled.Add, text = "Add a cloud account")
        Spacer(modifier = Modifier.height(8.dp))
        ButtonListItem(icon = Icons.Filled.List, text = "View our supported devices")
        Spacer(modifier = Modifier.height(8.dp))
        ButtonListItem(icon = Icons.Filled.Email, text = "Contact support")
    }
}

@Composable
fun ButtonListItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle button click */ }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = Color(0xFF00aae4),
            shape = CircleShape,
            contentColor = Color.White,
            modifier = Modifier
                .size(40.dp)
        )
        {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = Color(0xFF00aae4),
            fontSize = 18.sp
        )
    }
}