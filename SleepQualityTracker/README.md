# SleepQualityTracker

SleepQualityTracker is an Android app designed to track sleep quality. It features start and stop buttons to note the sleep duration, and users can rate their sleep quality using emoticons. The app uses the MVVM architecture, with a Room database to store sleep data, a ViewModel to manage the UI-related data, and a View to display the data.

## Features

- Start and stop buttons to track sleep duration
- Rate sleep quality using emoticons
- MVVM architecture for a modular and maintainable structure

## Installation

1. **Clone the repository to your local machine:**

    ```bash
    git clone https://github.com/Bhavye2003Developer/android-apps
    ```

2. **Change directory to TimerAI:**

    ```bash
    cd android-apps/SleepQualityTracker
    ```


3. **Open the project in Android Studio.**

4. **Build and run the app on your Android device or emulator.**

## Architecture and ViewModel

### MVVM Overview

SleepQualityTracker follows the **MVVM (Model-View-ViewModel)** architecture:

- **Model (Room Database):** Manages data persistence and retrieval. Sleep data is stored in the Room database.

- **View (Fragment and Activity):** Represents UI components responsible for displaying data and handling user input. The `HomeFragment` and `RatingFragment` are essential components.

- **ViewModel:** Acts as a mediator between the model and the view. The `SleepViewModel` manages UI-related data for the `HomeFragment` and `RatingFragment`.

## Screenshots
![f1](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/b54557d1-365d-4d30-94fa-0a460c7ba0b2)
![f2](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/1445ab08-459b-4d95-9e90-9b3d9535c802)
![f3](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/b843c77f-a878-420c-80d1-790d60a6584f)
![f4](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/d6c22037-ee50-4efb-a8b0-9b63743a20ca)
![f5](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/2eebba3f-86cb-42ea-ae97-d40cb3721bd3)


### ViewModel Usage

The `SleepViewModel` is responsible for managing sleep-related data and states. It facilitates the following functionalities:

1. **Observe Sleep Data in HomeFragment:**

   The `HomeFragment` observes sleep data from the Room database through the `SleepViewModel`. This ensures that any updates to the sleep data are immediately reflected in the UI.

2. **Navigate to RatingFragment:**

   When the user clicks the stop button, the app navigates to the `RatingFragment` using the `SleepViewModel`. Here, users can rate their sleep quality using emoticons.

   By utilizing ViewModel to handle data operations and UI-related logic, SleepQualityTracker achieves a clean and organized codebase.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or create a pull request.
