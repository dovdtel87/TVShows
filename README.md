# TVShows
## Project specifications
- **Language**: Kotlin
- **IDE**: Android Studio
- **Design Pattern**: MVVM (Model - View - ViewModel)
- **DI**: Dagger Hilt
- **Network**: Retrofit + okhttp 
- **Concurrency**: Coroutines + StateFlow
- **UI**: Jetpack Compose
- **Database**: Room

## Use Cases
I have developed an Android app using Jetpack Compose to retrieve information about shows from the given remote API, persisting them locally to allow the app to continue working offline.

The user can interact with the list of shows displayed shorting them when pressing the floating action button. Once the list is shorted the button is hidden.
This app contains the following use cases:
- If there is no connection (or other error) and no shows are stored in the database, an error is displayed on the screen.
- If there is no connection (or other error) and shows are stored in the database, then that list is presented.
- If there is a connection and shows are stored in the database, then that list is presented.
- If there is a connection and no shows are stored in the database the list is retrieved from the API.
- If shows are not shorted a floating action button is displayed.
- If shows are shorted the floating action button is not displayed.
- If the floating action button is pressed then the list is shorted.

## Architecture
The project has been developed following the concerns of clean architecture with an MVVM architecture and a repository pattern implementation.
To communicate the view with the view model, "StateFlow" has been used. The state of the app is a `SealedClass` that can have three states: `Loading`, `Content`, `Error`.
- `Loading`: Shows a `CircularProgressIndicator`.
- `Error`: Shows an icon, a message, and a retry button.
- `Content`: Displays the list of shows and if the list is shorted or not.

The project has a repositories for handling the retrieval of the shows data. The project implements the repository pattern.

Between the ViewModel and Repository, another "UseCase" layer has been created to handle the different use cases that the app can handle. In this case a simple use case is provided `FetchShowsUseCase`

Three different types of models have been defined:
- `Dtos`: e.g., `ShowDto`, to handle data received from the API.
- Domain model: e.g., `Show`, to handle the domain data.
- UI model: e.g., `ShowUI`, to handle the visual representation of the data.

Structure of the project:
- ![Screenshot 2023-07-07 at 23 16 00](https://github.com/dovdtel87/TVShows/assets/2633423/25d6b548-bf6d-47a3-be35-7feb72502107)

## DI
I have chosen Dagger as the framework for Dependency Injection instead of a Service Locator because it enables better scalability of the app and provides compile-time warnings if a dependency is not correctly initialized.
 
## Modularization
Given the simplicity of this app a mono-module approach has been selected. Although for a bigger production apps a multi module approach should be selected.
One possible solution could be having modules for `:coreNetwork` `:showsData` and `:showsFeature` 

## Authentication
It has been used the key provided for using the API. Reading the documentation this key is thought to be integrated in the query but the API provides better way to handle authentication with OAuth2 and token. In a production app I would handle the authentication this way adding the token in the header of the request.

## Developer Good Practices
- Single Responsibility Principle: Each class and function have a single responsibility, making the code easier to understand, test, and maintain. For example:
  - The `ListShowsViewModel` is responsible for managing the app's state and interacting with the repository.
  - The `ShowsRepository` is responsible for fetching shows data from the API and performing database operations.
- Separation of concerns: The code is organized into separate layers, each in their own package, to ensure clear separation of concerns and maintainability.
- KISS Principle: The code in the app follows the KISS principle by prioritizing simplicity and readability. Complex logic is avoided, and code is kept concise and easy to understand.
- DRY (Don't Repeat Yourself) Principle: The DRY principle is applied throughout the codebase by promoting code reusability and avoiding duplication. For example, the UI components of Loading and Error can be reusable.
- Other things taken into account:
  - Dependency Injection: Dagger Hilt is used for dependency injection, making it easier to manage and scale the project.
  - Asynchronous Programming: Coroutines are used for handling asynchronous operations, such as fetching data from the API and performing database operations.
  - Error Handling: Errors during API calls are handled by displaying an error message in the UI if there are no available products in the database; otherwise, a cached list of products is shown.
## Tests
Unit tests have been provided for all the layers.
Also, a simple UI test to ensure the list is displayed on the screen has been provided.

## Scalability
- The current implementation allows for easy expansion of the API, repository, and view model to support other show-related functionalities.
- To create a more complex app, navigation should be implemented, although for this project, the skeleton of navigation has been provided.

## Future Features (pagination)
It is not implemented, but a nice following feature that could be implemented with more time is pagination.
The api provides a pagination structure, it could be implemented a feature to load more shows implementing a InfiniteListHandler. When the user is about to reach the end of the list, a new call to fetchMoreUsers with the updated number of the page should be call. The returned list will be added to the existing one.
