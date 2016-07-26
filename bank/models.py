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