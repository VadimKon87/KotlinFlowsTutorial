package com.example.kotlinflowstutorial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    // Tutorial from https://youtu.be/ZX8VsqNO_Ss Part 1
    // and a little from part 2 - https://youtu.be/sk3svS_fzZM

    // in this example we use flows (which is a coroutine) to update UI each time our countdown
    // loop gives a new value (flows are used to notify when a change occurs)

    val countDownFlow = flow<Int>{
        val startingValue = 10
        var currentValue = startingValue
        emit(startingValue) // emits 10
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue) // emits 9, 8, 7..1
        }
    }
    /****************************************************************************************/

    // example of how to collect flows in log (instead of UI)
    // NOTE: collectLatest can be useful for using in cases when we need to update our UI state

    // also added operators as examples, they are not needed here

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            countDownFlow
                .filter { time ->
                    time % 2 == 0 // filter to get only even results
                }
                .map { time ->
                    time * time
                }
                .onEach { time ->
                    println(time)
                }
                .collect { time ->
                println("The current time is $time") // collect finishes the flow
            }
        }
    }
}