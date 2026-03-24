# Миграция пакетов org.example → ru.x5.stores

> **Для агенских рабочих:** REQUIRED SUB-SKILL: Используйте superpowers:subagent-driven-development (рекомендуется) или superpowers:executing-plans для реализации этого плана задача за задачей. Шаги используют синтаксис чекбокса (`- [ ]`) для отслеживания.

**Цель:** Мигрировать все пакеты проекта с `org.example` на `ru.x5.stores` для соответствия стандартам компании X5 Retail Group

**Архитектура:** Изменение всех package declarations и imports в Kotlin-файлах и документации без изменения логики приложения

**Примечание про запуск приложения:**
Application plugin не используется в этом проекте. Для проверки работоспособности приложения после миграции:
- Использовать IDE (IntelliJ IDEA) для запуска Main.kt
- Или добавить application plugin отдельно (если требуется)
- Для проверки миграции пакетов достаточно успешной сборки `./gradlew clean build`

**Tech Stack:** Kotlin 2.2.21, Gradle (Kotlin DSL)

---

## Предварительные требования

**Проверка перед началом:**
- [ ] Текущая сборка проходит: `./gradlew clean build`
- [ ] Приложение запускается: `./gradlew run`
- [ ] Все файлы включены в git

**Создание ветки:**
- [ ] Создать feature-ветку: `feature/package-migration-ru.x5.stores`
- [ ] Убедиться, что ветка создана: `git status`

```bash
git checkout -b feature/package-migration-ru.x5.stores
```

---

## Файловая структура (до и после)

**До:**
```
src/main/kotlin/
├── Main.kt (package org.example)
└── org/example/
    ├── api/
    │   ├── mapper/StoreMapper.kt
    │   ├── mocks/MockApi.kt
    │   ├── model/StoreResponse.kt
    │   └── repository/StoresRepository.kt
    └── domain/
        ├── model/Store.kt, StringId.kt, TSType.kt
        └── usecase/StoresUsecase.kt
```

**После:**
```
src/main/kotlin/ru/x5/stores/
├── Main.kt (package ru.x5.stores)
└── ru/x5/stores/
    ├── api/
    │   ├── mapper/StoreMapper.kt
    │   ├── mocks/MockApi.kt
    │   ├── model/StoreResponse.kt
    │   └── repository/StoresRepository.kt
    └── domain/
        ├── model/Store.kt, StringId.kt, TSType.kt
        └── usecase/StoresUsecase.kt
```

---

## Task 1: Обновление настройки Gradle

**Файлы:**
- Modify: `build.gradle.kts`

- [ ] **Step 1: Обновить группу проекта**

```kotlin
group = "ru.x5.stores"
```

**Было:** `group = "org.example"`
**Стало:** `group = "ru.x5.stores"`

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew clean build`
Expected: SUCCESS — конфигурация корректна

- [ ] **Step 3: Commit**

```bash
git add build.gradle.kts
git commit -m "refactor: update Gradle group to ru.x5.stores"
```

---

## Task 2: Обновление Main.kt

**Файлы:**
- Modify: `src/main/kotlin/Main.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores
```

**Было:** `package org.example`
**Стало:** `package ru.x5.stores`

- [ ] **Step 2: Обновить import**

```kotlin
import ru.x5.stores.domain.usecase.StoresUsecase
```

**Было:** `import org.example.domain.usecase.StoresUsecase`
**Стало:** `import ru.x5.stores.domain.usecase.StoresUsecase`

- [ ] **Step 3: Проверить сборку**

Run: `./gradlew clean build`
Expected: SUCCESS — конфигурация корректна, старая структура еще работает

- [ ] **Step 4: Commit**

```bash
git add src/main/kotlin/Main.kt
git commit -m "refactor: update Main.kt package and imports to ru.x5.stores"
```

---

## Task 3: Обновление Domain модели TSType

**Файлы:**
- Modify: `src/main/kotlin/org/example/domain/model/TSType.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.domain.model
```

**Было:** `package org.example.domain.model`
**Стало:** `package ru.x5.stores.domain.model`

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — новый package declaration компилируется корректно

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/domain/model/TSType.kt
git commit -m "refactor: update TSType package to ru.x5.stores.domain.model"
```

---

## Task 4: Обновление Domain модели StringId

