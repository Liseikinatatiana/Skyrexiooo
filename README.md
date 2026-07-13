# 📊 Автотесты для торговой платформы (Java + Selenide + TestNG)

Проект по автоматизации тестирования веб-платформы (торговый терминал / криптобиржа).  
Написан на **Selenide** — современном фреймворке для UI-тестирования с встроенными ожиданиями и лаконичным API.

---

## 📌 Что покрыто тестами

- ✅ Авторизация (LoginPage)
- ✅ Регистрация (RegisterPage)
- ✅ Восстановление пароля (RecoverPasswordPage)
- ✅ Работа с торговым терминалом (TradingTerminalPage)
- ✅ Подтверждение действий (ConfirmPage)

---

## 🛠️ Стек технологий

| Компонент | Технология |
| :--- | :--- |
| **Язык** | Java 17 |
| **Тестовый фреймворк** | TestNG |
| **UI-тестирование** | Selenide |
| **API-тестирование** | REST Assured (в папке api) |
| **Сборка** | Maven |
| **Отчёты** | Allure |
| **Система контроля версий** | Git, GitHub |

---

## 📁 Структура проекта
src/test/java/
├── pages/           # Page Object'ы
│   ├── LoginPage
│   ├── RegisterPage
│   ├── RecoverPasswordPage
│   ├── TradingTerminalPage
│   └── ConfirmPage
├── tests/           # UI-тесты
│   ├── LoginTest
│   ├── RegisterTest
│   └── TradingTerminalTest
└── api/             # API-тесты
├── client/
├── config/
├── dto/
└── tests/

```

---

## 🚀 Как запустить

### 1. Клонировать репозиторий
```bash
git clone https://github.com/ваш-логин/Skyrexiooo.git
cd Skyrexiooo
```

2. Установить зависимости

```bash
mvn clean install
```

3. Запустить все тесты

```bash
mvn clean test
```

4. Открыть Allure-отчёт

```bash
mvn allure:serve
```

---

🧪 Пример UI-теста на Selenide

```java
@Test
public void loginTest() {
    open("/login");
    $("#email").setValue("user@mail.com");
    $("#password").setValue("Qwerty123!");
    $("[type='submit']").click();
    $(".dashboard-title").shouldHave(text("Dashboard"));
}
```

---

🔗 Связанные проекты

· UI-автотесты (Sogaz): github.com/Liseikinatatiana/Sogaz
· UI-автотесты (Saucedemo): github.com/Liseikinatatiana/Saucedemo

---

👤 Обо мне

Имя: Татьяна
Стажировка: Автоматизация тестирования (Java + Selenide + TestNG)

🐙 GitHub: github.com/Liseikinatatiana

---

📜 Лицензия

Учебный проект. Все права принадлежат их владельцам.
