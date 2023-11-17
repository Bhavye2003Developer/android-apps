# GPTOpenAI

GPTOpenAI is an AI application designed to interact with the OpenAI API, providing users with answers to their queries. The app includes features like sending requests to the OpenAI API, displaying user questions and answers in a RecyclerView, and utilizing coroutines with Retrofit to avoid blocking the UI. The app follows the MVVM architecture with components like `ChatRecyclerView`, `GptViewModel`, `network`, `ChatGptFragment`, and `MainActivity`.

## Features

- Send user queries to the OpenAI API
- Display user questions and answers in a RecyclerView
- Use coroutines to handle API requests without blocking the UI
- MVVM architecture for a modular and maintainable structure

## Installation

1. **Clone the repository to your local machine:**

    ```bash
    git clone https://github.com/your-username/android-apps.git
    ```

2. **Change directory to GPTOpenAI:**

    ```bash
    cd android-apps/GPTOpenAI
    ```

3. **Open the project in Android Studio.**

4. **Build and run the app on your Android device or emulator.**

## Architecture and MVVM

### MVVM Overview

GPTOpenAI adopts the **MVVM (Model-View-ViewModel)** architecture:

- **Model:** Represents data and business logic, including API requests and responses. The `network` directory contains classes related to API communication.

- **View:** Displays data and handles user input. `ChatGptFragment` is the main fragment where user interactions and responses are displayed.

- **ViewModel:** Acts as an intermediary between the model and the view, exposing data and managing user interactions. `GptViewModel` handles the observation of the list of request-response objects.

### ViewModel Usage

The `GptViewModel` is crucial in GPTOpenAI for managing data and state. It facilitates the following functionalities:

1. **Observing List of Chat Data:**

   The `ChatGptFragment` observes the list of `Chat` data objects in the `GptViewModel`. This ensures that any updates to the user's chat interactions are immediately reflected in the UI.

   By using ViewModel to handle API requests and responses, GPTOpenAI achieves a responsive and scalable design.

### RecyclerView

The `ChatRecyclerView` is responsible for populating the RecyclerView with chat items. It adapts to changes in the list of `Chat` data objects observed by the `GptViewModel`.

## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.
