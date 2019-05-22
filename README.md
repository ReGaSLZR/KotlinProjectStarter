
# Kotlin Project Starter

> A kit for developers who want to make an app quickly.

- One `ApiSource` for all endpoints related by the same base URL.
- One `ApiClient`/Repository to establish the `ApiSource`.
- One `UseCase` to represent one endpoint from the injected ApiSource, for the purpose of abstraction and granularity.
- Multiple `ViewModel`s where one or more business `UseCase`s are injected, depending on need.
- One `ScreenManager`, multiple downcast `Dispatcher`s for fragment management.
- One `Activity` (with the injected `ScreenManager`) to host and manage all `View`s and shared UI.
- Multiple `View`s (fragments) each with a `Dispatcher` injected for signals to the `Activity`.
- Multiple `View`s (fragments) that may or may not have partner `ViewModel`s, depending on need.
- Just one file where dependency injection is done. :) No boilerplate code.

# Specifics:
- Coded in **full-Kotlin** (While Java, as a language, is like home in Android development, you can't deny that Kotlin, as an alternative, is like magic.)
- Uses **Koin** for dependency injection (Dagger2 is good and all but it presents the creation and use of a lot of boilerplate classes and interfaces. Also, Dagger2 slows build time. With Koin, your DI is ready in a second with no boilerplate code and build overhead)
- Uses **MVVM pattern** and has base classes for Views and ViewModels
- Has ready-to-configure Failure subclasses for your specific failure scenarios
- Has ready-to-configure **Retrofit** client for your networking needs
- Implements **Reactive Programming**

# Samples to take note of:
- files under the package **data.sampleapi** (for an understanding on how the association of ApiClient, ApiSource and UseCases goes here)
- `SampleInjection.kt` under the package **injection** (for some code samples on how dependency injection with Koin was done and to see which classes/interfaces are the injectibles)
- files under the package **samplepages** (to see the MVVM pattern in effect, how the injected useCases in viewModels are done)
- `MainActivity` (for an understanding on how one Activity can manage the views and share the toolbar)

> Visit the documentations written within the files under the **base** and **common** packages for more clarity. :)

# Coming Soon:
- Caching (soft and local)
- Migrate to AndroidX
