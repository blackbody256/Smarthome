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

@Composable
fun HomePage(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(16.dp) // Padding to prevent overlap with screen edges
    ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(15.dp))
            Image(
                painter = painterResource(id = R.drawable.dail),
                contentDescription = "Daily Routine Icon",
                modifier = Modifier.size(120.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "No things!",
                fontSize = 40.sp,
                color = Color.Gray,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "It looks like we didn't discover any devices.",
                fontSize = 19.sp,
                color = Color.LightGray
            )
            Text(
                text = "Try an option below.",
                fontSize = 19.sp,
                color = Color.LightGray
            )
            Spacer(modifier = Modifier.height(15.dp))
            Divider(thickness = 2.dp, color = Color.LightGray)


            Row(
                modifier = modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Left
            ){
                Button(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp) // Padding to prevent clipping
                        .size(60.dp), // Standard FAB size
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ){
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp))
                }
                Text(
                    text = "Run discovery",
                    color = Color(0xFF03A9F4),
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Left
            ){
                Button(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp) // Padding to prevent clipping
                        .size(60.dp), // Standard FAB size
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ){
                    Icon(imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp))
                }
                Text(
                    text = "Add cloud account",
                    color = Color(0xFF03A9F4),
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Left
            ){
                Button(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp) // Padding to prevent clipping
                        .size(60.dp), // Standard FAB size
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ){
                    Icon(imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp))
                }
                Text(
                    text = "View supported devices",
                    color = Color(0xFF03A9F4),
                    fontSize = 20.sp
                )
            }
            Row(
                modifier = modifier.fillMaxWidth(0.9f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Left
            ){
                Button(
                    onClick = { /*TODO*/ },
                    shape = CircleShape,
                    modifier = Modifier
                        .padding(10.dp) // Padding to prevent clipping
                        .size(60.dp), // Standard FAB size
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF03A9F4))
                ){
                    Icon(imageVector = Icons.Default.Email,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp))
                }
                Text(
                    text = "Contact Support",
                    color = Color(0xFF03A9F4),
                    fontSize = 20.sp
                )
            }

        }

    }
}



@Preview
@Composable
fun HomePagePreview(){
    HomePage()
}