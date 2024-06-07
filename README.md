# Курсовой проект "Разработка веб-сервиса управления оборудованием"
----------------
**Описание**:
Проект "Разработка веб-сервиса управления оборудованием" на Spring Boot направлен на создание инструмента для управления оборудованием в организации для проведения мероприятий. Этот веб-сервис предоставляет пользователю возможность эффективно управлять оборудованием.

**Скриншот рабочего окна приложения**:
![image](https://github.com/Gatnaron/ProgrammingTechnology-microService-Equipment/assets/104280338/994951ec-69bf-47c3-9271-c5c9f41674bd)

## Архитектура

На диаграмме представлены основные классы программы
![image](https://github.com/Gatnaron/ProgrammingTechnology-microService-Equipment/assets/104280338/241402ae-b103-447a-98bf-f78b11dc62c0)

## Зависимости

Зависимости, которые должны быть установлены для работы этого программного обеспечения:
1. Язык программирования: Java
2. Фреймворк: Spring Boot (рекомендуемая версия, совместимая с вашей версией Java)
3. База данных: PostgreSQL

Для клиентской части веб-сервиса на Spring Boot потребуются следующие ресурсы и зависимости:
1. Языки разметки и программирования:
HTML,
CSS,
JavaScript

## Установка

Подробная инструкция по установке:

**Установка, настройка и запуск проекта: Клиентская часть**:
1. Клонирование репозитория
   - Склонируйте репозиторий с клиентской частью проекта с помощью Git (git clone <ссылка_на_репозиторий>)
2. Установка зависимостей:
  - Перейдите в директорию проекта (cd <директория_проекта>)
3. Настройка:
  - Настройте необходимые параметры, такие как адрес сервера API, порт и другие, в файле конфигурации клиентской части, если это необходимо.
4. Запуск:
  - После установки зависимостей запустите клиентскую часть проекта открыв файл index.html.

**Установка, настройка и запуск проекта: Серверная часть**:
1. Установка необходимых инструментов:
  - Убедитесь, что на вашем компьютере установлен Java Development Kit (JDK) версии 8 или выше. Если нет, установите JDK с официального сайта Oracle или OpenJDK.
2. Клонирование репозитория:
  - Склонируйте репозиторий с серверной частью проекта с помощью Git (git clone <ссылка_на_репозиторий>)
3. Настройка базы данных:
  - Убедитесь, что у вас установлена и настроена база данных, указанная в проекте. Создайте базу данных и пользователя с необходимыми правами доступа, если это необходимо.
4. Настройка:
  - Настройте параметры подключения к базе данных в файле application.properties.
5. Сборка и запуск:
  - Перейдите в директорию проекта (cd <директория_проекта>)
  - Соберите проект с помощью Maven (или Gradle, если вы используете его) - mvn clean install
  - После успешной сборки запустите серверную часть проекта: java -jar target/<имя_jar_файла>.jar
После запуска сервер будет доступен по адресу http://localhost:8080 (или другой порт, если вы его изменяли).

Теперь клиентская и серверная части вашего проекта должны быть успешно установлены, настроены и запущены.

## Описание API

Описание API микросервиса «Управление оборудованием»

Основная информация 
|    Описание   |    Значение   |
| ------------- | ------------- |
| Роль в системе  | Микросервис для управления оборудованием в системе.  |
| Задача  | Обеспечить хранение информации о оборудованиях. Обеспечить возможность назначить оборудование на мероприятие. |
| Сценарий  | Администратор может создавать, просматривать, редактировать, менять статус и удалять оборудование. Могут назначать оборудования на определенные мероприятия. |
