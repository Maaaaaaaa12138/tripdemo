from django.contrib import admin
from .models import *
# Register your models here.

admin.site.register(Intent)
admin.site.register(Item)
admin.site.register(User)
admin.site.register(Vercode)
admin.site.register(Token)
admin.site.register(FeedBack)
admin.site.register(Message)
admin.site.register(PasswordWrong)
admin.site.register(Comment)