# Timer AI

Timer AI is a Kotlin-based timer app designed to provide accessibility in setting custom stop times and playing an alarm when the timer stops. Users can also pause and resume the timer as needed.

## Features

- Set custom stop times
- Play default alarm tune on timer completion
- Pause and resume functionality

## Installation

1. **Clone the repository to your local machine:**

    ```bash
    git clone https://github.com/Bhavye2003Developer/android-apps
    ```

2. **change directory to TimerAI:**

    ```bash
    cd android-apps/TimerAI
    ```

3. **Open the project in Android Studio.**

4. **Build and run the app on your Android device or emulator.**

## Architecture and ViewModel

### MVVM Overview

Timer AI adopts the **MVVM (Model-View-ViewModel)** architecture:

- **Model:** Manages data and business logic, including timer functionality, alarm configurations, and timer states.

- **View:** Represents UI components responsible for displaying data and handling user input.

- **ViewModel:** Acts as a mediator between the model and the view, exposing data and managing user interactions. The `TimerViewModel` is crucial in Timer AI.

### ViewModel Usage

The `TimerViewModel` in Timer AI is responsible for managing timer-related data and states. Notably, it facilitates observing time changes in the fragment:

1. **Observing Time in Fragment:**

   The `TimerFragment` observes the `secondsRemaining` property in the `TimerViewModel`. This ensures that any updates to the timer state are immediately reflected in the UI.

   By using ViewModel to handle timer logic and state, Timer AI achieves a modular and maintainable structure.

## Screenshots
![f5](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/ac6e7530-1a72-437f-8fed-e95c6cf26fde)
![f4](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/7dbaafab-cd58-4648-b46a-3847ecb55869)
![f3](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/6f09fd9f-c59f-4187-aa8b-81cd3f5ce120)
![f2](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/e63fa193-03a3-4338-b709-7627608e9a2d)
![f1](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/b144623f-2ed9-41bf-8744-aba692a384b0)

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.
