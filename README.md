# TodoLite

TodoLite is a simple Android todo application built with Kotlin, Jetpack Compose, Room, Flow and MVVM.

## Features

- Add new tasks
- Edit existing tasks
- Delete tasks
- Undo task deletion
- Mark tasks as completed
- Filter tasks by status
- Search tasks by title
- Clear completed tasks
- Undo clearing completed tasks
- Save tasks locally with Room

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- Room
- Flow
- ViewModel
- MVVM architecture

## Architecture

The project uses a simple layered structure:

- `presentation` — screens, components, ViewModel
- `domain` — models and repository interface
- `data` — Room database, DAO, entities, repository implementation

## Screenshots

### Main screen
![Main screen](./screenshots/main_screen.jpeg)

### Search
![Search](./screenshots/search.jpeg)

### Edit task
![Edit task](./screenshots/edit_task.jpeg)

### Undo delete
![Undo delete](./screenshots/undo_delete.jpeg)

## How to Run

1. Clone the repository:

```bash
git clone https://github.com/maksympash-code/TodoLite.git
```

2. Open the project in **Android Studio**.

3. Wait until Gradle Sync finishes.

4. Run the app on an Android emulator or a physical Android device.

5. If needed, build the project manually:

```bash
./gradlew assembleDebug
```

## Project Status

MVP completed.

The app supports adding, editing, deleting, searching, filtering and completing tasks.
All tasks are saved locally using Room database.

## Author

Created by Maksym Pashchenko.