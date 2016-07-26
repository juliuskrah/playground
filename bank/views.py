from django.http import HttpResponse


# The welcome page
def index(request):
    return HttpResponse("Hello, welcome to Wolff International Bank.")
