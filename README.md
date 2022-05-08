## У меня лапки

Приложение написанное с помощью технологии : **Kotlin Multiplatform App**. Состоит из трех частей :
- Модуль с логикой, общей для всех платформ (shared)
- iOS приложение
- Android приложение

# Shared модуль.
- Язык : Kotlin
- Библиотека локального хранилища : [SQLDelight](https://github.com/cashapp/sqldelight)
- Linter : Нет
- Тесты : Нет

# iOS приложение
- Язык : Swift
- DI : [Resolver](https://github.com/hmlongco/Resolver)
- Navigation : [SwiftDevPackage](https://github.com/ISSArt-LLC/SwiftDeveloperPackage)
- Linter : [SwiftLint](https://github.com/realm/SwiftLint)
- Тесты : UI
- CI/CD : [Fastlane](https://fastlane.tools/), Github Actions. Выкатка в TestFlight, Запуск UI тестов.

# Android приложение
- Язык : Kotlin
- DI : [Koin](https://insert-koin.io/)
- Navigation : [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- Linter : Нет
- Тесты : Нет
- CI/CD : [Fastlane](https://fastlane.tools/). Выкатка в Internal Testing.

