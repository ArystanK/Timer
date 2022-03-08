package kz.arctan.timer

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private var timeCurrent = 0
    private var counting = true
    private val _time = mutableStateOf(0)
    val time: State<Int>
        get() = _time

    private val timeCount = flow {
        emit(timeCurrent)
        while (counting) {
            delay(10)
            timeCurrent++
            emit(timeCurrent)
        }
    }

    fun pause() {
        counting = false
    }

    fun play() {
        counting = true
        viewModelScope.launch {
            timeCount.collect {
                _time.value = it
            }
        }
    }

    fun stop() {
        timeCurrent = 0
        _time.value = 0
        counting = false
    }
}