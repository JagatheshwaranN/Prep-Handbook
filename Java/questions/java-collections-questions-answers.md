# Java - Collections Questions & Answers

---

## 1. What are collections in Java?

Collections in Java are **frameworks that provide architecture to store and manipulate a group of objects**. They are used to store, retrieve, manipulate, and communicate aggregate data efficiently.

---

## 2. What is the difference between `Collection` and `Collections` in Java?

| Feature | `Collection` | `Collections` |
|---------|-------------|--------------|
| **Type** | Interface | Utility class |
| **Purpose** | Represents a single group of objects. | Contains static utility methods for operating on collections. |
| **Example** | `List`, `Set`, `Queue` all extend `Collection`. | `Collections.sort()`, `Collections.shuffle()`, `Collections.synchronizedList()` |

---

## 3. What are the main interfaces in the Java Collections Framework?

| Interface | Description |
|-----------|-------------|
| **`List`** | Ordered collection — allows duplicate elements. Elements accessible by index. |
| **`Set`** | Collection that does **not** allow duplicate elements. |
| **`Map`** | Key-value pair collection — keys are unique, values can repeat. |
| **`Queue`** | Holds elements before processing — typically FIFO (First-In-First-Out). |
| **`Deque`** | Double-ended queue — allows insertion/removal from both ends. |

```
Collection (interface)
├── List → ArrayList, LinkedList, Vector
├── Set  → HashSet, LinkedHashSet, TreeSet
└── Queue → LinkedList, PriorityQueue, ArrayDeque

Map (separate hierarchy)
└── HashMap, LinkedHashMap, TreeMap, Hashtable
```

---

## 4. How does `HashMap` work in Java?

`HashMap` uses a **hash table** to store key-value pairs:

1. The **hash code** of the key is computed.
2. The hash code is mapped to a **bucket** (array index).
3. The key-value pair is stored in that bucket.
4. In case of **hash collisions** (multiple keys → same bucket), a **linked list** is used (Java 7) or a **balanced tree** (Java 8+, when bucket size > 8).

```java
Map<String, Integer> map = new HashMap<>();
map.put("Alice", 30);
map.put("Bob", 25);

int age = map.get("Alice"); // Computes hash("Alice") → finds bucket → returns 30
```

---

## 5. What is the difference between `HashMap` and `Hashtable`?

| Feature | `HashMap` | `Hashtable` |
|---------|-----------|-------------|
| **Synchronization** | ❌ Not synchronized — not thread-safe. | ✅ Synchronized — thread-safe. |
| **Null keys/values** | ✅ Allows one `null` key, multiple `null` values. | ❌ No `null` keys or values — throws `NullPointerException`. |
| **Performance** | ✅ Faster — no sync overhead. | Slower due to synchronization. |
| **Iterator** | Fail-fast — throws `ConcurrentModificationException`. | Fail-safe Enumeration — no exception on modification. |
| **Modern alternative** | `HashMap` + external sync, or `ConcurrentHashMap`. | Prefer `ConcurrentHashMap` for thread-safe use. |

---

## 6. What is the purpose of `Comparable` and `Comparator` interfaces?

| Feature | `Comparable` | `Comparator` |
|---------|-------------|-------------|
| **Package** | `java.lang` | `java.util` |
| **Purpose** | Defines **natural ordering** within a class. | Defines **custom ordering** outside the class. |
| **Method** | `compareTo(T other)` | `compare(T o1, T o2)` |
| **Implementation** | Class implements it — one natural order. | Separate class — multiple ordering strategies. |
| **Use when** | You control the class and want a default sort. | You need multiple orderings or can't modify the class. |

```java
// Comparable — natural ordering
class Student implements Comparable<Student> {
    String name;
    int grade;

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.grade, other.grade); // Sort by grade
    }
}

// Comparator — custom ordering
Comparator<Student> byName = (s1, s2) -> s1.name.compareTo(s2.name);
students.sort(byName); // Sort by name
```

---

## 7. How can you synchronize a `List` in Java?

Use `Collections.synchronizedList()` to wrap any list with synchronized access.

