# Дипломный проект профессии «Тестировщик ПО»
В рамках дипломного проекта требовалось автоматизировать тестирование комплексного сервиса покупки тура, взаимодействующего с СУБД и API Банка.

База данных хранит информацию о заказах, платежах, статусах карт, способах оплаты.

Для покупки тура есть два способа: с помощью карты и в кредит. В приложении используются два отдельных сервиса оплаты: Payment Gate и Credit Gate.

[Ссылка на Дипломное задание](https://github.com/AlekO1967/Diploma_Marakesh).

## Тестовая документация
1. [План тестирования](https://github.com/AlekO1967/Diploma_Marakesh/blob/master/documentation/Plan.md);
1. [Отчёт по итогам тестирования](https://github.com/AlekO1967/Diploma_Marrakesh/blob/master/documentation/Repotr.md);
1. [Отчет по итогам автоматизации](https://github.com/AlekO1967/Diploma_Marrakesh/blob/master/documentation/Summary.md)

## Запуск приложения
### Подготовительный этап
1. Установить и запустить IntelliJ IDEA;
1. Установить и запустить Docker Desktop;
1. Скопировать репозиторий с Github [по ссылке](https://github.com/AlekO1967/Diploma_Marakesh).
1. Открыть проект в IntelliJ IDEA.

### Запуск тестового приложения
1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой:
   ```
   docker-compose up
   ```
1. В новой вкладке терминала запустить тестируемое приложение:
    * Для MySQL:
   ```
   java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar"
   ```
    * Для PostgreSQL:
   ```
   java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar"
   ```
   .
1. Убедиться в готовности системы. Приложение должно быть доступно по адресу:
```
http://localhost:8080/
```

### Запуск тестов
В новой вкладке терминала запустить тесты:
1. Для MySQL:
   ```
   ./gradlew clean test "-Ddatasource.url=jdbc:mysql://localhost:3306/app"
   ```
1. Для PostgreSQL:
   ```
   ./gradlew clean test "-Ddatasource.url=jdbc:postgresql://localhost:5432/app"
   ```
### Перезапуск приложения, тестов и/или отчёта
Для перезапуска приложения, тестов и/или отчёта (например, для использования другой БД) необходимо выполнить остановку их работы, нажав в соответствующих вкладках терминала Ctrl+С

### Формирование отчёта о тестировании
В новой вкладке терминала ввести команды:
```
./gradlew allurereport
./gradlew allureServe
```
Отчёт откроется в браузере автоматически.

### Завершение работы приложения
1. Для завершения работы allureServe выполнить команду:

`Ctrl + С` далее выбрать `Y`

2. Для завершения работы контейнеров выполнить команду:

`Ctrl + С` далее `docker-compose down`
