# Playground Repository
This repository is for my personal experimentation.  
**DO NOT TRY TO CLONE AND RUN**


## Problem
### Python Interview Questions
Mr. Zac is the new manager of the Wolff International Bank. He has requested that a new banking system be deployed.  
You have been assigned the task of developing a new banking system software for the client.

With reference to Python Object Oriented Programming:

1. Design a core banking system software that meets Mr. Zac's requirements. Create the following classes for the bank:
  * Bank Class
  * eServices Class
  
2. **Bank Class**  
Using functions, your software should perform basic banking functions like:
  * Allowing tellers to view customer details (customer name, customer account number, customer phone number, customer address)
  * The banking system should be able to accept debit & credit transactions on any account.
  
3. Create a function that writes each account created to a file. The prefix of the profile should be the account number and the extension, **"wolff"**. Eg. **11231001.wolff**

4. **eServices Class**  
Create a class called eServices then create an eService Type (e.g. Mobile Banking). Every customer should be able to access any eService Type.

## Solution
### Create project structure
This project uses Django
```shell
$ pip install django
```
Create Django Project
```shell
$ django-admin startproject banking_system
```
Create app in `banking_system`
```shell
$ cd banking_system
$ django-admin startapp bank
```
Run
```shell
$ python manage.py runserver
```
visit <http://localhost:8000/>

### Add some code
Modify file; replace content
`bank/views.py`
```python
from django.http import HttpResponse


# The welcome page
def index(request):
    return HttpResponse("Hello, welcome to Wolff International Bank.")
```
Create file
`bank/urls.py`
```python
from django.conf.urls import url

from . import views

urlpatterns = [
    url(r'^$', views.index, name='index'),
]
```
Add import `from django.conf.urls import include, url` in `banking_system/urls.py` and add line
```python
urlpatterns = [
    url(r'^index/', include('bank.urls')),
    ...
]
```
In `banking_system/settings.py` register `bank` app in `INSTALLED_APPS`
```python
INSTALLED_APPS = [
    'django.contrib.admin',
    ...
    'bank.apps.BankConfig',
]
```
Modify file
`bank/models.py`
```python
from django.db import models


class CardType(models.Model):
    type = models.CharField(max_length=100)


class Card(models.Model):
    card_type = models.ForeignKey(CardType, on_delete=models.CASCADE)
    card_number = models.IntegerField


class Address(models.Model):
    street = models.CharField(max_length=100)
    city = models.CharField(max_length=100)
    zip = models.CharField(max_length=8)


class Customer(models.Model):
    name = models.CharField(max_length=60)
    age = models.IntegerField(default=18)
    account_number = models.CharField("account number", max_length=100)
    phone = models.CharField("phone number", max_length=100)
    address = models.ForeignKey(Address, on_delete=models.CASCADE)
    card = models.ForeignKey(Card, on_delete=models.CASCADE)
    created = models.DateTimeField("date created")


class TransactionType(models.Model):
    type = models.CharField(max_length=100)


class Transaction(models.Model):
    type = models.ForeignKey(TransactionType, on_delete=models.CASCADE)
    account = models.ForeignKey(Customer, on_delete=models.CASCADE)
```

### Run migrations
Generate migration file
```shell
$ python manage.py makemigrations bank
```
To see the generated sql:
```shell
$ python manage.py sqlmigrate bank 0001
```
Now, run migrate to create those model tables in your database:
```shell
$ python manage.py migrate
```

*This is work in progress*
