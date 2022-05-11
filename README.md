## У меня лапки

Приложение написанное с помощью технологии : **Kotlin Multiplatform App**. Состоит из трех частей :
- Модуль с логикой, общей для всех платформ (shared)
- iOS приложение
- Android приложение

# Shared модуль.
- Язык : Kotlin
- Библиотека локального хранилища : [SQLDelight](https://github.com/cashapp/sqldelight)
- Linter : [detekt](https://github.com/detekt/detekt)
- Тесты : Нет

# iOS приложение
- Язык : Swift
- DI : [Resolver](https://github.com/hmlongco/Resolver)
- Navigation : [SwiftDevPackage](https://github.com/ISSArt-LLC/SwiftDeveloperPackage)
- Linter : [SwiftLint](https://github.com/realm/SwiftLint)
- Тесты : UI
- CI/CD : [Fastlane](https://fastlane.tools/), Github Actions. Выкатка в TestFlight, Запуск UI тестов.
- Опубликовано : [App Store](https://apps.apple.com/us/app/%D1%83-%D0%BC%D0%B5%D0%BD%D1%8F-%D0%BB%D0%B0%D0%BF%D0%BA%D0%B8/id1620575531)

# Android приложение
- Язык : Kotlin
- DI : [Koin](https://insert-koin.io/)
- Navigation : [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- Linter : [detekt](https://github.com/detekt/detekt)
- Тесты : Нет
- CI/CD : [Fastlane](https://fastlane.tools/). Выкатка в Internal Testing.
- Опубликовано : [Play Market](https://play.google.com/store/apps/details?id=com.nikolai.ihavepaws.android)

# Дизайн шаблон
[Figma](https://www.figma.com/file/hs8ix9eyqbhMweDaKu3SHp/Bloo-Lo-Fi-Wireframe-Kit-(Community)?node-id=111%3A3207)

