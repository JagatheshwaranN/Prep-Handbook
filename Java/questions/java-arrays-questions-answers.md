# Java - Arrays Questions & Answers

---

## 1. What is an array in Java?

An array in Java is a **data structure that stores multiple values of the same data type** under a single variable name. It is a container object that holds a **fixed number** of values of a single type.

---

## 2. How do you declare an array in Java?

```java
dataType[] arrayName;

// Examples
int[] numbers;
String[] names;
double[] prices;
```

---

## 3. How do you initialize an array in Java?

**Method 1 — Using `new` with values:**

```java
int[] numbers = new int[]{1, 2, 3, 4, 5};
```

**Method 2 — Shorthand initialization:**

```java
int[] numbers = {1, 2, 3, 4, 5};
```

**Method 3 — Declare with size (default values):**

```java
int[] numbers = new int[5]; // {0, 0, 0, 0, 0}
```

---

## 4. How do you access elements in an array?

Elements are accessed using their **index** (0-based). The first element is at index `0`, the last at index `length - 1`.

```java
int[] numbers = {1, 2, 3, 4, 5};

int first = numbers[0]; // 1
int last  = numbers[4]; // 5
int third = numbers[2]; // 3

// Modify an element
numbers[1] = 20;
System.out.println(numbers[1]); // 20
```

---

## 5. What is the `length` of an array?

Use the `.length` property (not a method) to get the number of elements.

```java
int[] numbers = {1, 2, 3, 4, 5};
int length = numbers.length; // 5
System.out.println("Array length: " + length);
```

---

## 6. Can the length of an array be changed once it is created?

No. Arrays in Java have a **fixed size** — once created, the length cannot be changed. If a resizable array is needed, use **`ArrayList`** instead.

---

## 7. What is the difference between an array and an `ArrayList`?

| Feature | Array | ArrayList |
|---------|-------|-----------|
| **Size** | Fixed — set at creation. | Dynamic — resizes automatically. |
| **Type** | Can hold primitives and objects. | Objects only (uses autoboxing for primitives). |
| **Performance** | Faster for direct access. | Slightly slower due to resizing overhead. |
| **Methods** | No built-in add/remove. | Rich API (`add()`, `remove()`, `contains()`, etc.). |
| **Length** | `array.length` | `list.size()` |
| **Syntax** | `int[] arr = new int[5];` | `ArrayList<Integer> list = new ArrayList<>();` |

---

## 8. How do you iterate through an array in Java?

**Method 1 — Traditional `for` loop (with index):**

```java
int[] numbers = {1, 2, 3, 4, 5};
for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

**Method 2 — Enhanced `for-each` loop:**

```java
for (int num : numbers) {
    System.out.println(num);
}
```

**Method 3 — `Arrays.stream()` (Java 8+):**

```java
Arrays.stream(numbers).forEach(System.out::println);
```

---

## 9. How do you find the maximum or minimum value in an array?

**Manual iteration:**

```java
int[] numbers = {5, 2, 7, 1, 4};
int max = numbers[0], min = numbers[0];

for (int num : numbers) {
    if (num > max) max = num;
    if (num < min) min = num;
}
System.out.println("Max: " + max + ", Min: " + min); // Max: 7, Min: 1
```

**Using `Arrays.sort()`:**

```java
Arrays.sort(numbers);
int min = numbers[0];                    // Smallest after sort
int max = numbers[numbers.length - 1];   // Largest after sort
```

**Using streams (Java 8+):**

```java
int max = Arrays.stream(numbers).max().getAsInt();
int min = Arrays.stream(numbers).min().getAsInt();
```

---

## 10. How do you sort an array in Java?

Use `Arrays.sort()` from `java.util.Arrays`:

```java
int[] numbers = {5, 2, 7, 1, 4};
Arrays.sort(numbers); // Ascending: {1, 2, 4, 5, 7}
System.out.println(Arrays.toString(numbers));

// Sort in descending order (Integer array required)
Integer[] nums = {5, 2, 7, 1, 4};
Arrays.sort(nums, Collections.reverseOrder()); // {7, 5, 4, 2, 1}
```

---

## 11. What are the default values of an array?

When an array is created with `new dataType[size]`, elements are initialized to **default values**:

| Data Type | Default Value |
|-----------|--------------|
| `int`, `long`, `short`, `byte` | `0` |
| `float`, `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` (null character) |
| Reference types (`Object`, `String`) | `null` |

```java
int[] ints    = new int[3];    // {0, 0, 0}
boolean[] bools = new boolean[2]; // {false, false}
String[] strs = new String[2];  // {null, null}
```

---

## 12. What is the difference between `length` and `length()`?

| Feature | `.length` | `.length()` |
|---------|-----------|-------------|
| **Type** | Property (field). | Method. |
| **Used with** | **Arrays** | **Strings** |
| **Returns** | Number of elements in the array. | Number of characters in the string. |

```java
int[] arr = {1, 2, 3};
System.out.println(arr.length);       // 3 — array property

