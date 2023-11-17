# Quotify

Quotify is an Android app designed to display random quotes. It features a clean and simple interface, with the main functionality implemented using the MVVM architecture. The app includes the following components: `MainActivity`, `QuoteFragment`, `QuoteViewModel`, and `Quotes`.

## Features

- Display random quotes
- Navigate to the next and previous quotes
- Share the current quote
- MVVM architecture for a structured and modular codebase

## Installation

1. **Clone the repository to your local machine:**

    ```bash
    git clone https://github.com/your-username/android-apps.git
    ```

2. **Change directory to Quotify:**

    ```bash
    cd android-apps/Quotify
    ```

3. **Open the project in Android Studio.**

4. **Build and run the app on your Android device or emulator.**

## Architecture and ViewModel

### MVVM Overview

Quotify follows the **MVVM (Model-View-ViewModel)** architecture:

- **Model (Quotes):** Manages data and business logic, including the collection of quotes and the method to retrieve a new quote.

- **View (Fragment and Activity):** Represents UI components responsible for displaying data and handling user input. The `MainActivity` inflates the `QuoteFragment`, which displays the quotes.

- **ViewModel:** Acts as a mediator between the model and the view. The `QuoteViewModel` manages UI-related data for the `QuoteFragment` and provides the new quote using LiveData.

### ViewModel Usage

The `QuoteViewModel` is responsible for managing quote-related data and states. It facilitates the following functionalities:

1. **Observe New Quote in QuoteFragment:**

   The `QuoteFragment` observes the new quote from the `QuoteViewModel` using LiveData. This ensures that any updates to the displayed quote are immediately reflected in the UI.

2. **Next and Previous Buttons:**

   The `QuoteFragment` features buttons to navigate to the next and previous quotes. These buttons interact with the `QuoteViewModel` to update the displayed quote accordingly.

3. **Share Icon:**

   The `QuoteFragment` includes a share icon that allows users to share the current quote. The sharing functionality is implemented using Android's sharing capabilities.

   By adopting ViewModel to handle data operations and UI-related logic, Quotify achieves a well-organized and maintainable codebase.

## Screenshots
![f1](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/b32efe02-4b3e-4596-8186-79aa47debeef)
![f2](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/bd191daa-a431-42db-aae7-55221ea5fab5)
![f3](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/6dd9307f-adf9-4be3-8f10-bf9509ecada4)
![f4](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/e1767c96-c9c8-4d68-a755-f078a883b000)

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or create a pull request.
