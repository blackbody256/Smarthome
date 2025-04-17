package com.example.welcome


import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.mysmarthome.ui.components.ButtonList

@Composable
fun HomePage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp) // Padding to prevent overlap with screen edges
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(id = R.drawable.dail),
                contentDescription = "Daily com.example.welcome.Routine.Routine Icon",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No things!",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "It looks like we didn't discover any devices.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Try an option below.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.padding(start = 20.dp, end = 20.dp))

            Spacer(modifier = Modifier.height(10.dp))
            ButtonList()
        }
    }
}

@Preview
@Composable
fun HomePagePreview(){
    HomePage()
}