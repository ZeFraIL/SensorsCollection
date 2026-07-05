# Detailed Class Description: ShakeAndStart

## 1. General Information
*   **Class Name:** `ShakeAndStart`
*   **Type:** `Activity`
*   **Purpose:** This Activity detects if the user is shaking the device. Once a significant shake is detected, it triggers a voice countdown and automatically returns the user to the `MainActivity`.
*   **Interaction:** It is launched from `MainActivity` and returns to it after the countdown. It uses the Accelerometer sensor and the Text-To-Speech (TTS) engine.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `SHAKE_LEVEL` | `int` | The sensitivity threshold (set to 14). Higher values require harder shaking. | In `onSensorChanged()` to check for shakes. |
| `tts` | `TextToSpeech` | The engine used to convert text into spoken words. | In `onSensorChanged()` and `onTick()`. |
| `stadb` | `String[]` | Array of phrases for the voice countdown. | In `onTick()` to speak. |
| `sensor_shake` | `Sensor` | Reference to the Accelerometer sensor. | In `initComponents()` and `onCreate()`. |
| `gotostart` | `Intent` | An instruction to go back to the `MainActivity`. | In `onFinish()` of the timer. |

## 3. Class Methods

### Method Name: `initComponents`
*   **Method Type:** `private`
*   **Return Value:** `void`
*   **What does it do:**
    1. Initializes the `Intent` for navigation.
    2. Gets the `SensorManager` and the `ACCELEROMETER` sensor.
    3. Defines the `sensorEventListener` which calculates the "sum of shake": `|X| + |Y| + |Z|`.
    4. If the sum exceeds `SHAKE_LEVEL`:
        - It initializes the `TextToSpeech` engine.
        - It starts a `CountDownTimer` for 8 seconds.
*   **When called:** From `onCreate()`.

### Method Name: `onTick` (inside CountDownTimer)
*   **What does it do:** Every 2 seconds, it picks the next phrase from the `stadb` array and uses `tts.speak()` to say it out loud.
*   **When called:** Automatically by the timer.

### Method Name: `onFinish` (inside CountDownTimer)
*   **What does it do:** 
    1. Unregisters the sensor listener to stop detecting shakes.
    2. Launches the `MainActivity`.
*   **When called:** When the 8-second countdown ends.

## 4. Lifecycle
*   **`onCreate()`**: Registers the sensor listener immediately.
*   **Missing Methods:** This class is missing `onPause()` or `onStop()` unregistration. 

## 5. Interface Interaction (UI)
*   **Elements:** No specific buttons are used; the whole interaction is based on physical movement (shaking) and sound.

## 6. General Logic
1. The user opens the screen.
2. The phone waits for a "shake".
3. When shaken, the phone says: "Three seconds to start", "Two seconds...", etc.
4. After the countdown, the app "jumps" back to the main menu.

## 7. Bug Alert & Improvements (Important for Students!) ⚠️
There are two major issues in this code that you should mention in your defense:
1.  **TTS Initialization:** The `new TextToSpeech(...)` is called inside `onSensorChanged`. This is **very bad**. If you shake the phone for 1 second, `onSensorChanged` might trigger 50 times. This creates 50 TTS objects, which will likely crash the app or slow it down significantly. 
    - *Fix:* Initialize `tts` once in `onCreate`.
2.  **Battery Drain:** The sensor is registered in `onCreate` but never unregistered if the user simply leaves the activity (e.g., by pressing the Back button) before the timer finishes.
    - *Fix:* Always unregister listeners in `onPause()`.

## 8. Simplified Explanation
**"Explanation in simple words"**
Imagine your phone is a **sleeping guard**. When you shake it, you "wake it up". The guard then starts counting out loud: "3, 2, 1... GO!". Once he finishes counting, he sends you back to the main entrance of the building.

---
**Note for students:** Using `Math.abs(x) + Math.abs(y) + Math.abs(z)` is a simple way to detect movement in any direction!