**Файлы:**
- Modify: `src/main/kotlin/org/example/domain/model/StringId.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.domain.model
```

**Было:** `package org.example.domain.model`
**Стало:** `package ru.x5.stores.domain.model`

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — новый package declaration компилируется корректно

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/domain/model/StringId.kt
git commit -m "refactor: update StringId package to ru.x5.stores.domain.model"
```

---

## Task 5: Обновление Domain модели Store

**Файлы:**
- Modify: `src/main/kotlin/org/example/domain/model/Store.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.domain.model
```

**Было:** `package org.example.domain.model`
**Стало:** `package ru.x5.stores.domain.model`

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — новый package declaration компилируется корректно

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/domain/model/Store.kt
git commit -m "refactor: update Store package to ru.x5.stores.domain.model"
```

---

## Task 6: Обновление API модели StoreResponse

**Файлы:**
- Modify: `src/main/kotlin/org/example/api/model/StoreResponse.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.api.model
```

**Было:** `package org.example.api.model`
**Стало:** `package ru.x5.stores.api.model`

- [ ] **Step 2: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — новый package declaration компилируется корректно

- [ ] **Step 3: Commit**

```bash
git add src/main/kotlin/org/example/api/model/StoreResponse.kt
git commit -m "refactor: update StoreResponse package to ru.x5.stores.api.model"
```

---

## Task 7: Обновление MockApi

**Файлы:**
- Modify: `src/main/kotlin/org/example/api/mocks/MockApi.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.api.mocks
```

**Было:** `package org.example.api.mocks`
**Стало:** `package ru.x5.stores.api.mocks`

- [ ] **Step 2: Обновить import**

```kotlin
import ru.x5.stores.api.model.StoreResponse
```

**Было:** `import org.example.api.model.StoreResponse`
**Стало:** `import ru.x5.stores.api.model.StoreResponse`

- [ ] **Step 3: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — package и import обновлены корректно

- [ ] **Step 4: Commit**

```bash
git add src/main/kotlin/org/example/api/mocks/MockApi.kt
git commit -m "refactor: update MockApi package and imports to ru.x5.stores.api.mocks"
```

---

## Task 8: Обновление StoreMapper

**Файлы:**
- Modify: `src/main/kotlin/org/example/api/mapper/StoreMapper.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.api.mapper
```

**Было:** `package org.example.api.mapper`
**Стало:** `package ru.x5.stores.api.mapper`

- [ ] **Step 2: Обновить import-ы**

```kotlin
import ru.x5.stores.api.model.StoreResponse
import ru.x5.stores.domain.model.Store
import ru.x5.stores.domain.model.StringId
import ru.x5.stores.domain.model.TSType
```

**Было:**
```kotlin
import org.example.api.model.StoreResponse
import org.example.domain.model.Store
import org.example.domain.model.StringId
import org.example.domain.model.TSType
```

**Стало:**
```kotlin
import ru.x5.stores.api.model.StoreResponse
import ru.x5.stores.domain.model.Store
import ru.x5.stores.domain.model.StringId
import ru.x5.stores.domain.model.TSType
```

- [ ] **Step 3: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — все package и import обновлены корректно

- [ ] **Step 4: Commit**

```bash
git add src/main/kotlin/org/example/api/mapper/StoreMapper.kt
git commit -m "refactor: update StoreMapper package and imports to ru.x5.stores.api.mapper"
```

---

## Task 9: Обновление StoresRepository

**Файлы:**
- Modify: `src/main/kotlin/org/example/api/repository/StoresRepository.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.api.repository
```

**Было:** `package org.example.api.repository`
**Стало:** `package ru.x5.stores.api.repository`

- [ ] **Step 2: Обновить import-ы**

```kotlin
import ru.x5.stores.api.mapper.StoreMapper
import ru.x5.stores.api.mocks.MockApi
import ru.x5.stores.domain.model.Store
```

**Было:**
```kotlin
import org.example.api.mapper.StoreMapper
import org.example.api.mocks.MockApi
import org.example.domain.model.Store
```

**Стало:**
```kotlin
import ru.x5.stores.api.mapper.StoreMapper
import ru.x5.stores.api.mocks.MockApi
import ru.x5.stores.domain.model.Store
```

