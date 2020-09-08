# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class Intent(models.Model):
    userid = models.ForeignKey('User', models.DO_NOTHING, db_column='userId', blank=True, null=True)  # Field name made lowercase.
    itemid = models.ForeignKey('Item', models.DO_NOTHING, db_column='itemId', blank=True, null=True)  # Field name made lowercase.
    type = models.IntegerField(blank=True, null=True)
    indentid = models.CharField(db_column='indentId', max_length=255, blank=True, null=True)  # Field name made lowercase.

    def __str__(self):
        return self.indentid

    class Meta:
        managed = True
        db_table = 'indent'


class Item(models.Model):
    name = models.CharField(max_length=255, blank=True, null=True)
    type = models.CharField(max_length=255, blank=True, null=True)
    imageurl = models.CharField(db_column='imageUrl', max_length=255, blank=True, null=True)  # Field name made lowercase.
    originalcost = models.IntegerField(db_column='originalCost', blank=True, null=True)  # Field name made lowercase.
    currentcost = models.IntegerField(db_column='currentCost', blank=True, null=True)  # Field name made lowercase.
    detail = models.TextField(blank=True, null=True)
    location = models.CharField(max_length=255, blank=True, null=True)

    def __str__(self):
        return self.name

    class Meta:
        managed = True
        db_table = 'item'


class Token(models.Model):
    userid = models.ForeignKey('User', models.DO_NOTHING, db_column='userId', blank=True, null=True)  # Field name made lowercase.
    content = models.CharField(max_length=255, blank=True, null=True)
    createtime = models.IntegerField(db_column='createTime', blank=True, null=True)  # Field name made lowercase.

    def __str__(self):
        return self.userid.username

    class Meta:
        managed = True
        db_table = 'token'


class User(models.Model):
    username = models.CharField(max_length=255, blank=True, null=True)
    password = models.CharField(max_length=255, blank=True, null=True)
    email = models.CharField(max_length=255, blank=True, null=True)
    phonenumber = models.CharField(db_column='phoneNumber', max_length=255, blank=True, null=True)  # Field name made lowercase.
    avatar = models.CharField(max_length=255, blank=True, null=True)

    def __str__(self):
        return self.username

    class Meta:
        managed = True
        db_table = 'user'


class Vercode(models.Model):
    content = models.CharField(max_length=6, blank=True, null=True)
    email = models.CharField(max_length=255, blank=True, null=True)
    createtime = models.IntegerField(db_column='createTime', blank=True, null=True)  # Field name made lowercase.

    def __str__(self):
        return self.email
        
    class Meta:
        managed = True
        db_table = 'vercode'

class FeedBack(models.Model):
    title = models.CharField(max_length=255, blank=True, null=True)
    content = models.TextField()
    user = models.ForeignKey(User, on_delete=models.DO_NOTHING, db_column="userId")

    def __str__(self):
        return self.user.username + "-" + self.title
    
    class Meta:
        db_table = "feedback"

class Message(models.Model):
    # id = models.IntegerField(primary_key=True, auto_created=True)
    title = models.CharField(max_length=255, blank=True, null=True)
    content = models.TextField()
    type1 = models.IntegerField(db_column="type", default=0)
    user = models.ForeignKey(User, models.DO_NOTHING, db_column="userId")
    from_user = models.ForeignKey(User, models.DO_NOTHING, db_column="fromId", related_name="fromUser")

    def __str__(self):
        return self.user.username + "-" + self.title

    class Meta:
        db_table = "message"

class PasswordWrong(models.Model):
    user = models.ForeignKey(User, on_delete=models.DO_NOTHING, db_column="user")
    wrongTimes = models.IntegerField()
    updateTime = models.IntegerField()

    def __str__(self):
        return self.user.username
    
    class Meta:
        db_table = "passwordWrong"

class Comment(models.Model):
    user = models.ForeignKey(User, on_delete=models.DO_NOTHING, db_column="user")
    content = models.TextField()
    createTime = models.IntegerField()
    item = models.ForeignKey(Item, on_delete=models.DO_NOTHING, db_column="item")

    def __str__(self):
        return self.user.username + "-" + self.item.name
    
    class Meta:
        db_table = "comment"
    
class Favour(models.Model):
    user = models.ForeignKey(User, on_delete=models.DO_NOTHING, db_column="user")
    item = models.ForeignKey(Item, on_delete=models.DO_NOTHING, db_column="item")
    createTime = models.IntegerField(db_column="create_time")

    def __str__(self):
        return self.user.username + "-" + self.item.name
    
    class Meta:
        db_table = "love"