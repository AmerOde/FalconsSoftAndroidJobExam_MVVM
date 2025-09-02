# FalconsSoftAndroidJobExam_MVVM
Android app built with Kotlin and Clean Architecture that fetches data from a REST API, caches it locally using Room, and displays it with MVVM architecture.


## Features
- Fetch data from API
- Store & retrieve data using Room
- Swipe-to-refresh
- MVVM + Repository pattern

### Architecture
This project follows a **three-layer MVVM architecture** to clearly separate concerns:

1. **Data Layer:** Handles data from APIs, local database (Room), and cache.
2. **Domain Layer:** Contains business logic and use cases for fetching/updating items.
3. **Presentation Layer:** Manages UI logic with ViewModel, Adapter, and user interactions (RecyclerView, SwipeRefresh).

### Description

## Notes
- Filtering and search were not implemented due to time constraints.