- [ ] **Step 3: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — все package и import обновлены корректно

- [ ] **Step 4: Commit**

```bash
git add src/main/kotlin/org/example/api/repository/StoresRepository.kt
git commit -m "refactor: update StoresRepository package and imports to ru.x5.stores.api.repository"
```

---

## Task 10: Обновление StoresUsecase

**Файлы:**
- Modify: `src/main/kotlin/org/example/domain/usecase/StoresUsecase.kt`

- [ ] **Step 1: Обновить package declaration**

```kotlin
package ru.x5.stores.domain.usecase
```

**Было:** `package org.example.domain.usecase`
**Стало:** `package ru.x5.stores.domain.usecase`

- [ ] **Step 2: Обновить import-ы**

```kotlin
import ru.x5.stores.api.repository.StoresRepository
import ru.x5.stores.domain.model.Store
```

**Было:**
```kotlin
import org.example.api.repository.StoresRepository
import org.example.domain.model.Store
```

**Стало:**
```kotlin
import ru.x5.stores.api.repository.StoresRepository
import ru.x5.stores.domain.model.Store
```

- [ ] **Step 3: Проверить сборку**

Run: `./gradlew build`
Expected: SUCCESS — все файлы проекта обновлены, все импорты корректны

- [ ] **Step 4: Commit**

```bash
git add src/main/kotlin/org/example/domain/usecase/StoresUsecase.kt
git commit -m "refactor: update StoresUsecase package and imports to ru.x5.stores.domain.usecase"
```

---

## Task 11: Перемещение файлов в новую структуру директорий

**Файлы:** Все файлы src/main/kotlin

- [ ] **Step 1: Создать новую структуру директорий**

```bash
mkdir -p src/main/kotlin/ru/x5/stores/api/{mapper,mocks,model,repository}
mkdir -p src/main/kotlin/ru/x5/stores/domain/{model,usecase}
```

- [ ] **Step 2: Переместить файлы из org.example в ru.x5.stores**

```bash
# Main.kt
mv src/main/kotlin/Main.kt src/main/kotlin/ru/x5/stores/

# Domain модели
mv src/main/kotlin/org/example/domain/model/TSType.kt src/main/kotlin/ru/x5/stores/domain/model/
mv src/main/kotlin/org/example/domain/model/StringId.kt src/main/kotlin/ru/x5/stores/domain/model/
mv src/main/kotlin/org/example/domain/model/Store.kt src/main/kotlin/ru/x5/stores/domain/model/

# API модели
mv src/main/kotlin/org/example/api/model/StoreResponse.kt src/main/kotlin/ru/x5/stores/api/model/

# API mapper
mv src/main/kotlin/org/example/api/mapper/StoreMapper.kt src/main/kotlin/ru/x5/stores/api/mapper/

# API repository
mv src/main/kotlin/org/example/api/repository/StoresRepository.kt src/main/kotlin/ru/x5/stores/api/repository/

# Mock API
mv src/main/kotlin/org/example/api/mocks/MockApi.kt src/main/kotlin/ru/x5/stores/api/mocks/

# UseCase
mv src/main/kotlin/org/example/domain/usecase/StoresUsecase.kt src/main/kotlin/ru/x5/stores/domain/usecase/

# Удалить старую структуру
rm -rf src/main/kotlin/org/example/
```

- [ ] **Step 3: Проверить структуру директорий**

Run: `find src/main/kotlin -name "*.kt"`

Expected output:
```
src/main/kotlin/ru/x5/stores/Main.kt
src/main/kotlin/ru/x5/stores/api/mapper/StoreMapper.kt
src/main/kotlin/ru/x5/stores/api/mocks/MockApi.kt
src/main/kotlin/ru/x5/stores/api/model/StoreResponse.kt
src/main/kotlin/ru/x5/stores/api/repository/StoresRepository.kt
src/main/kotlin/ru/x5/stores/domain/model/Store.kt
src/main/kotlin/ru/x5/stores/domain/model/StringId.kt
src/main/kotlin/ru/x5/stores/domain/model/TSType.kt
src/main/kotlin/ru/x5/stores/domain/usecase/StoresUsecase.kt
```

- [ ] **Step 4: Проверить сборку**

Run: `./gradlew clean build`
Expected: SUCCESS — все файлы обновлены и перемещены

