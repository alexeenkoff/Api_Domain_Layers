# API и Domain модели в Kotlin: Дизайн спецификация

**Дата:** 2026-03-24
**Проект:** `/Users/dmitrijalekseenkov/idea-projects/m4l4_api_domain`

**Цель:**

Создать учебный пример разделения API и Domain слоев в Kotlin с использованием kotlinx.serialization, демонстрирующий чистую архитектуру и правильный маппинг данных между слоями.

---

## Архитектура

### Структура приложения

Двухслойная архитектура с четким разделением ответственности:

```
Main.kt
    ↓
StoresUsecase (domain/usecase)
    ↓
StoresRepository (api/repository)
    ↓
StoreResponse (api/model)
    ↓
StoreMapper (api/mapper)
    ↓
Store (domain/model)
```

### Пакеты

```
src/main/kotlin/org/example/
├── Main.kt
├── api/
│   ├── model/
│   │   └── StoreResponse.kt
│   ├── repository/
│   │   └── StoresRepository.kt
│   └── mapper/
│       └── StoreMapper.kt
└── domain/
    ├── model/
    │   ├── Store.kt
    │   ├── StoreType.kt
    │   ├── TSType.kt
    │   └── StringId.kt
    └── usecase/
        └── StoresUsecase.kt
```

---

## API Layer

### StoreResponse.kt

DTO модель для десериализации JSON из внешнего источника. Содержит только примитивные типы.

**Местоположение:** `org.example.api.model`

**Поля:**
- `storeId: String` — идентификатор магазина
- `storeName: String` — название магазина
- `storeType: String` — тип магазина (TSX, TS5, TSH)
- `address: String` — адрес магазина
- `ts: String` — технический статус (TSX, TS5, TSC)

**Характеристики:**
- Все поля не nullable
- Аннотация `@Serializable` для kotlinx.serialization
- Поля помечены `@SerialName` с именами из JSON
- Не содержит бизнес-логики

### StoresRepository.kt

Mock реализация репозитория для учебных целей.

**Местоположение:** `org.example.api.repository`

**Функционал:**
- Возвращает моковые данные `List<StoreResponse>`
- Не взаимодействует с реальными сетевыми запросами

### StoreMapper.kt

Класс для маппинга API моделей в Domain модели.

**Местоположение:** `org.example.api.mapper`

**Функционал:**
- Метод `toDomain(List<StoreResponse>): List<Store>`
- Преобразует каждый `StoreResponse` в `Store`
- Создает `StringId` из строки
- Парсит строки в enum-ы (`StoreType`, `TSType`)

---

## Domain Layer

### Store.kt

Бизнес-модель магазина.

**Местоположение:** `org.example.domain.model`

**Поля:**
- `id: StringId` — строго типизирированный идентификатор
- `name: String` — название магазина
- `type: StoreType` — тип магазина (enum)
- `address: String` — адрес магазина
- `ts: TSType` — технический статус (enum)

**Характеристики:**
- Все поля не nullable
- Без аннотаций сериализации
- Отражает бизнес-сущность

### StringId.kt

Класс-обертка над String для строгой типизации идентификаторов.

**Местоположение:** `org.example.domain.model`

**Характеристики:**
- Value object
- Хранит String
- Обеспечивает типобезопасность

### StoreType.kt

Enum для типа магазина.

**Местоположение:** `org.example.domain.model`

**Значения:**
- `TSX`
- `TS5`
- `TSH`

### TSType.kt

Enum для технического статуса.

**Местоположение:** `org.example.domain.model`

**Значения:**
- `TSX`
- `TS5`
- `TSC`

### StoresUsecase.kt

Оркестратор бизнес-логики.

**Местоположение:** `org.example.domain.usecase`

**Функционал:**
- Метод `getStores(): List<Store>`
- Вызывает `StoresRepository` для получения данных
- Использует `StoreMapper` для преобразования в Domain модели
- Возвращает готовый `List<Store>`

---

## Main.kt

Точка входа приложения.

**Функционал:**
- Создает экземпляр `StoresUsecase`
- Вызывает `getStores()`
- Выводит результат в консоль
- Демонстрирует полную цепочку передачи данных

---

## Конфигурация Gradle

### build.gradle.kts

**Плагины:**
- `kotlin("jvm")` — стандартный Kotlin плагин
- `kotlin("plugin.serialization")` — плагин для kotlinx.serialization

**Зависимости:**
- `kotlinx-serialization-json:1.10.0` — актуальная версия

---

## Поток данных

1. **Main.kt** → Создает и вызывает `StoresUsecase`
2. **StoresUsecase** → Вызывает `StoresRepository.getStores()`
3. **StoresRepository** → Возвращает моковый `List<StoreResponse>`
4. **StoresUsecase** → Передает результат в `StoreMapper.toDomain()`
5. **StoreMapper** → Преобразует каждый `StoreResponse` в `Store`
6. **StoresUsecase** → Возвращает `List<Store>`
7. **Main.kt** → Выводит результат в консоль

---

## Тестирование

Приложение должно быть проверено путем:
- Сборки через `./gradlew build`
- Запуска `Main.kt` и проверки вывода в консоль
- Проверки типов данных в цепочке маппинга

---

## Учебные цели

Пример демонстрирует:
1. Разделение API и Domain слоев
2. Использование `@Serializable` для API моделей
3. Создание Type-safe value objects (StringId)
4. Правильный маппинг между слоями
5. Оркестрация бизнес-логики через UseCase
6. Чистая архитектура без смешения слоев
