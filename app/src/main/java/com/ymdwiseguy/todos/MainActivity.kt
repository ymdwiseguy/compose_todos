package com.ymdwiseguy.todos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ymdwiseguy.todos.ui.theme.TodosTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodosTheme {
                MainScreen()
            }
        }
    }

}