- [ ] **Step 5: Проверить структуру скомпилированных классов**

Run: `find build/classes/kotlin/main -type d | sort | grep ru`
Expected output:
```
build/classes/kotlin/main/ru/x5/stores
build/classes/kotlin/main/ru/x5/stores/api
build/classes/kotlin/main/ru/x5/stores/api/mapper
build/classes/kotlin/main/ru/x5/stores/api/mocks
build/classes/kotlin/main/ru/x5/stores/api/model
build/classes/kotlin/main/ru/x5/stores/api/repository
build/classes/kotlin/main/ru/x5/stores/domain
build/classes/kotlin/main/ru/x5/stores/domain/model
build/classes/kotlin/main/ru/x5/stores/domain/usecase
```

- [ ] **Step 6: Проверить отсутствие классов в старой структуре**

Run: `find build/classes/kotlin/main/org -name "*.class"`
Expected: Пустой вывод (no results)

- [ ] **Step 7: Commit**

```bash
git add src/main/kotlin/
git rm -r src/main/kotlin/org/example/
git commit -m "refactor: move all Kotlin files to new ru.x5.stores package structure"
```

---

## Task 12: Обновление документации

**Файлы:**
- Modify: `docs/superpowers/specs/2026-03-24-api-domain-models-design.md`
- Modify: `docs/superpowers/plans/2026-03-24-api-domain-models-implementation.md`

- [ ] **Step 1: Обновить все упоминания org.example в design spec**

Replace all occurrences of:
- `org.example` → `ru.x5.stores`

Files to update in spec:
- Package structure diagram
- All package locations
- Import statements

- [ ] **Step 2: Обновить все упоминания org.example в implementation plan**

Replace all occurrences of:
- `org.example` → `ru.x5.stores`

Files to update in plan:
- File structure diagram
- All package declarations in code samples
- All import statements in code samples

- [ ] **Step 3: Verify all package references updated**

Run: `grep -r "org.example" docs/superpowers/`
Expected: No results (all replaced with ru.x5.stores)

- [ ] **Step 4: Commit**

```bash
git add docs/superpowers/
git commit -m "docs: update all package references to ru.x5.stores"
```

---

## Task 13: Финальная проверка

**Files:** Все файлы проекта

- [ ] **Step 1: Полная очистка и сборка**

Run: `./gradlew clean build`
Expected: SUCCESS — весь проект собирается без ошибок

- [ ] **Step 2: Проверить наличие MainKt в правильном пакете**

Run: `find build/classes/kotlin/main -name "MainKt.class"`
Expected: `build/classes/kotlin/main/ru/x5/stores/MainKt.class`

- [ ] **Step 3: Проверить отсутствие остатков org.example в коде**

Run: `grep -r "org.example" src/`
Expected: No results (all replaced with ru.x5.stores)

- [ ] **Step 4: Проверить, что все импорты обновлены**

Run: `grep -r "import.*org\.example" src/`
Expected: No results (все импорты обновлены)

- [ ] **Step 5: Проверить, что структура пакетов соответствует новой**

Run: `find src/main/kotlin/ru/x5/stores -type f -name "*.kt" | wc -l`
Expected: 9 файлов

Run: `find src/main/kotlin/ru/x5/stores -type f -name "*.kt"`
Expected output:
```
src/main/kotlin/ru/x5/stores/Main.kt
src/main/kotlin/ru/x5/stores/api/mapper/StoreMapper.kt
src/main/kotlin/ru/x5/stores/api/mocks/MockApi.kt
src/main/kotlin/ru/x5/stores/api/model/StoreResponse.kt
src/main/kotlin/ru/x5/stores/api/repository/StoresRepository.kt
src/main/kotlin/ru/x5/stores/domain/model/Store.kt
src/main/kotlin/ru/x5/stores/domain/model/StringId.kt
src/main/kotlin/ru/x5/stores/domain/model/TSType.kt
src/main/kotlin/ru/x5/stores/domain/usecase/StoresUsecase.kt
```

- [ ] **Step 6: Функциональный тест (через IDE или manual)**

Для проверки работоспособности запустить Main.kt через IDE:
1. Открыть `src/main/kotlin/ru/x5/stores/Main.kt` в IntelliJ IDEA
2. Нажать кнопку Run рядом с `fun main()`
3. Убедиться, что вывод соответствует спецификации

