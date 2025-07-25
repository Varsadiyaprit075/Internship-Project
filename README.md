# Android Multi-Function App

This Android app demonstrates several core features and UI components in a single screen, including camera access, theme switching, SwipeRefreshLayout, navigation to other activities, animated buttons, Snackbar messages, and a custom popup menu (drawer).

## 🔧 Features Implemented

### 🌗 Theme Switch (Light/Dark Mode)
- A toggle (`Switch`) allows the user to switch between light and dark themes.
- User preference is saved using `SharedPreferences`.

### 🔄 Pull to Refresh
- Implemented using `SwipeRefreshLayout`.
- On refresh, the current image in the `ImageView` is cleared and a Toast is shown.

### 📸 Camera Access
- Opens the camera using `MediaStore.ACTION_IMAGE_CAPTURE`.
- Captured image is displayed in an `ImageView`.
- Uses `ActivityResultLauncher` for permission handling.

### 🔁 Activity Navigation
- Buttons to navigate to other activities: `SecondActivity`, `ThirdActivity`, `FourthActivity`, and `FifthActivity`.

### 🔄 Button Animations
- Buttons like toast and camera trigger animations on click.
- `Snackbar` shown with a message when toast button is clicked.

### 🧾 Popup Drawer (Menu)
- A custom drawer menu is shown using `PopupWindow` on clicking the menu `ImageButton`.

## 🖼️ UI Components Used
- `Switch`
- `SwipeRefreshLayout`
- `ProgressDialog`
- `ImageView`
- `ImageButton`
- `PopupWindow`
- `Toast` & `Snackbar`
- Multiple `Button`s

## 📂 File Structure

