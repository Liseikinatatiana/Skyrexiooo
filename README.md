# 📊 Автотесты для торговой платформы (Java + Selenium + TestNG)

Проект по автоматизации тестирования веб-платформы (торговый терминал / криптобиржа).  
Реализованы UI-тесты, Allure-отчёты, Page Object Model.

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
| **UI-тестирование** | Selenium WebDriver |
| **API-тестирование** | REST Assured (в папке api) |
| **Паттерн** | Page Object Model |
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

3. Запустить UI-тесты

```bash
mvn clean test -Dtest="*Test"
```

4. Запустить API-тесты

```bash
mvn clean test -Dtest="api.tests.*"
```

5. Открыть Allure-отчёт

```bash
mvn allure:serve
```

---

🧪 Пример UI-теста

```java
@Test
public void loginTest() {
    loginPage.open();
    loginPage.enterEmail("user@mail.com");
    loginPage.enterPassword("Qwerty123!");
    loginPage.clickLogin();
    Assert.assertEquals(tradingTerminalPage.getTitle(), "Dashboard");
}
```

---
👤 Обо мне

Имя: Татьяна
Стажировка: Автоматизация тестирования (Java + Selenium + TestNG)

🐙 GitHub: github.com/Liseikinatatiana

---

📜 Лицензия

Учебный проект. Все права принадлежат их владельцам.