Ожидаемый вывод:
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

**Если приложение не запускается через IDE:**
- Проверить, что JVM toolchain настроен (Java 17+)
- Refresh Gradle project в IDE
- Invalidate caches в IntelliJ: File → Invalidate Caches/Restart

- [ ] **Step 7: Commit (фиксировать завершение)**

```bash
git add .
git commit -m "chore: final verification complete - package migration to ru.x5.stores successful"
```

---

## Task 14: Объединение в main (опционально)

- [ ] **Step 1: Запушить ветку**

```bash
git push origin feature/package-migration-ru.x5.stores
```

- [ ] **Step 2: Создать Pull Request (если используется)**

Создать PR с описанием изменений:
- Обновлены все пакеты с org.example на ru.x5.stores
- Обновлена документация
- Все тесты проходят

- [ ] **Step 3: Merge в main (после обзора)**

```bash
git checkout main
git merge feature/package-migration-ru.x5.stores
git push
```

- [ ] **Step 4: Удалить feature-ветку**

```bash
git branch -d feature/package-migration-ru.x5.stores
git push origin --delete feature/package-migration-ru.x5.stores
```

---

## Итоговая проверка

После успешной миграции:

1. **Пакеты обновлены:** все файлы используют `ru.x5.stores` ✅
2. **Сборка проходит:** `./gradlew clean build` ✅
3. **Файловая структура:** новая иерархия ru/x5/stores/ создана ✅
4. **Документация обновлена:** все упоминания org.example заменены ✅
5. **Нет остатков:** никаких отсылок к org.example в коде ✅
6. **Приложение работает:** запуск через IDE или manual testing ✅

**Статистика изменений:**
- Файлы исходного кода: 9 файлов
- Файлы документации: 2 файла
- Файловая структура: создана новая иерархия ru/x5/stores/
- Package декларации: 9 изменений
- Import-ы: ~20 изменений
- Директории: созданы ru/x5/stores/{api,domain} и поддиректории

**Проверка миграции:**
```bash
# Проверить сборку
./gradlew clean build

# Проверить все импорты ru.x5.stores
grep -r "import ru\.x5\.stores" src/

# Проверить количество Kotlin файлов
find src/main/kotlin -name "*.kt" | wc -l  # Должно быть 9

# Убедиться, что org.example отсутствует
grep -r "org\.example" src/main/kotlin/  # Должно быть пусто

# Проверить структуру скомпилированных классов
find build/classes/kotlin/main/ru/x5/stores -name "*.class"
```

**Функциональная проверка (через IDE):**
Открыть Main.kt в IDE и запустить:
- Вывод: список 3 магазинов с правильной информацией
- ID: store-001, store-002, store-003
- Названия и адреса соответствуют mock данным
- ТС: TSX, TS5, TSC
- Footer: "Всего магазинов: 3"

---

## Стратегия отката (Rollback)

Если что-то пошло не так:

```bash
# Отменить изменения в текущей рабочей директории
git reset --hard HEAD

# Или вернуться к исходной ветке
git checkout main

# Или создать новую ветку из чистого состояния
git checkout -b feature/retry-package-migration
```

---

## Примечания

- Все изменения затрагивают только package declarations и import-ы
- Логика приложения остается неизменной
- Миграция атомарная — либо все работает, либо ничего не работает
- Рекомендуется выполнять по одному task за раз для лучшей отслеживаемости
- После каждого task рекомендуется run build для быстрого обнаружения проблем

---

## Диагностика и устранение проблем

### Ошибки компиляции (Build failed)

**1. Unresolved reference (Неразрешенная ссылка)**
```
error: unresolved reference: org.example.domain.usecase
```
Причины:
- Файл еще не обновлен
- Import указывает на старый пакет
- Package declaration не обновлен

Решение:
```bash
# Проверить package declarations
grep "^package" src/main/kotlin/ru/x5/stores/**/*.kt

# Проверить imports
grep "^import" src/main/kotlin/ru/x5/stores/**/*.kt | grep org.example

# Убедиться, что package соответствует import
# package ru.x5.stores.domain.usecase
# import ru.x5.stores.domain.usecase.*
```

