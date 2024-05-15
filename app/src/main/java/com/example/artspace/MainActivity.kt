package com.example.artspace

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenHeight: Dp
        val displayMetrics = resources.displayMetrics
        val screenHeightDp = (displayMetrics.heightPixels / displayMetrics.density).dp
        if (screenHeightDp < 480.dp) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            screenHeight = 500.dp
        } else if (screenHeightDp >= 900.dp){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
            screenHeight = 900.dp
        } else {
            screenHeight = 500.dp
        }
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp(screenHeight)
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(screenHeight: Dp,
                modifier: Modifier = Modifier
) {
    var curState by remember { mutableIntStateOf(1) }
    val paint: Painter
    val title: String
    val author: String
    val year: String
    when (curState){
        1 -> {
            paint = painterResource(id = R.drawable.mona_lisa)
            title = stringResource(id = R.string.mona_lisa_paint)
            author = stringResource(id = R.string.mona_lisa_author)
            year = stringResource(id = R.string.mona_lisa_year)
        }
        2 -> {
            paint = painterResource(id = R.drawable.starry_night)
            title = stringResource(id = R.string.starry_night_paint)
            author = stringResource(id = R.string.starry_night_author)
            year = stringResource(id = R.string.starry_night_year)
        }
        3 -> {
            paint = painterResource(id = R.drawable.scream)
            title = stringResource(id = R.string.scream_paint)
            author = stringResource(id = R.string.scream_author)
            year = stringResource(id = R.string.scream_year)
        }
        else -> {
            paint = painterResource(id = R.drawable.ic_launcher_background)
            title = "unknown"
            author = "unknown"
            year = "unknown"
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .statusBarsPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 10.dp)
            .safeDrawingPadding(),

        ) {
        Surface(
            modifier = modifier
                .padding(20.dp)
                .shadow(4.dp)
                .height(screenHeight)
                .width(700.dp)
        ) {
            Image(
                painter = paint,
                contentDescription = null,
                modifier = modifier
                    .padding(20.dp)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(20.dp)
                .fillMaxWidth()
                .background(Color(0xFFCCCCFF))
                .wrapContentHeight()
        ) {
            Text(text = title, fontFamily = FontFamily.Cursive, fontSize = 30.sp)
            Text(text = author, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = year, fontSize = 20.sp)
        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier.fillMaxWidth()
        ) {
            Button(
                modifier = modifier.width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9999FF)),
                onClick = {
                    if (curState > 1) curState -= 1 else curState = 3
                }
            ) {
                Text(text = "Previous")
            }
            Button(
                modifier = modifier.width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9999FF)),
                onClick = {
                    if (curState < 3) curState += 1 else curState = 1
                }
            ) {
                Text(text = "Next")
            }
        }
    }
}
