# Translator

## Назначение
- API для перевода текста для выбранной пары языков, с использованием стороннего сервиса (Yandex)

## Требования для запуска
- Java 17;
- Наличие Maven;
- Установленный PostgreSQL;
- Иметь платёжный аккаунт в [Yandex Cloud](https://yandex.cloud);
- Облако должно быть [привязано к платёжному аккаунту](https://yandex.cloud/ru/docs/billing/operations/pin-cloud) (иначе не будет доступа к каталогам облака);
- [Получить OAuth-токен](https://yandex.cloud/ru/docs/iam/concepts/authorization/oauth-token) и с помощью него [получить IAM-токен](https://yandex.cloud/ru/docs/iam/operations/iam-token/create);
- [Получить идентификатор любого каталога](https://yandex.cloud/ru/docs/resource-manager/operations/folder/get-id), на который у вашего аккаунта есть роль `ai.translate.user` или выше;

## Запуск
1. В директории проекта `src/main/resources/`:
   - изменить название файла `application.properties.dist` на `application.properties`
2. Postgres:
   - запустить
   - создать новую базу данных
   - создать новые таблицы с помощью SQL-скрипта: `create-db-tables.sql`
3. В `application.properties`:
    - вписать IAM-токен в `yandex.token.iam=`
    - вписать идентификатор каталога в `yandex.folder-id=`
    - для работы БД вписать: 
      - URL-путь к базе данных в `db.url=`
      - username в `db.username=`
      - password - в `db.password=`  

**При запуске из среды разработки:**

4. В классе `src/main/java/ru/tbank/shkalin/translator/App.java` запустить метод `main(String[] args)`

## Использование
В HTTP-клиенте (например, Postman):
- Отправить **POST**-запрос, такой, что:
  - URL: http://localhost:8080/translator/api/v1 (либо хост, порт и контекст (по-умолчанию контекст: "/translator") другие, если у вас это настроено иначе. порт и контекст прописаны в `application.properties`)
  - Тело запроса - JSON, вида:
```json
{
   "sourceLang": "en",
   "targetLang": "ru",
   "sourceString": "Hello world, this is my first program"
}
```
- Ответ придёт в формате JSON, вида:
```json
{
    "text": "Привет, мир, это моя первая программа"
}
```
