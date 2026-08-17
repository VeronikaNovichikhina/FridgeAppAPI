# FridgeChef

**FridgeChef** is an Android app that solves an everyday problem: *"What can I cook with what I already have?"* Users enter the ingredients they have on hand, and the app suggests matching recipes, sorted by the smallest number of missing ingredients.

> Enter your fridge's ingredients — get matching recipes.

## Features

### Ingredient Management
- Add ingredients manually
- Remove ingredients from the list
- Ingredient list is stored locally (Room)
- Ingredients are displayed in alphabetical order

### Recipe Search
- Search recipes by ingredient list via the Spoonacular API
- Recipes are sorted by the number of missing ingredients
- Shows the count of available vs. missing ingredients for each recipe

### Recipe Details
- Full recipe info: photo, cooking time, number of servings
- Step-by-step cooking instructions with navigation
- Full ingredient list with quantities
- Short recipe description

### Favorites
- Add or remove recipes from favorites
- Favorite recipes are available offline
- Favorites list is sorted by date added

### Shopping List
- Automatically generated from a recipe
- Checkboxes to mark items as purchased
- Purchased items are visually struck through

## Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | Clean Architecture (data / domain / presentation) |
| Dependency Injection | Hilt |
| Networking | Retrofit + Gson |
| Local Storage | Room |
| Image Loading | Coil |
| Navigation | Navigation Compose |
| Recipe Data | Spoonacular API |

## Architecture

The app follows **Clean Architecture** principles, split into three layers:

- **data** — API clients (Retrofit), Room database, repository implementations
- **domain** — use cases, business logic, domain models
- **presentation** — Jetpack Compose UI, ViewModels, navigation

Dependencies are wired together with **Hilt**, keeping layers decoupled and testable.

## Screenshot


<img width="145" height="317" alt="image" src="https://github.com/user-attachments/assets/a64acb03-840c-4b31-8172-14b815a7ccf4" />
<img width="156" height="337" alt="image" src="https://github.com/user-attachments/assets/bcc4326c-4d74-4cb4-b228-ab1bb99a7349" />
<img width="156" height="327" alt="image" src="https://github.com/user-attachments/assets/b23be1da-004e-4e4d-9a9d-e7bb3db7cb4c" />
<img width="161" height="342" alt="image" src="https://github.com/user-attachments/assets/fbebf6f2-b86b-4b44-a18e-472753083451" />
<img width="166" height="335" alt="image" src="https://github.com/user-attachments/assets/315f72e8-f5d8-4772-8633-48652ef29794" />
