package com.example.web_sdk.caller

import com.example.web_sdk.response.RetrofitResponse
import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest
import com.robotemi.sdk.listeners.OnGoToLocationStatusChangedListener

class TemiCaller: Robot.TtsListener, OnGoToLocationStatusChangedListener {

    private val retrofitResponse = RetrofitResponse()

    private var temiRobot: Robot

    private var ttsStatus = false
    private var gotoStatus = false

    init {
        temiRobot = Robot.getInstance()
        temiRobot.addTtsListener(this)
        temiRobot.addOnGoToLocationStatusChangedListener(this)
    }

    fun speakCaller(text: String): Boolean {
        temiRobot.speak(TtsRequest.create(text, false, TtsRequest.Language.IT_IT, true))
        while (!ttsStatus) Thread.sleep(1000)
        ttsStatus = false
        Thread.sleep(1000)
        retrofitResponse.sendCallback(text)
        return true
    }

    fun gotoCaller(location: String): Boolean {
        temiRobot.goTo(location)
        while (!gotoStatus) Thread.sleep(1000)
        gotoStatus = false
        Thread.sleep(1000)
        retrofitResponse.sendCallback(location)
        return true
    }

    fun followCaller() = temiRobot.beWithMe()

    fun unfollowCaller() = temiRobot.stopMovement()

    override fun onTtsStatusChanged(ttsRequest: TtsRequest) {
        ttsStatus = when (ttsRequest.status) {
                        TtsRequest.Status.COMPLETED -> { true }
                        else -> { false }
                    }
    }

    override fun onGoToLocationStatusChanged(
        location: String,
        status: String,
        descriptionId: Int,
        description: String
    ) {
        gotoStatus = when (status) {
                        "complete" -> { true }
                        else -> { false }
                    }
    }

}