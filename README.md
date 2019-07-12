
## MyHashMap

Custom hash map implementation using linked-lists for collision support

### methods:
put(), get(), size(), loadFactor(), capacity()

### Capacity Computation:
initial capacity is set to 997, largest prime number < 1000. When load factor >= .7, capacity is recomputed to the next 
largest prime number < capacity * 2. The recompute requires O(1) space and O(sqr(capacity*2)) time, capacity recompute will only occur
in put() method.