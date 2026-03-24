# API и Domain модели в Kotlin: План реализации

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Создать учебный пример разделения API и Domain слоев в Kotlin с использованием kotlinx.serialization и mock реализацией репозитория

**Architecture:** Двухслойная архитектура (API и Domain) с mock API, репозиторием и usecase. API модели (@Serializable) - Domain модели (type-safe) с mapper и строгой типизацией.

**Tech Stack:** Kotlin 2.2.21, kotlinx.serialization-json 1.10.0, Gradle (Kotlin DSL)

---

## Файловая структура

```
src/main/kotlin/org/example/
├── Main.kt (modify)
├── api/
│   ├── model/
│   │   └── StoreResponse.kt (create)
│   ├── repository/
│   │   └── StoresRepository.kt (create)
│   ├── mapper/
│   │   └── StoreMapper.kt (create)
│   └── mocks/
│       └── MockApi.kt (create)
└── domain/
    ├── model/
    │   ├── Store.kt (create)
    │   ├── TSType.kt (create)
    │   └── StringId.kt (create)
    └── usecase/
        └── StoresUsecase.kt (create)
```

---

## Task 1: Настройка Gradle для kotlinx.serialization

**Files:**
- Modify: `build.gradle.kts`

- [ ] **Step 1: Добавить плагин kotlinx.serialization**

```kotlin
plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("plugin.serialization") version "2.2.21"
}
```

- [ ] **Step 2: Добавить зависимость kotlinx-serialization-json**

```kotlin
dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")
}
```

- [ ] **Step 3: Синхронизировать Gradle**

Run: `./gradlew build`
Expected: SUCCESS — проект собирается без ошибок

- [ ] **Step 4: Commit**

```bash
git add build.gradle.kts
git commit -m "feat: setup kotlinx.serialization plugin and dependency"
```

---

## Task 2: Создание Domain моделей (Enums и Value Object)

**Files:**
- Create: `src/main/kotlin/org/example/domain/model/TSType.kt`
- Create: `src/main/kotlin/org/example/domain/model/StringId.kt`

- [ ] **Step 1: Создать TSType enum**

```kotlin
package org.example.domain.model

enum class TSType {
    TSX, TS5, TSC
}
```

- [ ] **Step 2: Создать StringId value object**

```kotlin
package org.example.domain.model

@JvmInline
value class StringId(val value: String)
```

- [ ] **Step 3: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS -

- [ ] **Step 4: Commit**

```bash
git add src/main/kotlin/org/example/domain/model/
git commit -m "feat: add domain value objects (TSType, StringId)"
```

---

## Task 3: Создание Store Domain модели

**Files:**
- Create: `src/main/kotlin/org/example/domain/model/Store.kt`

- [ ] **Step 1: Создать Store data class**

```kotlin
package org.example.domain.model

data class Store(
    val id: StringId,
    val name: String,
    val address: String,
    val ts: TSType
)
```

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS -

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/domain/model/Store.kt
git commit -m "feat: add Store domain model"
```

---

## Task 4: Создание StoreResponse API модели

**Files:**
- Create: `src/main/kotlin/org/example/api/model/StoreResponse.kt`

- [ ] **Step 1: Создать StoreResponse с @Serializable**

```kotlin
package org.example.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    @SerialName("storeId")
    val storeId: String,

    @SerialName("storeName")
    val storeName: String,

    @SerialName("address")
    val address: String,

    @SerialName("ts")
    val ts: String
)
```

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS - плагин serialization генерирует код

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/api/model/StoreResponse.kt
git commit -m "feat: add StoreResponse API model with @Serializable"
```

---

## Task 5: Создание MockApi

**Files:**
- Create: `src/main/kotlin/org/example/api/mocks/MockApi.kt`

- [ ] **Step 1: Создать MockApi с mock данными**

```kotlin
package org.example.api.mocks

import org.example.api.model.StoreResponse

object MockApi {
    fun getStores(): List<StoreResponse> {
        return listOf(
            StoreResponse(
                storeId = "store-001",
                storeName = "Пятерочка - Тверская",
                address = "г. Москва, ул. Тверская, 1",
                ts = "TSX"
            ),
            StoreResponse(
                storeId = "store-002",
                storeName = "Перекресток - Арбат",
                address = "г. Москва, ул. Арбат, 5",
                ts = "TS5"
            ),
            StoreResponse(
                storeId = "store-003",
                storeName = "Чижик - Садовая",
                address = "г. Москва, ул. Садовая, 3",
                ts = "TSC"
            )
        )
    }
}
```

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS -

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/api/mocks/MockApi.kt
git commit -m "feat: add MockApi with mock store data"
```

---

## Task 6: Создание StoreMapper

**Files:**
- Create: `src/main/kotlin/org/example/api/mapper/StoreMapper.kt`

- [ ] **Step 1: Создать StoreMapper с маппингом**

```kotlin
package org.example.api.mapper

