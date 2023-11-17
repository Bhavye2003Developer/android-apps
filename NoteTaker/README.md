# Notify

Notify is an Android note-taking app that leverages Room database concepts to provide a seamless and persistent note management experience. The app includes the following components: `MainActivity`, `NoteItemAdapter`, `NotesViewModel`, `Note`, `NoteDao`, and `NoteDatabase`.

## Features

- Create, edit, and delete notes
- Mark notes as completed
- Room database for data persistence
- RecyclerView to display a list of notes

## Installation

1. **Clone the repository to your local machine:**

    ```bash
    git clone https://github.com/your-username/android-apps.git
    ```

2. **Change directory to Notify:**

    ```bash
    cd android-apps/NoteTaker
    ```

3. **Open the project in Android Studio.**

4. **Build and run the app on your Android device or emulator.**

## Screenshots
![f1](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/621b0398-80a5-4fed-9d80-62288c0c4247)
![f2](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/52bb8c9d-47b3-4bfe-ad6d-d43c22efe30e)
![f3](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/deca9c3b-0121-4ace-9332-44d03ef7be29)
![f4](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/6be21464-7b05-4bd4-99ed-47c72821343a)
![f5](https://github.com/Bhavye2003Developer/android-apps/assets/110657263/7c96f798-66cf-46ed-93bd-43c2dd6e7468)

## Architecture and Room Database

### Room Database Overview

Notify implements the **Room Database** for managing note data:

- **Entity (Note):** Represents a single note as a data class. Each note has properties like title, content, and completion status.

- **DAO (NoteDao):** Data Access Object for performing CRUD (Create, Read, Update, Delete) operations on the database.

- **Database (NoteDatabase):** Manages and holds the database instance.

### ViewModel Usage

The `NotesViewModel` is responsible for managing note-related data and states. It facilitates the following functionalities:

1. **Create, Edit, Delete Notes:**

   The `MainActivity` interacts with the `NotesViewModel` to perform operations like creating, editing, and deleting notes.

2. **Mark Notes as Completed:**

   The `NoteItemAdapter` handles the RecyclerView items and includes tick and cross marks. Clicking the tick mark marks the note as completed.

   The `NotesViewModel` updates the note completion status in the Room database.

### RecyclerView

The `NoteItemAdapter` is responsible for populating the RecyclerView with note items. It utilizes the `NotesViewModel` to handle user interactions and updates.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or create a pull request.
