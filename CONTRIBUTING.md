# Руководство по участию в проекте MRSU-Assistant

Спасибо за ваш интерес к проекту! Мы рады любому вкладу.

## Как начать

### Необходимые инструменты

*   [Git](https://git-scm.com/)
*   [Java JDK 21+](https://www.oracle.com/java/technologies/downloads/#jdk21-windows)
*   [Apache Maven](https://maven.apache.org/)
*   [Node.js](https://nodejs.org/) (включая npm)

### Настройка проекта

1.  Склонируйте репозиторий:
    ```shell
    git clone git@github.com:MRSU-FAQ/MRSU-Assistant.git
    cd MRSU-Assistant
    ```

2.  **Backend (Java/Spring Boot)**:
    Перейдите в директорию `backend` и соберите проект с помощью Maven.
    ```shell
    cd backend
    mvn clean install
    ```
    Запустить приложение можно командой:
    ```shell
    mvn spring-boot:run
    ```
    Сервер будет доступен по адресу `http://localhost:8080`.

3.  **Frontend (React)**:
    Откройте новый терминал, перейдите в директорию `frontend`, установите зависимости и запустите dev-сервер.
    ```shell
    cd frontend
    npm install
    npm run dev
    ```
    Клиент будет доступен по адресу `http://localhost:5173`.

## Управление задачами

Все задачи по проекту ведутся в **YouTrack**. Перед началом работы убедитесь, что для вашей задачи создан соответствующий тикет.

## Процесс разработки

Основная ветка для разработки — `develop`. Ветка `main` содержит только стабильный, рабочий код и не используется для повседневной разработки.

1.  **Создайте ветку**: Всегда создавайте новую ветку для вашей задачи от актуальной версии `develop`.
    ```shell
    # Имя ветки должно содержать номер задачи из YouTrack
    # Пример: feature/MA-15-add-login-page, fix/MA-21-fix-button-bug
    git checkout -b feature/MA-15-add-login-page
    ```

2.  **Вносите изменения**: Пишите код, следуя принятому стилю.

3.  **Делайте коммиты**: Используйте понятные и описательные сообщения для коммитов.
    ```shell
    git add .
    git commit -m "feat(auth): Add login page structure (MA-15)"
    ```

4.  **Отправьте изменения**: Загрузите вашу ветку в удаленный репозиторий.
    ```shell
    git push -u origin feature/MA-15-add-login-page
    ```

5.  **Создайте Pull Request**: Откройте Pull Request из вашей ветки в `develop`. Опишите сделанные изменения и привяжите тикет из YouTrack.

## Стиль кода

Мы используем `EditorConfig` для поддержания единого стиля форматирования. Убедитесь, что ваш редактор кода поддерживает его.

*   **Java**: Google Java Style Guide.
*   **JavaScript/React**: Airbnb JavaScript Style Guide.
*   **Форматирование**: Настроенные линтеры (`ESLint`) и `.editorconfig` помогут вам придерживаться стиля.
