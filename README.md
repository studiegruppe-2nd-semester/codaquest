# CodaQuest

For loop initiated... Inifinite inspiration loading...

codaQuest is the app for you, if you are looking for inspiration for your next intereseting coding project.

## Package structure
```
com.example.codaquest
├── data                 # Data layer containing repositories and data sources
│ ├── repositories       # Repositories for accessing data sources
│ └── services           # Services for accessing accounts or API
├── domain               # Domain layer with models and business logic
│ ├── interfaces         # Interfaces
│ └── models             # Models (e.g. custom classes)
├── ui                   # Presentation layer with UI components
│ ├── components         # Composables such as screens and single elements
│ │ ├── common           # Common composables (e.g. dropdowns, textfields)
│ │ ├── gallery          # Gallery feature UI components
│ │ ├── generateProject  # Generate projects feature UI components
│ │ ├── home             # Home screen UI components
│ │ ├── loading          # Temporary loading screen UI components
│ │ ├── login            # Login and sign-up feature UI components
│ │ ├── navbar           # Navigation bar UI components
│ │ ├── onboarding       # Onboarding screen UI components
│ │ ├── profile          # Profile feature UI components
│ │ ├── savedProjects    # Saved projects screen UI components
│ │ └── viewmodels       # ViewModels for managing UI state
│ ├── navigation         # Navigation components
│ └── theme              # Theming (colors, typography)
└── util                 # Utility objects and functions (tests)
```
