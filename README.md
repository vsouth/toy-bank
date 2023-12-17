# Toy Bank
```
Клиент 1: Заявка Request{clientThreadName='Клиент 1', amount=500, requestType=CREDIT} отправлена в банк
Клиент 4: Заявка Request{clientThreadName='Клиент 4', amount=1500, requestType=REPAYMENT} отправлена в банк
Клиент 3: Заявка Request{clientThreadName='Клиент 3', amount=2000, requestType=CREDIT} отправлена в банк
Клиент 2: Заявка Request{clientThreadName='Клиент 2', amount=1000, requestType=REPAYMENT} отправлена в банк
Фронтальная система приняла заявку - Клиент 1
Фронтальная система передала заявку - Клиент 1
Обработчик №1: Получена заявка на обработку по клиенту - Клиент 1
Фронтальная система приняла заявку - Клиент 2
Фронтальная система приняла заявку - Клиент 3
Фронтальная система передала заявку - Клиент 2
Обработчик №2: Получена заявка на обработку по клиенту - Клиент 2
Фронтальная система приняла заявку - Клиент 4
Бэк система: Заявка Request{clientThreadName='Клиент 1', amount=500, requestType=CREDIT}: Баланс банка: 500
Фронтальная система передала заявку - Клиент 3
Обработчик №1: Получена заявка на обработку по клиенту - Клиент 3
Бэк система: Заявка Request{clientThreadName='Клиент 2', amount=1000, requestType=REPAYMENT}: Баланс банка: 1500
Фронтальная система передала заявку - Клиент 4
Обработчик №2: Получена заявка на обработку по клиенту - Клиент 4
Бэк система: Заявка Request{clientThreadName='Клиент 4', amount=1500, requestType=REPAYMENT}: Баланс банка: 3000
Бэк система: Заявка Request{clientThreadName='Клиент 3', amount=2000, requestType=CREDIT}: Баланс банка: 1000

Process finished with exit code 130

```