package com.example.counterproject

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@Composable
fun SplashScreen (
    modifier: Modifier = Modifier,
    onTimeout: @Composable () -> Unit
){
    var isVisible by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        delay(3000)
        isVisible=false
        onTimeout
    }
    AnimatedVisibility(
        visible = isVisible

    ){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.compose),
                contentDescription = "Splash Screen",
                modifier=Modifier.fillMaxSize(0.3f)
            )
        }


    }


}