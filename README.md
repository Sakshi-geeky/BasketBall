# Basketball Schedule App

A modern Android application that displays basketball game schedules with real-time updates, team information, and ticket purchasing capabilities.

## Features

- **Game Schedule Display**
  - Monthly schedule view with easy navigation
  - Real-time game status updates (Live, Final, Upcoming)
  - Arena and location information
  - Team logos and scores
  - Ticket purchase integration

- **Search Functionality**
  - Search by team name
  - Search by arena
  - Search by city

- **UI/UX**
  - Modern Material Design 3 implementation
  - Dark theme with gold accents
  - Smooth animations and transitions
  - Responsive layout

## Tech Stack

- **Architecture Pattern**: MVVM (Model-View-ViewModel)
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Concurrency**: Kotlin Coroutines
- **State Management**: StateFlow
- **Image Loading**: Coil
- **JSON Parsing**: Gson
- **Build System**: Gradle with Kotlin DSL

## Project Structure

```
com.example.basketball/
├── data/
│   └── model/
│       ├── Schedule.kt
│       ├── ScheduleResponse.kt
│       ├── Team.kt
│       ├── TeamInfo.kt
│       └── TeamsResponse.kt
├── di/
│   └── AppModule.kt
├── repository/
│   └── ScheduleRepository.kt
├── ui/
│   ├── schedule/
│   │   ├── components/
│   │   │   └── TeamDisplay.kt
│   │   ├── ScheduleScreen.kt
│   │   └── ScheduleViewModel.kt
│   └── theme/
│       └── Theme.kt
└── BasketBallApp.kt
```

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio Arctic Fox or later
3. Sync project with Gradle files
4. Run on an emulator or physical device with Android API level 24 or higher

## Dependencies

```kotlin
dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.activity:activity-compose:1.9.2")
    
    // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.01.00"))
    
    // Hilt
    implementation("com.google.dagger:hilt-android:2.49")
    
    // Additional dependencies in build.gradle.kts
}
```

## Data Sources

The app currently reads game schedules and team information from local JSON assets:
- `schedule.json`: Contains game schedule information
- `teams.json`: Contains team information and logos

## State Management

The app uses a sealed class `ScheduleUiState` to handle different UI states:
```kotlin
sealed class ScheduleUiState {
    object Loading : ScheduleUiState()
    data class Success(
        val schedules: Map<String, List<Schedule>>,
        val teams: Map<String, TeamInfo>
    ) : ScheduleUiState()
    data class Error(val message: String) : ScheduleUiState()
}
```

## Future Improvements

- Implement real-time data fetching from an API
- Add offline support with Room database
- Implement unit tests and UI tests
- Add user preferences for favorite teams
- Support for multiple seasons
- Add game statistics and player information
- Implement light theme support

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
************************************************
![WhatsApp Image 2025-01-10 at 13 32 44(2)](https://github.com/user-attachments/assets/550da26e-cafd-434b-8de3-d833618c8dbc)

![WhatsApp Image 2025-01-10 at 13 32 44(1)](https://github.com/user-attachments/assets/5db2469b-61c9-4854-acc2-96e05f295515)
![WhatsApp Image 2025-01-10 at 13 32 44](https://github.com/user-attachments/assets/b4b92ed9-2701-42e4-b85f-7788e121f7d1)

![WhatsApp Image 2025-01-10 at 13 32 43(1)](https://github.com/user-attachments/assets/f4b98565-50b5-4566-a1b9-9c488e6845ee)



