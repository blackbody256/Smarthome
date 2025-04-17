package com.example.welcome

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun favPage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(20.dp) // Padding to prevent overlap with screen edges
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.grey_icon),
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No Favorites!",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color=Color.Gray,
                fontWeight = FontWeight.W400
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Add your favorite routines for easy access here.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color=Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tap the '+' button below to add your favorite routines.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color=Color.Gray
            )

        }

        // Floating Action Button at Bottom Right
        FloatingActionButton(
            onClick = {
            },
            containerColor = Color(0xFF00aae4),
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add Routine")
        }
    }
}

@Preview
@Composable
fun favPagePreview(){
    favPage()
}