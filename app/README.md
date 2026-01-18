# 📸 CameraX & Video/Photo Capture Guide

Welcome to the **Pics** app! This guide explains how we use Google's **CameraX** library to build a modern, lifecycle-aware camera experience with Jetpack Compose.

---

## 1. 🌟 CameraX Overview

### What is CameraX?
CameraX is an Android Jetpack library designed to make camera development easier. It handles the complex "behind-the-scenes" work of camera management so you can focus on building your app's unique features.

### Core Components
CameraX is built on three main pillars:
1.  **Preview**: Shows the live camera feed on the screen.
2.  **ImageCapture**: Takes high-quality photos.
3.  **VideoCapture**: Records videos with audio.

### ⚖️ CameraX vs. Traditional Camera API
| Feature | Traditional (Camera2) | Modern (CameraX) |
| :--- | :--- | :--- |
| **Complexity** | High (Manages sessions, surfaces, threads) | Low (Use-case based) |
| **Lifecycle** | Manual management (Easy to leak memory) | **Lifecycle-aware** (Automatic) |
| **Consistency** | Varies by device manufacturer | Consistent across 98% of Android devices |
| **Boilerplate** | Hundreds of lines | Minimal code |

### 🏗️ Architecture Flow
Imagine the camera as a **Projector**:
- **The Lens**: Your physical camera hardware.
- **The Projector (CameraX)**: Manages the light and focus.
- **The Screens (UseCases)**:
    - **Preview Screen**: Where you see the live view.
    - **Photo Screen**: Where the final image is captured.
    - **Video Screen**: Where the recording is saved.

---

## 2. 🎬 Video & Photo Capture Flow

### Step-by-Step Breakdown
1.  **Initialize**: We create a `LifecycleCameraController`. Think of this as the "Remote Control" for the camera.
2.  **Bind**: We link the controller to the app's lifecycle (so the camera turns off when you minimize the app).
3.  **Configure**: We tell the controller which "Screens" (UseCases) we want: `IMAGE_CAPTURE` and `VIDEO_CAPTURE`.
4.  **Display**: We use `PreviewView` inside a `CameraPreview` composable to show the lens's view to the user.

### 🔄 Lifecycle Awareness
CameraX is "Lifecycle-Aware." This means:
- When the user switches apps, CameraX **automatically pauses** the camera.
- When the user returns, CameraX **automatically resumes** the camera.
- No more manual `onPause()` or `onResume()` boilerplate!

### 🛠️ Error Handling
We handle common scenarios in [CameraActions.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/camera/CameraActions.kt):
- **Storage Full**: Alerts the user if there's no space.
- **Permission Denied**: Checks for Camera/Mic permissions before starting.
- **Encoding Failures**: Provides specific feedback if a recording fails.

---

## 3. 📂 Project Structure

The app's logic is organized into clean, focused packages:

| Package | Purpose | Role |
| :--- | :--- | :--- |
| `camera` | CameraX Logic | Contains [CameraPreview.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/camera/CameraPreview.kt) (UI component) and [CameraActions.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/camera/CameraActions.kt) (Logic). |
| `ui` | User Interface | [CameraScreen.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/ui/CameraScreen.kt) defines the layout, buttons, and indicators. |
| `viewmodel` | State Management | [MainViewModel.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/viewmodel/MainViewModel.kt) holds the list of captured photos and recording status. |
| `utils` | Helpers | [PermissionUtils.kt](file:///d:/contri/Pics---A-Camera-App-main/app/src/main/java/com/example/pics/utils/PermissionUtils.kt) handles Android permission requests. |

---

## 4. 📝 Key Implementation Details

### Taking a Photo
```kotlin
// Inside CameraActions.kt
fun takePhoto(context: Context, controller: LifecycleCameraController, onPhotoTaken: (Bitmap) -> Unit) {
    controller.takePicture(
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageCapturedCallback() {
            override fun onCaptureSuccess(image: ImageProxy) {
                onPhotoTaken(image.toBitmap()) // 🖼️ Send photo to ViewModel
                image.close() // 🧹 Clean up memory
            }
        }
    )
}
```

### Recording Video
```kotlin
// Inside CameraActions.kt
recording = controller.startRecording(
    FileOutputOptions.Builder(outputFile).build(),
    AudioConfig.create(false), // 🔇 Audio disabled for emulator stability
    ContextCompat.getMainExecutor(context)
) { event ->
    if (event is VideoRecordEvent.Finalize) {
        // 🏁 Handle the finished recording
    }
}
```

---

## 5. 🆘 Troubleshooting & Common Issues

- **Black Preview**: Ensure you have granted Camera permissions. Check if another app is using the camera.
- **Recording Fails (Emulator)**: Recording with audio often fails on Android Emulators. We disable audio by default in `AudioConfig.create(false)` for better stability during development.
- **Memory Issues**: Always ensure `image.close()` is called in the photo capture callback.

---

## 📚 References
- [Official CameraX Documentation](https://developer.android.com/training/camerax)
- [Jetpack Compose & CameraX Guide](https://developer.android.com/codelabs/camerax-getting-started)
