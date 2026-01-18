# 📸 Pics – A Modern, Beginner-Friendly Camera App

**Pics** is a clean, educational Android camera application designed to showcase modern Android development practices. Built with **Kotlin**, **Jetpack Compose**, and **CameraX**, it serves as a practical guide for developers to understand camera integration, lifecycle management, and reactive UI patterns.

The goal of this project is to help **beginners understand how a camera works in Android**, how CameraX integrates with Compose, and how to contribute to a real open‑source project in a friendly environment.

---

## ✨ Features

- 📹 **Video Recording**: High-quality video capture with real-time **Pause/Resume** functionality.
- 📸 **Photo Capture**: Instant photo taking with high-resolution output.
- 🔄 **Camera Switching**: Seamlessly toggle between front and back cameras.
- 🖼️ **In-App Gallery**: Quick preview of captured photos and videos via a sleek Bottom Sheet.
- 🎨 **Material 3 UI**: Modern, responsive design built entirely with Jetpack Compose.
- 🛡️ **Smart Permissions**: Robust handling of Camera and Microphone permissions.
- 🧩 **Lifecycle Aware**: Automatically manages camera resources to prevent battery drain and crashes.

---

## 🛠️ Tech Stack & Architecture

This app is built using the latest Android technologies to ensure performance, readability, and maintainability.

### **Technologies**
- **Language**: [Kotlin](https://kotlinlang.org/) (100%) - Leveraging modern features like Coroutines and Flow.
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative UI for a responsive and modern experience.
- **Camera Engine**: [CameraX](https://developer.android.com/training/camerax) - Google's modern camera library for consistent behavior across devices.
- **Architecture**: **MVVM (Model-View-ViewModel)** - Ensures clean separation of concerns.
- **State Management**: [Kotlin StateFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) - Reactive state updates for the UI.
- **Dependency Management**: [Gradle Version Catalog](https://docs.gradle.org/current/userguide/platforms.html) - Centralized and type-safe dependency management.
- **Design System**: [Material 3](https://m3.material.io/) - The latest iteration of Material Design.

---

## 🏗️ How It Works: A Technical Deep Dive

### **1. The Camera Controller**
The heart of the app is the `LifecycleCameraController`. Unlike the older Camera2 API, this controller is **Lifecycle-Aware**.
- It is initialized in [MainActivity.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/MainActivity.kt).
- It automatically handles camera startup and shutdown when the app moves between foreground and background.

### **2. CameraX Use Cases**
CameraX works by binding "Use Cases" to the camera lifecycle:
- **Preview**: Managed by `PreviewView` in [CameraPreview.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/camera/CameraPreview.kt). It provides a smooth live feed.
- **ImageCapture**: Configured to take high-quality stills. Logic resides in `CameraActions.takePhoto()`.
- **VideoCapture**: Uses the modern `Recorder` and `Recording` API for efficient video streaming.

### **3. Reactive Data Flow**

```text
[ User Interaction ] -> [ CameraActions ] -> [ CameraX Hardware ]
                                                     |
                                                     v
[ UI Recomposition ] <- [ MainViewModel ] <- [ Success Callback ]
```

- **Photo Flow**: User taps shutter -> [CameraActions.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/camera/CameraActions.kt) triggers `takePicture` -> `ImageProxy` received -> Converted to `Bitmap` -> Added to `StateFlow` in [MainViewModel.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/viewmodel/MainViewModel.kt).
- **Video Flow**: User taps record -> `controller.startRecording()` creates a `Recording` object -> [MainViewModel.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/viewmodel/MainViewModel.kt) tracks `isRecording` state -> File saved to `context.filesDir` upon finalization.

---

## 📂 Project Structure

```text
com.example.pics
│
├── 📷 camera/
│   ├── CameraActions.kt   # Core logic for capture and recording
│   └── CameraPreview.kt   # Bridge between CameraX and Jetpack Compose
│
├── 🎨 ui/
│   ├── theme/             # M3 Color schemes, Typography, and Shapes
│   ├── CameraScreen.kt    # The main UI layout and interaction logic
│   └── PhotoBottomSheet.kt # Interactive gallery preview component
│
├── 🧠 viewmodel/
│   └── MainViewModel.kt   # App state management and business logic
│
├── 🛠️ utils/
│   └── PermissionUtils.kt # Clean, modular permission handling
│
└── 🚀 MainActivity.kt     # App entry point and CameraX initialization
```

---

## 🛠️ Current Limitations & Roadmap

We are constantly improving! Here is what we are working on:

1.  **💾 Persistent Storage**: Currently, photos are kept in memory (lost on app close).
    - *Goal*: Save to MediaStore or internal storage.
2.  **📺 Full Media Viewer**: Tapping a photo currently only shows it in the bottom sheet.
    - *Goal*: Add a full-screen viewer with zoom and share options.
3.  **⚡ Flash & Zoom**: Basic controls are implemented, but UI toggles are coming soon.
4.  **🎭 Image Filters**: Integration with CameraX Extensions for real-time effects.

---

## 🚀 Getting Started

### **Prerequisites**
- **Android Studio**: Ladybug or newer.
- **JDK**: Java 17 (recommended).
- **Device**: A physical Android device with a camera (API 24+).

### **Setup Steps**
1.  **Clone the Repository**
    ```bash
    git clone https://github.com/Vaibhav-P1/Pics---A-Camera-App.git
    ```
2.  **Open & Sync**: Open the project in Android Studio and wait for Gradle to sync.
3.  **Run**: Click the **Run** button to install the app on your connected device.

---

## 🤝 Contributing & Learning

This project is **beginner-friendly** by design! We encourage you to:
- **Improve the UI**: Refine the Material 3 implementation.
- **Add Features**: Check our roadmap for "Good First Issues".
- **Refactor**: Suggest cleaner ways to handle state or logic.

Please read our [CONTRIBUTING.md](file:///d:/contri/Pics---A-Camera-App-main/CONTRIBUTING.md) for detailed guidelines.

### **Learning Goals for Newcomers**
- Master **CameraX** Use Cases and Lifecycle.
- Understand **Jetpack Compose** state and recomposition.
- Learn modern **MVVM** architecture with StateFlow.
- Practice the **Git/GitHub** open-source workflow.

---

## 🆘 Troubleshooting

- **Black Screen**: Ensure Camera permissions are granted. Check if another app is using the camera.
- **Recording Fails**: On emulators, video recording can be unstable. We recommend testing on a physical device.
- **Build Errors**: Ensure you are using **JDK 17** and the latest Android Studio.

---

## 📄 License

This project is open-source and available under the **MIT License**.

---

Happy coding! 🚀
