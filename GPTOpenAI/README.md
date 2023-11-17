![WhatsApp Image 2023-11-17 at 10 21 04_41c73403](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/950e1931-35c0-4c9a-a447-a81c9c7cad28)# GPTOpenAI

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

## Screenshots
![f1](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/ece862b9-411a-4a99-90e8-98cf1713deed)
![WhatsApp Image 2023-11-17 at 10 21 04_41c73403](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/339265f5-fa02-4ab1-a693-b66cc637bbaf)
![f3](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/7b97b406-f105-4059-9824-037d01e642c4)
![f4](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/4ab3911a-30e3-4bee-baff-c3838ed9b8f3)


## Setting up OpenAI API Key

To use GPTOpenAI, you'll need to obtain an API key from OpenAI. Follow these steps:

1. **Sign up for an OpenAI account:**

   If you don't have an OpenAI account, you'll need to sign up on the [[OpenAI website](https://platform.openai.com/)].

2. **Get your API key:**

   Once you have an account, navigate to the API section and generate an API key.

3. **Configure GPTOpenAI with your API key:**

   Open the `ChatService.kt` file located at `GPTOpenAI/app/src/main/java/com/example/gptopenai/network/ChatService.kt` and replace the placeholder `"INSERT_YOUR_API_KEY"` with your actual OpenAI API key.

   ![f6](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/88fd4725-f60c-47a0-a762-df3c99f41ba4)



## Contributing

Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or create a pull request.
