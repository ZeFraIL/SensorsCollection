# Detailed Class Description: GyroscopeSensor

## 1. General Information
*   **Class Name:** `GyroscopeSensor`
*   **Type:** `Activity`
*   **Purpose:** This Activity measures the rotation speed of the device around its three physical axes (X, Y, and Z). It is used to detect how fast the phone is being turned or tilted.
*   **Interaction:** Launched from `MainActivity`. It uses a manual registration system for its sensor listener.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `tvGX, tvGY, tvGZ`| `TextView` | Displays the rotation speed values for the X, Y, and Z axes. | In `onSensorChanged()`. |
| `bRegL` | `Button` | The "Register" button. Starts the sensor. | In `onCreate()`. |
| `bUnRegL` | `Button` | The "Unregister" button. Stops the sensor. | In `onCreate()`. |
| `sensorManager`| `SensorManager` | System service to access sensors. | Throughout the class. |
| `gyroscopeSensor`| `Sensor` | Reference to the Gyroscope hardware. | To start/stop listening. |
| `gyroscopeListener`| `SensorEventListener`| The logic that handles incoming data. | Defined in `onCreate()`. |

## 3. Class Methods

### Method Name: `onCreate`
*   **What does it do:**
    1. Sets up the UI.
    2. Calls `initComponents()`.
    3. Finds the Gyroscope sensor. If it doesn't exist, shows a warning.
    4. Defines the `gyroscopeListener`. When the sensor moves, it takes the X, Y, Z speeds and updates the text on the screen.
    5. Sets the `bRegL` button to start listening when clicked.
    6. Sets the `bUnRegL` button to stop listening and reset the text to "?" when clicked.
*   **When called:** When the Activity is created.

### Method Name: `onSensorChanged` (Inside the listener)
*   **Parameters:** `SensorEvent event`.
*   **What does it do:**
    1. Checks if the data is from the Gyroscope.
    2. Reads `values[0]` (X), `values[1]` (Y), and `values[2]` (Z).
    3. Updates the screen labels with these numbers.
*   **When called:** Every time the device is rotated.

## 4. Lifecycle
*   **`onCreate()`**: Main setup.
*   **Feature:** This class **does not** automatically stop the sensor when the screen is closed (it's missing `onPause` unregistration). The user must press the "Unregister" button manually.

## 5. Interface Interaction (UI)
*   **Elements:** 3 `TextView`s and 2 `Button`s.
*   **Interaction:** The user has full control over when the sensor is active.

## 6. Interaction with other components
*   **Hardware:** Direct communication with the Gyroscope via `SensorManager`.

## 7. General Logic
1. User opens the Gyroscope screen.
2. They see speed values as "?".
3. They press "Register".
4. The phone starts measuring rotation. As they move the phone, the numbers on the screen change rapidly.
5. They press "Unregister" to stop.

## 8. Simplified Explanation
**"Explanation in simple words"**
Imagine your phone has a **fidget spinner** inside it. When you turn the phone, the spinner reacts. The `GyroscopeSensor` class is like a **speedometer** for that spinner. It tells you exactly how fast you are spinning the phone in three different directions.

---
**Note for students:** In a real app, you should always unregister in `onPause()` to prevent the sensor from running in the background if the user forgets to click "Unregister".
