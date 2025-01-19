package com.dyno.androidtodostarter

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dyno.androidtodostarter.ui.theme.AndroidtodostarterTheme
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScreenshotTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testScreenshot() {
        composeTestRule.setContent {
            AndroidtodostarterTheme {
                Greeting(name = "Roborazzi")
            }
        }

        composeTestRule.onRoot().captureRoboImage("GreetingScreenshot")
    }
}