```java
List<String> list = new ArrayList<>();
List<String> syncList = Collections.synchronizedList(list);

// Must synchronize manually when iterating
synchronized (syncList) {
    for (String s : syncList) {
        System.out.println(s);
    }
}

// Modern alternative — thread-safe
List<String> concurrentList = new CopyOnWriteArrayList<>();
```

---

## 8. How do you iterate over elements in a `List`?

```java
List<String> list = List.of("A", "B", "C");

// Method 1 — for-each loop
for (String item : list) {
    System.out.println(item);
}

// Method 2 — traditional for loop (with index)
for (int i = 0; i < list.size(); i++) {
    System.out.println(list.get(i));
}

// Method 3 — Iterator
Iterator<String> it = list.iterator();
while (it.hasNext()) {
    System.out.println(it.next());
}

// Method 4 — Stream API (Java 8+)
list.stream().forEach(System.out::println);

// Method 5 — forEach method
list.forEach(System.out::println);
```

---

## 9. What is the difference between fail-fast and fail-safe iterators?

| Feature | Fail-fast | Fail-safe |
|---------|-----------|-----------|
| **Behavior** | Throws `ConcurrentModificationException` if collection is modified during iteration. | Allows modification during iteration — no exception. |
| **Works on** | Original collection. | A copy of the collection. |
| **Examples** | `ArrayList`, `HashMap` iterators. | `CopyOnWriteArrayList`, `ConcurrentHashMap` iterators. |
| **Memory** | Lower — no copy needed. | Higher — uses a snapshot/copy. |

```java
// Fail-fast — throws ConcurrentModificationException
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
for (String s : list) {
    list.add("D"); // Throws ConcurrentModificationException!
}

// Fail-safe — no exception (iterates over snapshot)
List<String> safeList = new CopyOnWriteArrayList<>(Arrays.asList("A", "B", "C"));
for (String s : safeList) {
    safeList.add("D"); // Safe — iterates over original snapshot
}
```

---

## 10. What are the benefits of using the Collections Framework?

| Benefit | Description |
|---------|-------------|
| **Consistency** | Provides a uniform way to work with various collections. |
| **Reusability** | Common operations (sorting, searching, shuffling) are pre-implemented. |
| **Extensibility** | Designed to allow custom collection implementations. |
| **Interoperability** | Collections can be converted between types easily. |
| **Performance** | Optimized implementations for common data structures. |

---

## 11. What is the difference between `ArrayList` and `LinkedList`?

| Feature | `ArrayList` | `LinkedList` |
|---------|------------|--------------|
| **Internal structure** | Dynamic array. | Doubly-linked list. |
| **Random access** | ✅ Fast — O(1) by index. | ❌ Slow — O(n) must traverse. |
| **Insertion/Deletion (middle)** | ❌ Slow — O(n) elements must shift. | ✅ Fast — O(1) update pointers. |
| **Memory** | Less — stores only elements. | More — stores elements + prev/next pointers. |
| **Use case** | Read-heavy, random access. | Write-heavy, frequent insert/delete. |

---

## 12. What is the difference between `HashSet` and `TreeSet`?

| Feature | `HashSet` | `TreeSet` |
|---------|-----------|-----------|
| **Internal structure** | Hash table. | Red-black tree. |
| **Order** | No guaranteed order. | ✅ Sorted (natural or custom). |
| **Performance** | ✅ O(1) for add, remove, contains. | O(log n) for add, remove, contains. |
| **Null values** | ✅ Allows one `null`. | ❌ No `null` — throws `NullPointerException`. |
| **Use case** | Fast access, order doesn't matter. | Need sorted order. |

---

## 13. What is the difference between `HashSet` and `LinkedHashSet`?

| Feature | `HashSet` | `LinkedHashSet` |
|---------|-----------|----------------|
| **Order** | ❌ No guaranteed order. | ✅ Preserves insertion order. |
| **Internal structure** | Hash table only. | Hash table + doubly-linked list. |
| **Performance** | ✅ Faster — no ordering overhead. | Slightly slower — maintains linked list. |
| **Memory** | Less memory. | More memory (stores linked list). |

---

## 14. What is the difference between `List` and `Set`?

