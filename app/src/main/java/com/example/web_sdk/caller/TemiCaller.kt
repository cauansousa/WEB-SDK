package com.example.web_sdk.caller

import com.robotemi.sdk.Robot
import com.robotemi.sdk.TtsRequest

class TemiCaller {

    private var temiRobot: Robot = Robot.getInstance()

    fun speakCaller(text: String) {
        temiRobot.speak(TtsRequest.create(text, false))
    }

    fun gotoCaller(location: String) {
        temiRobot.goTo(location)
    }

    fun followCaller() {
        temiRobot.beWithMe()
    }

    fun unfollowCaller() {
        val x = temiRobot.stopMovement()
        return x
    }

}