String str = "Hello";
System.out.println(str.length());     // 5 — String method
```

---

## 13. What is a multidimensional array in Java?

A multidimensional array is an **array of arrays**. It stores elements in a tabular form with rows and columns.

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

---

## 14. How do you declare a multidimensional array in Java?

```java
dataType[][] arrayName;

// Examples
int[][] matrix;
String[][] grid;
double[][][] cube; // 3D array
```

---

## 15. How do you initialize a multidimensional array in Java?

**With size (default values):**

```java
int[][] matrix = new int[3][3]; // 3 rows, 3 columns — all zeros
```

**With values:**

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

---

## 16. How do you access elements in a multidimensional array?

Use **row and column indices**: `array[row][column]`

```java
int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

int element = matrix[1][2]; // Row 1, Column 2 → 6
System.out.println(element);

matrix[0][0] = 100; // Modify element at row 0, column 0
```

---

## 17. How do you iterate through a multidimensional array?

**Using nested `for` loops:**

```java
int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };

for (int i = 0; i < matrix.length; i++) {
    for (int j = 0; j < matrix[i].length; j++) {
        System.out.print(matrix[i][j] + " ");
    }
    System.out.println();
}
// Output:
// 1 2 3
// 4 5 6
// 7 8 9
```

**Using nested `for-each` loops:**

```java
for (int[] row : matrix) {
    for (int element : row) {
        System.out.print(element + " ");
    }
    System.out.println();
}
```

---

## 18. Can rows in a multidimensional array have different lengths?

Yes. In Java, each row of a multidimensional array is an **independent array object** and can have a different length. This is called a **jagged array**.

```java
int[][] jagged = new int[3][];
jagged[0] = new int[]{1, 2};           // 2 elements
jagged[1] = new int[]{3, 4, 5};        // 3 elements
jagged[2] = new int[]{6, 7, 8, 9};     // 4 elements

for (int[] row : jagged) {
    System.out.println(Arrays.toString(row));
}
// Output: [1, 2] | [3, 4, 5] | [6, 7, 8, 9]
```

---

## 19. How do you find the sum of all elements in a multidimensional array?

```java
int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
int sum = 0;

for (int[] row : matrix) {
    for (int element : row) {
        sum += element;
    }
}

System.out.println("Sum: " + sum); // Sum: 45
```

**Using streams (Java 8+):**

```java
int sum = Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .sum();
```

---

## 20. Explain array cloning. How do you clone an array?

Array cloning creates a **new array with the same contents** as the original. Note that for object arrays, `clone()` creates a **shallow copy** — the objects themselves are not duplicated.

**Method 1 — `clone()`:**

```java
int[] original = {1, 2, 3, 4, 5};
int[] cloned = original.clone();

cloned[0] = 100; // Doesn't affect original
System.out.println(Arrays.toString(original)); // [1, 2, 3, 4, 5]
System.out.println(Arrays.toString(cloned));   // [100, 2, 3, 4, 5]
```

**Method 2 — `Arrays.copyOf()`:**

```java
int[] copy = Arrays.copyOf(original, original.length);

// Copy a portion
int[] partial = Arrays.copyOfRange(original, 1, 4); // [2, 3, 4]
```

**Method 3 — `System.arraycopy()`:**

```java
int[] dest = new int[original.length];
System.arraycopy(original, 0, dest, 0, original.length);
```

---

## 21. Can you use arrays with other data structures or classes?

Yes. Arrays can be used with other data structures and custom classes:

```java
// Array of custom objects
class Student {
    String name;
    int grade;
    Student(String name, int grade) { this.name = name; this.grade = grade; }
}

Student[] students = {
    new Student("Alice", 90),
    new Student("Bob", 85),
    new Student("Charlie", 92)
};

// Sort students by grade
Arrays.sort(students, (a, b) -> b.grade - a.grade); // Descending by grade

// Array used with a List
List<int[]> listOfArrays = new ArrayList<>();
listOfArrays.add(new int[]{1, 2, 3});
listOfArrays.add(new int[]{4, 5, 6});

// Array passed to a method
printArray(students);
```

---

## Arrays — Quick Reference

| Operation | Code |
|-----------|------|
| **Declare** | `int[] arr;` |
| **Initialize with values** | `int[] arr = {1, 2, 3};` |
| **Initialize with size** | `int[] arr = new int[5];` |
| **Access element** | `arr[index]` |
| **Length** | `arr.length` |
| **Sort** | `Arrays.sort(arr)` |
| **Print** | `Arrays.toString(arr)` |
| **Clone** | `arr.clone()` or `Arrays.copyOf(arr, arr.length)` |
| **Search** | `Arrays.binarySearch(arr, key)` (sorted array) |
| **Fill** | `Arrays.fill(arr, value)` |
| **2D access** | `matrix[row][col]` |
| **2D iterate** | Nested `for` loops |

| Default Values | Value |
|---------------|-------|
| `int` / `long` / `short` / `byte` | `0` |
| `float` / `double` | `0.0` |
| `boolean` | `false` |
| `char` | `'\u0000'` |
| Object reference | `null` |

---

*Happy Coding! 🚀*
