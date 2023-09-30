package ca.josuelubaki

import moe.tlaster.precompose.PreComposeApplication

actual fun getPlatformName(): String = "iOS"

fun MainViewController() = PreComposeApplication { App() }