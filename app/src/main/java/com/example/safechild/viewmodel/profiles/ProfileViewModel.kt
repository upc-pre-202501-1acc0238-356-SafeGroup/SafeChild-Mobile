package com.example.safechild.viewmodel.profiles

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.safechild.model.storage.UserSessionManager

class ProfileViewModel : ViewModel() {

    var caregiverId by mutableStateOf<Long?>(null)

    fun getCurrentUserId(context: Context): Long {
        caregiverId = UserSessionManager.getUserId(context)
        return caregiverId ?: 0
    }


}