import org.example.api.model.StoreResponse
import org.example.domain.model.Store
import org.example.domain.model.StringId
import org.example.domain.model.TSType

class StoreMapper {

    fun toDomain(api: StoreResponse): Store {
        return Store(
            id = StringId(api.storeId),
            name = api.storeName,
            address = api.address,
            ts = TSType.valueOf(api.ts)
        )
    }

    fun toDomain(apiList: List<StoreResponse>): List<Store> {
        return apiList.map { toDomain(it) }
    }
}
```

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS -

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/api/mapper/StoreMapper.kt
git commit -m "feat: add StoreMapper for API to Domain conversion"
```

---

## Task 7: Создание StoresRepository

**Files:**
- Create: `src/main/kotlin/org/example/api/repository/StoresRepository.kt`

- [ ] **Step 1: Создать StoresRepository с mapper**

```kotlin
package org.example.api.repository

import org.example.api.mapper.StoreMapper
import org.example.api.mocks.MockApi
import org.example.domain.model.Store

class StoresRepository(
    private val mockApi: MockApi = MockApi,
    private val mapper: StoreMapper = StoreMapper()
) {
    fun getStores(): List<Store> {
        val apiStores = mockApi.getStores()
        return mapper.toDomain(apiStores)
    }
}
```

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS -

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/api/repository/StoresRepository.kt
git commit -m "feat: add StoresRepository with mock API and mapper"
```

---

## Task 8: Создание StoresUsecase

**Files:**
- Create: `src/main/kotlin/org/example/domain/usecase/StoresUsecase.kt`

- [ ] **Step 1: Создать StoresUsecase**

```kotlin
package org.example.domain.usecase

import org.example.api.repository.StoresRepository
import org.example.domain.model.Store

class StoresUsecase(
    private val repository: StoresRepository = StoresRepository()
) {
    fun getStores(): List<Store> {
        return repository.getStores()
    }
}
```

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS -

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/domain/usecase/StoresUsecase.kt
git commit -m "feat: add StoresUsecase for business logic orchestration"
```

---

## Task 9: Обновление Main.kt

**Files:**
- Modify: `src/main/kotlin/org/example/Main.kt`

- [ ] **Step 1: Заменить содержимое Main.kt**

```kotlin
package org.example

import org.example.domain.usecase.StoresUsecase

fun main() {
    val usecase = StoresUsecase()
    val stores = usecase.getStores()

    println("=== Магазины ===")
    println()
    stores.forEach { store ->
        println("ID: ${store.id.value}")
        println("Название: ${store.name}")
        println("Адрес: ${store.address}")
        println("ТС: ${store.ts}")
        println()
    }

    println("Всего магазинов: ${stores.size}")
}
```

- [ ] **Step 2: Запустить приложение**

Run: `./gradlew run`
Expected: SUCCESS - выводится список магазинов с правильными типами

```
=== Магазины ===

ID: store-001
Название: Пятерочка - Тверская
Адрес: г. Москва, ул. Тверская, 1
ТС: TSX

ID: store-002
Название: Перекресток - Арбат
Адрес: г. Москва, ул. Арбат, 5
ТС: TS5

ID: store-003
Название: Чижик - Садовая
Адрес: г. Москва, ул. Садовая, 3
ТС: TSC

Всего магазинов: 3
```

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/Main.kt
git commit -m "feat: update Main.kt to demonstrate full data flow"
```

---

## Task 10: Финальная проверка

**Files:**
- Test: Full project build and run

- [ ] **Step 1: Проверить финальную сборку**

Run: `./gradlew clean build`
Expected: SUCCESS - весь проект собирается без ошибок

- [ ] **Step 2: Проверить работу приложения**

Run: `./gradlew run`
Expected: SUCCESS - выводится список 3-х магазинов с правильной информацией

- [ ] **Step 3: Commit (фиксировать завершение работы)**

```bash
git add .
git commit -m "chore: final verification complete - API and Domain layers working"
```

---

## Итоговая проверка

После выполнения всех задач приложение должно:

1. **Строиться без ошибок**: `./gradlew build` ✅
2. **Запускаться успешно**: `./gradlew run` ✅
3. **Выводить 3 магазина** с правильной информацией
4. **Продемонстрировать** полную цепочку:
   - Main → StoresUsecase
   - → StoresRepository
   - → MockApi.getStores()
   - → StoreMapper.toDomain()
   - → List<Store>
   - → Console output

**Архитектурные принципы:**
- ✅ API слои: только примитивы, @Serializable
- ✅ Domain слои: типобезопасные value objects, enum-ы
- ✅ Чистое разделение: mapper изолирует слои
- ✅ Mock реализация: без реальных сетевых запросов
- ✅ Оркестрация: UseCase управляет бизнес-логикой
