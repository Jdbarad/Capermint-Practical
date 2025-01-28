package com.jdbarad.jaypalsinhbarad.ui.splash

import android.graphics.Shader
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jdbarad.jaypalsinhbarad.R
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun SplashRoute(onSplashFinished: () -> Unit = {}) {
    SplashScreen(onSplashFinished = onSplashFinished)
}

@Preview
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onSplashFinished: () -> Unit = {}
) {

    LaunchedEffect(Unit) {
        delay(3.seconds)
        onSplashFinished()
    }

    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(
                    Color(0xFF00BCD4),
                    Color(0xFFE91E63),
                ),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.8f)
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(largeRadialGradient),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.ic_app_logo),
            contentDescription = stringResource(R.string.app_logo)
        )
    }
}
