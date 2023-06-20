package com.begobang.marvel

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
    To be providing a Good Architectural patter to the app, we separated the UI from app module.
    In that way, from presentation module we can only see domain, and we will avoid brieching
    our architecture.
 */
@HiltAndroidApp
class App: Application()