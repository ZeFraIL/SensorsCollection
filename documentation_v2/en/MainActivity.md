# Detailed Class Description: MainActivity

## 1. General Information
*   **Class Name:** `MainActivity`
*   **Type:** `Activity`
*   **Purpose:** This class serves as the main entry point and the home screen of the application. It is responsible for displaying a menu of buttons that allow the user to navigate to different sensor demonstration screens.
*   **Interaction:** It interacts with all other sensor Activities (`ListAllSensors`, `GyroscopeSensor`, `MagnetField`, `OrientationSensor`, `StepByStep`, `LightSensor`, `ShakeAndStart`) by launching them using `Intent` objects when a button is clicked.

## 2. Variables (Class Fields)

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `context` | `Context` | Stores the current state/environment of the application. | `initComponents()` for creating buttons and `Intent`s. |
| `names` | `String[]` | An array of text labels for the menu buttons. | `initComponents()` to set button text. |
| `n` | `int` | Stores the number of buttons to be created. | `initComponents()` as a loop limit. |
| `buttons` | `Button[]` | An array to hold the dynamically created `Button` objects. | `initComponents()` for storage and UI setup. |
| `LLallButtons`| `LinearLayout` | The layout container where buttons are added. | `initComponents()` to add views. |
| `classes` | `Class[]` | An array of class names (Activities) corresponding to the buttons. | `initComponents()` to define `Intent` destinations. |

## 3. Class Methods

### Method Name: `onCreate`
*   **Method Type:** `protected`
*   **Return Value:** `void` (returns nothing)
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `savedInstanceState` | `Bundle` | Stores the activity state if it was previously shut down. |
*   **What does it do:**
    1. Calls the parent class's `onCreate` method.
    2. Sets the visual layout from `activity_main.xml`.
    3. Calls `initComponents()` to build the menu.
*   **When called:** Automatically by the Android system when the app starts.
*   **Important:** This is the first step in the Activity's life.

### Method Name: `initComponents`
*   **Method Type:** `private`
*   **Return Value:** `void`
*   **Parameters:** None
*   **What does it do:**
    1. Initializes `context` with the current activity instance.
    2. Loops through the `names` array.
    3. For each name, it creates a new `Button` programmatically.
    4. Configures button properties (margins, padding, text size).
    5. Creates an `Intent` pointing to the corresponding class in the `classes` array.
    6. Attaches a `ClickListener` to the button to start the selected Activity.
    7. Adds the button to the `LLallButtons` container.
*   **When called:** Manually from `onCreate()`.
*   **Important:** This approach makes the menu very easy to expand; just add a name and a class to the arrays.

## 4. Lifecycle
*   **`onCreate()`**: Called when the Activity is first created. It sets up the layout and initializes the buttons.

## 5. Interface Interaction (UI)
*   **Elements:** `LinearLayout` (with ID `LLallButtons`) and dynamically created `Button`s.
*   **Code Relation:** `findViewById` is used to get the layout. Buttons are created using `new Button(context)`.
*   **Events:** `setOnClickListener` is handled for every button to navigate to other screens.

## 6. Interaction with Other Components
*   **Intents:** Uses `Intent` to transition from `MainActivity` to specific sensor activities.
*   **Data Transfer:** No complex data is transferred, just the command to start a new screen.

## 7. General Logic
1. The app starts and displays the main layout.
2. The code reads arrays of names and class types.
3. It "draws" buttons on the screen for each sensor.
4. When you tap a button, the app opens the specific sensor screen.

## 8. Simplified Explanation
**"Explanation in simple words"**
Think of `MainActivity` as the **Reception Desk** or the **Main Menu** of a game. 
Imagine you are in a building with many rooms (Sensors). `MainActivity` is the hallway with doors. Each door has a label (Button). When you click a label, you are essentially walking through that door into a room where a specific sensor's data is shown.

---
**Note for students:** In `initComponents`, the buttons are created "dynamically" (via code) rather than "statically" (in XML). This is a professional way to handle lists that might change size, although for a fixed menu, it could also be done in XML for better performance.
