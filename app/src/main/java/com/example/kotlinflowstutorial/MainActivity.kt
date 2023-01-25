package com.example.kotlinflowstutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinflowstutorial.ui.theme.KotlinFlowsTutorialTheme

/*
* What Is Kotlin Flows? You can define a flow in Kotlin as a coroutine that has multiple
* asynchronously computed values. It is a stream of data that can be computed asynchronously
* and is used to send multiple values in sequence. The data emitted must be of the same type and
* is also emitted in a sequential manner.
*
* So in this example it's a countdown clock with UI updating every second (see MainViewModel.kt)
* */

// Tutorial from https://youtu.be/ZX8VsqNO_Ss Part 1
// Didn't do part 2, to keep it simple for now


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinFlowsTutorialTheme {
                val viewModel = viewModel<MainViewModel>()
                val time = viewModel.countDownFlow.collectAsState(initial = 10)
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = time.value.toString(),
                        fontSize = 32.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}
