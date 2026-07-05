# Detailed Class Description: MagnetField

## 1. General Information
*   **Class Name:** `MagnetField`
*   **Type:** `Activity`
*   **Purpose:** This Activity measures the ambient magnetic field around the device. It can be used to detect the Earth's magnetic poles (like a compass) or to find nearby magnets and metal objects.
*   **Interaction:** Launched from `MainActivity`. It automatically starts measuring when opened and stops when the user leaves the screen.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `sensorManager`| `SensorManager` | System service to manage sensors. | In `onCreate()` and `onStop()`. |
| `magneticFieldSensor`| `Sensor` | Reference to the Magnetometer hardware. | In `onCreate()`. |
| `magneticFieldListener`| `SensorEventListener`| Handles incoming magnetic data. | Defined in `onCreate()`. |
| `tvMX, tvMY, tvMZ`| `TextView` | Displays the magnetic field strength for X, Y, Z axes. | In `onSensorChanged()`. |

## 3. Class Methods

### Method Name: `onCreate`
*   **What does it do:**
    1. Sets the layout.
    2. Initializes TextViews.
    3. Finds the `MAGNETIC_FIELD` sensor.
    4. Defines the listener: reads `values[0, 1, 2]` (measured in **micro-Tesla, µT**) and displays them on the screen.
    5. Registers the listener immediately so measurement starts right away.
*   **When called:** When the Activity starts.

### Method Name: `onStop`
*   **What does it do:** Unregisters the magnetic listener.
*   **When called:** When the Activity is no longer visible to the user.
*   **Important:** Using `onStop` instead of `onPause` is acceptable, but `onPause` is usually preferred for faster resource release.

## 4. Lifecycle
*   **`onCreate()`**: Initialization and starting the sensor.
*   **`onStop()`**: Stopping the sensor to save power.

## 5. Interface Interaction (UI)
*   **Elements:** 3 `TextView`s.
*   **Events:** None (automatic updates).

## 6. General Logic
1. User opens the Magnetometer screen.
2. The phone detects the magnetic pull of the environment.
3. The X, Y, and Z values update as the user moves the phone near a piece of metal or turns it towards the North Pole.

## 7. Simplified Explanation
**"Explanation in simple words"**
Think of your phone as having a **built-in compass needle**. This class reads how that needle is being pulled by invisible magnetic forces. If you bring a kitchen magnet close to the phone, you will see the numbers jump up!

---
**Note for students:** Magnetic field strength is measured in micro-Teslas. Normal Earth background is usually between 25 and 65 µT.
