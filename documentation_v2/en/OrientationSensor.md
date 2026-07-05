# Detailed Class Description: OrientationSensor

## 1. General Information
*   **Class Name:** `OrientationSensor`
*   **Type:** `Activity`
*   **Purpose:** This Activity tracks the orientation (tilt) of the device using the Accelerometer sensor. It shows how the phone is positioned in space along the X, Y, and Z axes.
*   **Interaction:** Launched from `MainActivity`. It registers the sensor as soon as the screen is created.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `sensorManager`| `SensorManager` | System service to access device hardware. | In `onCreate()` and `onStop()`. |
| `orientationSensor`| `Sensor` | Reference to the Accelerometer sensor. | In `onCreate()`. |
| `orientationListener`| `SensorEventListener`| Receives and handles acceleration data. | Defined in `onCreate()`. |
| `tvX, tvY, tvZ`| `TextView` | Displays the raw acceleration values. | In `onSensorChanged()`. |

## 3. Class Methods

### Method Name: `onCreate`
*   **What does it do:**
    1. Connects the UI components.
    2. Gets the `SensorManager`.
    3. Finds the `TYPE_ACCELEROMETER` sensor.
    4. Creates a listener that updates the `TextView`s with gravity/acceleration values (X, Y, Z).
    5. Starts listening to the sensor.
*   **When called:** When the Activity is created.

### Method Name: `onStop`
*   **What does it do:** Stops the sensor listener to save battery when the user leaves the screen.

## 4. Lifecycle
*   **`onCreate()`**: Start phase.
*   **`onStop()`**: Cleanup phase.

## 5. Interface Interaction (UI)
*   **Elements:** 3 `TextView` objects for X, Y, and Z axes.

## 6. General Logic
1. User tilts the phone.
2. The Accelerometer detects the change in gravity force on each axis.
3. The app displays these numbers. For example, if the phone is flat on a table, the Z-axis should show approximately 9.8 (earth's gravity).

## 7. Bug Alert & Improvements (Important for Students!) ⚠️
- **Naming Confusion:** In the code, the sensor is named `orientationSensor`, but it is actually fetching `Sensor.TYPE_ACCELEROMETER`. While accelerometers help calculate orientation, they are not the same thing. 
- **Better Approach:** For precise orientation (Compass/Level), developers should use `Sensor.TYPE_ROTATION_VECTOR` or combine Accelerometer and Magnetometer data.

## 8. Simplified Explanation
**"Explanation in simple words"**
Think of a **bowl of water** inside your phone. When you tilt the phone, the water moves. This class reads exactly where the water is splashing. It tells the phone if it's lying flat, standing up, or being held sideways.

---
**Note for students:** Raw accelerometer data includes the force of gravity (9.8 m/s²). If you drop the phone, all values will briefly go to 0!
