# Body Fitness Goal Setting App

A modern Android application for setting and tracking fitness goals, built with Kotlin and following Material Design guidelines.

## Features

- **User Authentication**
  - Login screen with email/password validation
  - Session management

- **Home Screen**
  - Motivational quotes
  - Quick action cards for tracking workouts
  - Progress overview
  - Dynamic header image

- **Goals Management**
  - Create and track fitness goals
  - Progress tracking with visual indicators
  - Deadline management
  - Goal categories and status updates

- **Profile Section**
  - User information display
  - BMI calculation
  - Height and weight tracking
  - Profile image support
  - Account settings

## Technical Details

### Architecture & Components

- **Language:** Kotlin
- **Minimum SDK:** 21 (Android 5.0)
- **Target SDK:** 33 (Android 13)

### Libraries & Dependencies

- AndroidX Components
- Material Design Components
- Navigation Component
- ViewBinding
- Glide for image loading
- RecyclerView with ListAdapter

### Project Structure

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/bodyfitness/
│   │   │   ├── ui/
│   │   │   │   ├── login/
│   │   │   │   ├── main/
│   │   │   │   ├── home/
│   │   │   │   ├── goals/
│   │   │   │   └── profile/
│   │   │   └── ...
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── values/
│   │   │   ├── drawable/
│   │   │   ├── navigation/
│   │   │   └── menu/
│   │   └── AndroidManifest.xml
│   └── ...
└── build.gradle
```

## Setup Instructions

1. Clone the repository
2. Open the project in Android Studio
3. Sync project with Gradle files
4. Run the app on an emulator or physical device

### Requirements

- Android Studio Arctic Fox or newer
- JDK 8 or newer
- Android SDK with minimum API 21

## Usage

1. Launch the app
2. Login with email and password (minimum 6 characters)
3. Navigate using the bottom navigation bar:
   - Home: View motivation and quick actions
   - Goals: Manage your fitness goals
   - Profile: View and edit your profile

## Development Notes

- Uses ViewBinding for safe view access
- Implements Navigation Component for fragment management
- Material Design components for consistent UI
- Responsive layouts using ConstraintLayout
- Sample data included for demonstration

## Future Enhancements

- Backend integration for user data persistence
- Social features for sharing goals
- Workout tracking functionality
- Progress charts and analytics
- Push notifications for goal reminders
- Google Fit integration

## Contributing

Feel free to submit issues and enhancement requests.

## License

This project is licensed under the MIT License - see the LICENSE file for details.