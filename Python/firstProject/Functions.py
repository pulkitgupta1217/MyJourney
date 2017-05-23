def my_function():
    print("hello from myFunction")


def my_function_with_args(username, greeting):
    print("hello, %s , from my function!, i wish you %s" % (username, greeting))


def add(a, b):
    return a + b


my_function()

my_function_with_args("pulkit", "sahhh dude")

print(add(2, 3))

print("exercise")


# Modify this function to return a list of strings as defined above
def list_benefits():
    return ["More organized code", "More readable code", "Easier code reuse",
            "Allowing programmers to share and connect code together"]


# Modify this function to concatenate to each benefit - " is a benefit of functions!"
def build_sentence(benefit):
    return "%s is a benefit of functions!" % benefit


def name_the_benefits_of_functions():
    list_of_benefits = list_benefits()
    for benefit in list_of_benefits:
        print(build_sentence(benefit))


name_the_benefits_of_functions()
