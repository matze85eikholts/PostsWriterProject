"#PostWriterProject" 
# Gson CRUD Console App

Простое консольное CRUD-приложение на Java с использованием библиотеки Gson для сериализации/десериализации JSON.

## 🎯 Задача

Реализовать систему управления писателями, постами и метками с хранением данных в JSON-файлах.

## 🏗️ Архитектура

- **model**: POJO-классы сущностей
- **repository**: Работа с файлами через Gson
- **controller**: Логика обработки операций
- **view**: Консольный интерфейс

## 📦 Сущности

- `Writer` (id, firstName, lastName, List<Post>)
- `Post` (id, title, content, List<Label>)
- `Label` (id, name)
- `Status` (ACTIVE, DELETED)

Удаление — мягкое (меняется статус на `DELETED`).

## 🛠️ Технологии

- Java 8+
- Gson 2.10.1
- Maven

## 🚀 Запуск

1. Клонируйте репозиторий
2. Убедитесь, что в `src/main/resources` есть файлы: `writers.json`, `posts.json`, `labels.json` (можно создать пустые)
3. Запустите `Main.java`

## 📂 Файлы

Данные хранятся в:
- `writers.json`
- `posts.json`
- `labels.json`

## 📸 Пример работы
=== Main Menu ===

Writers
Posts
Labels
Exit
Choose: 1
=== Writer Menu ===

Create
List all
Update
Delete
Back
Choose: 1
First name: John
Last name: Doe
Writer created with ID: 1
## 🔗 Ссылки

- [Gson на MVNRepository](https://mvnrepository.com/artifact/com.google.code.gson/gson)