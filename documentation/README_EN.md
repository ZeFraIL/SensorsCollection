# 📱 Android App Documentation: SensorsCollection (Level 10/10)

________________________________________
## 🧾 General Information
**Project Name:**  
SensorsCollection

**Author(s):**  
Zeev Fraiman

**Date:**  
May 2024

**Language:**  
Java

**Development Environment:**  
Android Studio

**Android Version (minSdk / targetSdk):**  
26 / 34
________________________________________
## 🎯 Project Goal
•	The application is designed to demonstrate how to work with various hardware sensors on an Android device.  
•	This is important for understanding the interaction between software and the smartphone's hardware.  
•	Target Audience: Developers, students, and users interested in their device's capabilities.
________________________________________
## 📌 Requirements
**Functional Requirements**
•	Display a list of all available sensors on the device.  
•	Receive and visualize real-time data from the gyroscope, magnetometer, and orientation sensor.  
•	Step counting (Pedometer).  
•	Ambient light level measurement with UI color changes.  
•	Device shake recognition (Shake detector).

**Non-functional Requirements**
•	Performance: Data updates without noticeable lag.  
•	Usability: Intuitive menu with clear buttons.  
•	Reliability: Proper handling of the Activity lifecycle (registering/unregistering sensors).
________________________________________
## 🧠 General Architecture
•	**Selected Approach:**  
–	MVC (Model-View-Controller) — the classic approach for Android development in Java.

•	**Why this approach:**  
–	Simplicity of implementation for an educational project. Direct interaction between Activity and SensorManager clearly demonstrates the API's working principles.

•	**Main Components:**  
–	MainActivity: The navigation controller.  
–	SensorActivities: Specialized screens for each sensor type.  
–	SensorManager: System service for accessing sensors.
________________________________________
## 🧩 UML Diagram
**Structure Description:**
[MainActivity] –> [ListAllSensors]
[MainActivity] –> [GyroscopeSensor]
[MainActivity] –> [MagnetField]
[MainActivity] –> [OrientationSensor]
[MainActivity] –> [StepByStep]
[MainActivity] –> [LightSensor]
[MainActivity] –> [ShakeAndStart]

All sensor classes implement the `SensorEventListener` interface.
________________________________________
## 📂 Package Structure
The project uses a flat package structure: `zeev.fraiman.sensorscollection`.
- **Why:** For a small project, this provides quick access to all components.
- **Scalability:** For future expansion, it is recommended to split into `ui`, `sensors`, and `data` packages.
________________________________________
## 🧩 Detailed Class Description
📌 **Class: MainActivity**  
**Role:** Entry point and main menu.  
**Responsibility:** Dynamic UI creation (buttons) and launching corresponding Activities.  
**Main Methods:**  
- `onCreate()` — screen initialization.  
- `initComponents()` — programmatic creation of buttons based on a class array and names.  
**Interaction:** Launches all other Activities via Intent.

📌 **Class: LightSensor**  
**Role:** Handling ambient light sensor data.  
**Usage Reason:** To demonstrate UI changes based on the external environment.  
**Main Methods:**  
- `onSensorChanged()` — logic for changing the background color (from black to white).

📌 **Class: ShakeAndStart**  
**Role:** Shake detector.  
**Responsibility:** Tracking sudden accelerations along the X, Y, and Z axes.
________________________________________
## 🔄 App Workflow Diagram
1. User launches the app.
2. `MainActivity` generates the button list.
3. User selects a sensor (e.g., "LightSensor").
4. A new Activity opens, and the `SensorEventListener` is registered.
5. When physical parameters change, the sensor sends an event, and the UI updates.
6. When the screen is closed, the listener is disconnected (`onPause`).
________________________________________
## 🎨 UI/UX Analysis
•	The interface is built on dynamic element creation in `MainActivity`, making it flexible.  
•	Principles used:
–	**Simplicity:** Minimalist design, focusing on data.
–	**Logic:** One button — one function.
–	**Accessibility:** Large text and contrast colors (especially in LightSensor).
________________________________________
## ⚙️ Threading
•	Used: System callbacks `onSensorChanged`.  
•	**Why this method:** This is the standard mechanism in the Android SDK for sensors.  
•	**ANR Prevention:** Lightweight data processing within callback methods.  
•	**Memory Leaks:** Using `unregisterListener` in `onPause` ensures resource release.
________________________________________
## 💾 Data Management
•	Data is not stored in a database.  
•	**Why:** The app is intended for real-time monitoring.  
•	**Correctness:** Ensured by direct streaming of values from hardware sensors.
________________________________________
## 🌐 Networking
•	There is no networking functionality in the current version.
________________________________________
## 🔐 Security
•	No sensitive data (personal info) is collected.  
•	Only standard sensor access permissions are used.
________________________________________
## 🧪 Testing
•	Basic Unit tests and Instrumentation tests (ExampleUnitTest, ExampleInstrumentedTest) are provided.  
•	Checked: Context functionality and basic calculations.
________________________________________
## 🐞 Error Handling
•	Checks for sensor availability on the device (if a sensor is missing, the app doesn't crash but can inform the user).
________________________________________
## ⚡ Performance
•	Optimization: Use of `SENSOR_DELAY_NORMAL` to reduce battery consumption.  
•	Bottlenecks: Frequent UI updates at very high sensor sampling rates (not critical in this version).
________________________________________
## 🚀 Expansion Possibilities
•	Adding log recording to CSV/JSON.  
•	Graphic data visualization (oscillograms).  
•	Sending data to a remote server via MQTT or HTTP.
