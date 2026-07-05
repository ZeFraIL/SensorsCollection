# Detailed Class Description: StepByStep

## 1. General Information
*   **Class Name:** `StepByStep`
*   **Type:** `Activity`
*   **Purpose:** This Activity acts as a Pedometer (Step Counter). It uses the device's step counter sensor to track how many steps the user has taken while the app is active.
*   **Interaction:** Launched from `MainActivity`. It requests a special permission (`ACTIVITY_RECOGNITION`) from the user to access physical activity data.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `all_steps` | `int` | Stores the total number of steps counted in the current session. | In `onSensorChanged()` to update the count. |
| `tv_sbs` | `TextView` | Displays the step count text on the screen. | In `onSensorChanged()` and `initComponents()`. |
| `iv_sbs` | `ImageView` | Shows an image of a person walking or standing. | In `bStart` and `bStop` click listeners. |
| `stepCounterSensor` | `Sensor` | Reference to the physical Step Counter sensor. | In `initComponents()` and `registerListener()`. |

## 3. Class Methods

### Method Name: `onCreate`
*   **What does it do:**
    1. Requests the `ACTIVITY_RECOGNITION` permission (required for Android 10+).
    2. Initializes the components.
    3. Sets up the **Start** button: shows the "walking" image and starts listening to the sensor.
    4. Sets up the **Stop** button: shows the "standing" image and stops listening.
*   **When called:** When the Activity is created.

### Method Name: `onSensorChanged`
*   **What does it do:**
    1. Triggers a small "Toast" message saying "Step".
    2. Retrieves the step count from `event.values[0]`.
    3. Adds the value to `all_steps` and updates the `TextView`.
*   **When called:** Every time the sensor detects a step.

## 4. Lifecycle
*   **`onCreate()`**: Setup and permission request.
*   **Important:** This class relies on manual start/stop via buttons rather than `onResume`/`onPause`.

## 5. Interface Interaction (UI)
*   **Elements:** `ImageView` (`iv_sbs`), `TextView` (`tv_sbs`), `Button` (`bStart`, `bStop`).
*   **Events:** Button clicks to control the sensor.

## 6. Interaction with other components
*   **Permissions:** Uses `ActivityCompat.requestPermissions`. This is a system-level interaction.

## 7. Bug Alert & Improvements (Important for Students!) ⚠️
There is a logic error in how steps are calculated:
- **The Bug:** `TYPE_STEP_COUNTER` returns the total steps taken since the **phone was last restarted**. If the sensor says "5000", and you add it to `all_steps`, the count will be wrong. 
- **The Fix:** You should record the "start value" when the user clicks "Start" and then subtract that start value from the current sensor reading to get "steps in this session".
- **Permission Handling:** The code asks for permission but doesn't check if the user said "Yes" or "No". If they say "No", the app might not work correctly.

## 8. Simplified Explanation
**"Explanation in simple words"**
Imagine your phone has a **pedometer** like the ones people clip to their belts. This class is the **display** for that pedometer. You press "Start" to see the walking man, and as you walk, the "brain" of the app listens to the "clicks" from the sensor and updates the number on the screen.

---
**Note for students:** `ACTIVITY_RECOGNITION` is a "dangerous" permission, which is why the app must ask the user for it explicitly at runtime.
