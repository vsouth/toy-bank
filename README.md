# Toy Bank
```
Прогрев OuterSystem{name='Outer system 1', amount=500, timeout=7000ms}
Прогрев OuterSystem{name='Outer system 3', amount=300, timeout=9000ms}
Прогрев OuterSystem{name='Outer system 2', amount=200, timeout=6000ms}
Бэк система: Заявка Request{clientThreadName='Outer system 2', amount=200, requestType=REPAYMENT}: Баланс банка: 200
Бэк система: Заявка Request{clientThreadName='Outer system 1', amount=500, requestType=REPAYMENT}: Баланс банка: 700
Бэк система: Заявка Request{clientThreadName='Outer system 3', amount=300, requestType=REPAYMENT}: Баланс банка: 1000
Баланс банка: 1000
Клиент 1: Заявка Request{clientThreadName='Клиент 1', amount=500, requestType=CREDIT} отправлена в банк
Фронтальная система приняла заявку - Клиент 1
Клиент 2: Заявка Request{clientThreadName='Клиент 2', amount=1000, requestType=REPAYMENT} отправлена в банк
Фронтальная система приняла заявку - Клиент 2
Фронтальная система передала заявку - Клиент 1
Клиент 4: Заявка Request{clientThreadName='Клиент 4', amount=1500, requestType=REPAYMENT} отправлена в банк
Фронтальная система приняла заявку - Клиент 4
Клиент 3: Заявка Request{clientThreadName='Клиент 3', amount=2000, requestType=CREDIT} отправлена в банк
Фронтальная система приняла заявку - Клиент 3
Обработчик №1: Получена заявка на обработку по клиенту - Клиент 1
Фронтальная система передала заявку - Клиент 2
Обработчик №2: Получена заявка на обработку по клиенту - Клиент 2
Бэк система: Заявка Request{clientThreadName='Клиент 2', amount=1000, requestType=REPAYMENT}: Баланс банка: 1500
Фронтальная система передала заявку - Клиент 4
Обработчик №2: Получена заявка на обработку по клиенту - Клиент 4
Бэк система: Заявка Request{clientThreadName='Клиент 4', amount=1500, requestType=REPAYMENT}: Баланс банка: 3000
Фронтальная система передала заявку - Клиент 3
Обработчик №2: Получена заявка на обработку по клиенту - Клиент 3
Бэк система: Заявка Request{clientThreadName='Клиент 3', amount=2000, requestType=CREDIT}: Баланс банка: 1000
Бэк система: Заявка Request{clientThreadName='Клиент 1', amount=500, requestType=CREDIT}: Баланс банка: 500
Итоговый баланс банка: 1000
```