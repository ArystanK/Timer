package kz.arctan.timer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kz.arctan.timer.ui.theme.TimerTheme
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.hours

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = viewModel<MainViewModel>()
            TimerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${viewModel.time.value / 100 / 3600}:${viewModel.time.value / 100 / 60 % 60}:${viewModel.time.value / 100 % 60}:${viewModel.time.value % 100}",
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(0.8f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Button(
                                onClick = { viewModel.pause() },
                                modifier = Modifier.padding(end = 10.dp)
                            ) {
                                Text("Pause")
                            }
                            Button(
                                onClick = { viewModel.play() },
                                modifier = Modifier.padding(end = 10.dp)
                            ) {
                                Text("Play")
                            }
                            Button(onClick = { viewModel.stop() }) {
                                Text("Stop")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TimerTheme {
        Greeting("Android")
    }
}