| Feature | `List` | `Set` |
|---------|--------|-------|
| **Duplicates** | ✅ Allowed. | ❌ Not allowed. |
| **Order** | ✅ Maintains insertion order. | Depends on implementation. |
| **Index access** | ✅ Yes — `get(index)`. | ❌ No index access. |
| **Examples** | `ArrayList`, `LinkedList` | `HashSet`, `TreeSet`, `LinkedHashSet` |

---

## 15. What is the purpose of `Collections.unmodifiableList()`?

It returns an **unmodifiable view** of the list — attempts to modify it throw `UnsupportedOperationException`.

```java
List<String> original = new ArrayList<>(Arrays.asList("Apple", "Banana"));
List<String> readOnly = Collections.unmodifiableList(original);

readOnly.get(0);    // ✅ Apple — reads are allowed
// readOnly.add("Cherry"); // ❌ UnsupportedOperationException

// Note: original can still be modified — readOnly reflects the change
original.add("Cherry");
System.out.println(readOnly); // [Apple, Banana, Cherry]
```

---

## 16. What is the purpose of the `Queue` interface?

`Queue` holds elements **prior to processing** using **FIFO (First-In-First-Out)** ordering.

```java
Queue<String> queue = new LinkedList<>();
queue.offer("One");   // Add to tail
queue.offer("Two");
queue.offer("Three");

String head = queue.peek();  // "One" — view head without removing
String removed = queue.poll(); // "One" — retrieve and remove head

System.out.println(queue); // [Two, Three]
```

| Method | Description |
|--------|-------------|
| `offer(e)` | Add element to tail. |
| `poll()` | Retrieve and remove head (returns `null` if empty). |
| `peek()` | View head without removing (returns `null` if empty). |

---

## 17. What is the purpose of the `Deque` interface?

`Deque` (double-ended queue) allows **insertion and removal from both ends**. It extends `Queue`.

```java
Deque<String> deque = new ArrayDeque<>();
deque.addFirst("First"); // Add to front
deque.addLast("Last");   // Add to back
deque.addFirst("New First");

System.out.println(deque); // [New First, First, Last]

deque.removeFirst(); // Remove from front
deque.removeLast();  // Remove from back
```

---

## 18. What is an `Iterator` and how is it used?

An `Iterator` allows **sequential traversal** of a collection without needing to know its internal structure. It provides a uniform way to access elements.

```java
List<String> list = Arrays.asList("A", "B", "C");
Iterator<String> it = list.iterator();

while (it.hasNext()) {
    String element = it.next();
    System.out.println(element);
}

// Safe removal during iteration
List<String> mutable = new ArrayList<>(Arrays.asList("A", "B", "C"));
Iterator<String> it2 = mutable.iterator();
while (it2.hasNext()) {
    if (it2.next().equals("B")) {
        it2.remove(); // Safe — use iterator's remove, not list's
    }
}
```

---

## 19. How can you compare two Sets for equality when order might differ?

Sets don't consider order for equality. Use these approaches:

```java
Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C"));
Set<String> set2 = new TreeSet<>(Arrays.asList("C", "A", "B")); // Different order

// Method 1 — Set.equals() — order doesn't matter
System.out.println(set1.equals(set2)); // true

// Method 2 — containsAll() bidirectionally
boolean equal = set1.containsAll(set2) && set2.containsAll(set1);
System.out.println(equal); // true
```

---

## 20. How can collections be synchronized for thread safety?

```java
// Method 1 — Synchronized wrappers
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
Map<String, Integer> syncMap = Collections.synchronizedMap(new HashMap<>());

// Method 2 — Thread-safe implementations (preferred)
List<String> safeList = new CopyOnWriteArrayList<>();
Map<String, Integer> safeMap = new ConcurrentHashMap<>();
Set<String> safeSet = Collections.newSetFromMap(new ConcurrentHashMap<>());
```

---

## 21. When would you use `HashSet`, `LinkedHashSet`, or `TreeSet`?

| Use Case | Choice |
|----------|--------|
| Order doesn't matter, need fast access. | `HashSet` |
| Need to maintain insertion order. | `LinkedHashSet` |
| Need elements sorted (natural or custom). | `TreeSet` |

