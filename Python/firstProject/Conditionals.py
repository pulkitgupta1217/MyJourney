x = 2
print(x == 2)
print(x == 3)
print(x < 3)

name = "John"
if name in ["John", "Rick"]:
    print("your name is either john or rick")

x = 5
if x == 2:
    print("x is 2")
elif x == 3:
    print("x is 3")
else:
    print("x is neither 2 nor 3")

"""== vs is keyword
== checks the values of variables within objects
whereas "is" checks aliasing"""

x = [1, 2, 3]
y = [1, 2, 3]

print(x == y)
print(x is y)