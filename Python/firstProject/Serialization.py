import json
json_string = json.dumps([1, 2, 3, "a", "b", "c"])
print(json.loads(json_string))

import pickle
pickle_string = pickle.dumps([1, 2, 3, 'a', 'c'])
print(pickle.loads(pickle_string))