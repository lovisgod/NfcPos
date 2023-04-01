package com.lovisgod.nfcpos

import android.app.Application

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        POSApplication.initialize(this.applicationContext)
    }
}