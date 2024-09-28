
# Objects App

This project is a Phone Display application developed for showcasing various phone objects. The app follows the MVVM architecture pattern and provides a responsive UI suitable for different screen sizes, including tablets and landscape orientations.

## Instructions to Run the App

1. **Clone the Repository**: 
   ```bash
   git clone https://github.com/esthcarelle/ObjectApp
   ```

2. **Open the Project**: 
   Open the project in Android Studio.

3. **Install Dependencies**: 
   Make sure to install all required dependencies. Open the `build.gradle` file and sync the project.

4. **Run the App**: 
   Connect an Android device or use an emulator. Click on the 'Run' button in Android Studio to launch the app.

## Libraries and Tools Used

- **Retrofit**: For making network calls and fetching phone objects from the API.
- **MVVM Architecture**: To separate concerns and manage UI-related data in a lifecycle-conscious way.
- **Coroutines**: For asynchronous programming and handling background tasks smoothly.
- **JUnit Jupiter**: For unit testing the ViewModel and other components.
- **Flow**: For handling data streams in a reactive way.
- **XML Layouts**: For designing the UI components.
- **Placeholders Drawables**: Used to enhance the visual appearance of the app.
- **Git Flow**

## Design Decisions and Assumptions

- The app is designed as a **Single Activity** application utilizing **Fragments** to manage different screens and components.
- Emphasis was placed on **UI responsiveness** to ensure a smooth user experience across various devices and orientations.
- The app supports both **landscape** and **tablet** modes, providing a consistent experience regardless of device size.
- The use of **placeholders** for images improves the UI, allowing for a more polished look while loading actual images from the network.
- Assumed that the users of the app would prefer a clean and minimalistic interface that focuses on showcasing the phone objects effectively.

## Screenshots

![Screenshot_20240928_142624](https://github.com/user-attachments/assets/b197ecb0-cabb-4bb8-b800-1e73cb1af4d2)
![Screenshot_20240928_142607](https://github.com/user-attachments/assets/ed50d8da-ac73-4f09-863f-ade2b2f22352)
![Screenshot_20240928_142601](https://github.com/user-attachments/assets/0c5f369a-3266-4c65-b3e7-494a72d0172f)
![Screenshot_20240928_142931](https://github.com/user-attachments/assets/93db57bf-0e51-43a5-ae69-074468db3db0)




## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue for any enhancements or bug fixes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
