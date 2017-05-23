phoneBook = {}

phoneBook["John"] = 938477566
phoneBook["Jack"] = 938377264
phoneBook["Jill"] = 947662781

print(phoneBook)

phonebook = {
    "John" : 938477566,
    "Jack" : 938377264,
    "Jill" : 947662781
}
print(phonebook)

for k, v in phonebook.items():
    print("Phone number of %s is %d" % (k, v))


print(phonebook)
del phonebook["John"]
print(phonebook)

phonebook = {
    "John" : 938477566,
    "Jack" : 938377264,
    "Jill" : 947662781
}

phonebook.pop("John")
print(phonebook)