---

## 22. How does `ConcurrentHashMap` achieve thread safety vs. `Hashtable`?

| Feature | `Hashtable` / `synchronizedMap` | `ConcurrentHashMap` |
|---------|-------------------------------|---------------------|
| **Locking** | Locks the **entire map** on every operation. | Locks only individual **segments/buckets**. |
| **Concurrency** | One thread at a time. | ✅ Multiple threads can read/write different segments. |
| **Performance** | Poor in high concurrency. | ✅ Much better — minimal contention. |
| **Null keys/values** | `Hashtable`: none. `synchronizedMap`: depends on wrapped map. | ❌ No null keys or values. |

---

## 23. What is the difference between `HashMap` and `TreeMap`?

| Feature | `HashMap` | `TreeMap` |
|---------|-----------|-----------|
| **Order** | No guaranteed order. | ✅ Sorted by key (natural or custom). |
| **Performance** | ✅ O(1) for get/put. | O(log n) for get/put. |
| **Null keys** | ✅ One null key allowed. | ❌ No null keys. |
| **Use case** | Fast access, order doesn't matter. | Need keys sorted. |

---

## 24. How does `HashSet` ensure element uniqueness internally?

`HashSet` internally uses a `HashMap`. When adding an element:
1. The element's **`hashCode()`** is computed → determines the bucket.
2. If the bucket is empty → element is added.
3. If the bucket has elements → **`equals()`** is called to check for duplicates.
4. If `equals()` returns `true` → element is NOT added (duplicate detected).

```java
// Proper hashCode and equals are essential for HashSet to work correctly
class Point {
    int x, y;
    @Override public int hashCode() { return Objects.hash(x, y); }
    @Override public boolean equals(Object o) {
        Point p = (Point) o;
        return x == p.x && y == p.y;
    }
}
```

---

## 25. Differences between `Iterator`, `ListIterator`, and `Enumeration`.

| Feature | `Iterator` | `ListIterator` | `Enumeration` |
|---------|-----------|---------------|---------------|
| **Direction** | Forward only. | ✅ Bidirectional (forward + backward). | Forward only. |
| **Remove** | ✅ `remove()` supported. | ✅ `remove()`, `add()`, `set()` supported. | ❌ Not supported. |
| **Applies to** | All `Collection` types. | `List` only. | Legacy — `Vector`, `Hashtable`. |
| **Modern use** | ✅ Preferred. | ✅ When bidirectional traversal needed. | ❌ Legacy only. |

```java
// ListIterator — bidirectional traversal
List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
ListIterator<String> lit = list.listIterator();

while (lit.hasNext()) System.out.print(lit.next() + " ");    // A B C
while (lit.hasPrevious()) System.out.print(lit.previous() + " "); // C B A
```

---

## Collections — Quick Reference

| Interface | Implementations | Ordered? | Duplicates? | Sorted? |
|-----------|----------------|---------|------------|---------|
| `List` | `ArrayList`, `LinkedList` | ✅ Yes | ✅ Yes | ❌ No |
| `Set` | `HashSet`, `LinkedHashSet`, `TreeSet` | Varies | ❌ No | TreeSet: ✅ |
| `Map` | `HashMap`, `LinkedHashMap`, `TreeMap` | Varies | Keys: ❌ | TreeMap: ✅ |
| `Queue` | `LinkedList`, `PriorityQueue` | FIFO/Priority | ✅ Yes | PriorityQueue: ✅ |
| `Deque` | `ArrayDeque`, `LinkedList` | Both ends | ✅ Yes | ❌ No |

| Class | Thread-safe? | Null Keys/Values? |
|-------|-------------|-----------------|
| `HashMap` | ❌ | One null key, multiple null values |
| `Hashtable` | ✅ | ❌ No nulls |
| `ConcurrentHashMap` | ✅ | ❌ No nulls |
| `ArrayList` | ❌ | ✅ Allows nulls |
| `CopyOnWriteArrayList` | ✅ | ✅ Allows nulls |

---

*Happy Coding! 🚀*