**2. Package does not exist (Пакет не существует)**
```
error: package org.example.domain.mapper does not exist
```
Причины:
- Структура директорий еще не обновлена
- Файл остался в старом месте

Решение:
```bash
# Найти, где находится файл
find src -name "StoreMapper.kt"

# Убедиться, что файл в нужном месте
# src/main/kotlin/ru/x5/stores/api/mapper/StoreMapper.kt
```

**3. Класс не найден после миграции (Task 11)**
```
e: file://src/main/kotlin/ru/x5/stores/Main.kt - Unresolved reference: MainKt
```
Причины:
- Главный класс еще не скомпилирован
- Кэш Gradle outdated

Решение:
```bash
# Полная очистка
./gradlew clean

# Пересобрать
./gradlew build

# Проверить, что класс скомпилирован
find build/classes -name "MainKt.class"
```

### Ошибки после перемещения файлов (Task 11)

**1. Файлы не компилируются после перемещения**
```
error: expect member 'Main' in package 'ru.x5.stores'
```
Причины:
- Package declaration в файле не обновлен
- Файл перемещен, но package declaration остался старый

Решение:
```bash
# Проверить package declaration в Main.kt
cat src/main/kotlin/ru/x5/stores/Main.kt | head -1
# Должно быть: package ru.x5.stores

# Исправить, если package declaration неправильный
```

**2. Старые файлы остаются в build/classes**
```bash
find build/classes -path "*/org/example/*" -name "*.class"
```
Если результаты не пустые:

Решение:
```bash
# Полная очистка кэшей
./gradlew clean

# Удалить .gradle caches (экстремальный вариант)
rm -rf .gradle/

# Пересобрать
./gradlew build
```

### Ошибки импортов (Import errors)

**1. Imports не обновлены полностью**
```
error: unresolved reference: Store (в StoreMapper)
import ru.x5.stores.api.model.Store
```
Причины:
- Import указывает на неправильный пакет
- Store находится в domain, не api

Решение:
```bash
# Проверить, где находится Store
find src -name "Store.kt"
# src/main/kotlin/ru/x5/stores/domain/model/Store.kt

# Обновить import
# Должно быть: import ru.x5.stores.domain.model.Store
```

**2. Circular imports (циклические импорты)**
Этот проект не должен иметь циклических импортов, но если возникают:

Решение:
- Проверить архитектуру: API не должна import Domain
- Domain может import API только для репозиториев
- Mapper не должна быть в Domain (текущая архитектура верна)

### Проблемы с запуском через IDE (Task 13 Step 6)

**1. IDE не видит новые файлы**
- Refresh Gradle project в IntelliJ
- File → Invalidate Caches → Invalidate and Restart

**2. Класс Main не находится**
- Проверить package declaration: `package ru.x5.stores`
- Проверить, что файл в src/main/kotlin/ru/x5/stores/Main.kt
- Re-import Gradle project

**3. Ошибки JVM toolchain**
```
Unsupported class file major version
```
Решение:
- Убедиться, что Java 17+ установлен
- Проверить JVM toolchain: `./gradlew javaToolchains`
- В IntelliJ IDEA: File → Project Structure → Project SDK

### Common debugging commands

```bash
# Быстрая проверка всех package declarations
grep "^package" src/main/kotlin/ru/x5/stores/**/*.kt

# Найти все импорты org.example (должно быть пусто)
grep "^import.*org\.example" src/main/kotlin/ru/x5/stores/**/*.kt

# Проверить, что все файлы на месте
find src/main/kotlin/ru/x5/stores -name "*.kt" | sort

# Проверить, что скомпилировано
find build/classes/kotlin/main/ru/x5/stores -name "*.class" | wc -l  # 13+

# Проверить старую структуру (должно быть пусто)
find build/classes/kotlin/main/org -name "*.class"

# Детальная информация об ошибках компиляции
./gradlew clean build --stacktrace 2>&1 | grep "error:"
```

### Если ничего не помогает (Rollback strategy)

```bash
# Отменить все изменения в текущей рабочей директории
git reset --hard HEAD

# Вернуться в чистое состояние
git checkout main

# Удалить feature ветку
git branch -D feature/package-migration-ru.x5.stores

# Начать с нуля
git checkout -b feature/package-migration-ru.x5.stores
```
