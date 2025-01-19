# **Android Starter Project**

This repository serves as a starter template for Android projects with pre-configured tools and best
practices. It provides a solid foundation for building scalable and maintainable Android
applications.

---

## **1. Dependencies**

This project includes the following essential dependencies:

- **Dependency Injection**: [Hilt](https://dagger.dev/hilt/) - Simplified dependency injection.
- **Networking**: [Ktor](https://ktor.io/) - HTTP client for Android with serialization and logging
  support.
- **Coroutines**: [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) -
  Simplified asynchronous programming.
- **Database**: [Room](https://developer.android.com/training/data-storage/room) - SQLite database
  with type-safe access.
- **Paging
  **: [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) -
  Efficient data pagination for large data sets.

### Dependency Highlights:

```kotlin
dependencies {
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Ktor
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.json)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Room
    implementation(libs.androidx.room.runtime)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

    // Paging
    implementation(libs.androidx.paging.runtime.ktx)
}
```

---

## **2. Static Analysis**

The project includes the following tools for static analysis and build validation:

- **[Detekt](https://detekt.dev/)**: Kotlin static code analysis for code smells.
- **[Spotless](https://github.com/diffplug/spotless)**: Ensures consistent code formatting with
  Ktlint.
- **[Dependency Guard](https://github.com/dropbox/dependency-guard)**: Tracks changes in project
  dependencies.
- **Unit Testing**: JUnit and Roborazzi for screenshot testing.
- **Lint**: Analyzes and suggests fixes for Android-specific issues.
- **Build**: Ensures the application compiles correctly.

### Tasks Overview:

```bash
# Detekt
./gradlew detekt

# Spotless
./gradlew spotlessApply

# Dependency Guard
./gradlew dependencyGuard
./gradlew dependencyGuardBaseline

# Unit Tests
./gradlew testDebugUnitTest

# Lint
./gradlew lint

# Build APK
./gradlew assembleDebug
```

---

## **3. Architecture**

The project follows a layered **MVVM architecture** with clear separation of concerns:

- **UI Layer**:
    - Contains `Activity` and `Compose` components for user interface.
    - Uses `ViewModel` to handle UI state and logic.

- **Domain Layer**:
    - Business logic and use case handling.
    - Independent of UI and data layers.

- **Data Layer**:
    - Responsible for managing data from local (Room) and remote (Ktor) sources.
    - Implements repositories for data abstraction.

### **Directory Structure**

```
app/
├── data/
│   ├── repository/
│   ├── local/
│   └── remote/
├── domain/
│   ├── model/
│   └── usecase/
├── ui/
│   ├── components/
│   ├── screens/
│   └── viewmodel/
```

---

## **4. Getting Started**

### Prerequisites:

- **Android Studio**: Latest stable version.
- **JDK 11**: Compatible with the project's Gradle configuration.

### Steps:

1. Clone this repository:
   ```bash
   git clone https://github.com/your-repo/android-todo-starter.git
   ```
2. Open the project in **Android Studio**.
3. Sync Gradle and resolve dependencies.
4. Run the following command to verify the setup:
   ```bash
   ./gradlew build
   ```
5. Start developing!

---

## **5. License**

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

