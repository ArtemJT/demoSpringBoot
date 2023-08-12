## **DEMO PROJECT**

### Основне завдання програми:

- При створенні нового замовлення покупцем воно отримує статус "В роботі" та зберігається в базу даних.
![img_1.png](readme_images/img_1.png)

- Можна отримати з бази даних всі замовлення або одне, а також всі, що мають тільки статус "В роботі".
![img_2.png](readme_images/img_2.png)

- Статус замовлення може бути змінено на "Закінчене" або "Відхилено".
![img.png](readme_images/img_3.png)

- Менеджер може отримати всі свої замовлення або тільки ті, що мають статус "В роботі".
![img.png](readme_images/img_4.png)

- Покупець може отримати свої замовлення.
![img.png](readme_images/img_5.png)


### Додатковий опис:

- Для логування використав АОП. Всі класи з пакету 'web' логуються на рівні 'info', а всі класи 'service' - на рівні 'debug'.
![img.png](readme_images/img_6.png)

- Додав обробники для формування повідомлення при появі виключення.
![img_1.png](readme_images/img_7.png)