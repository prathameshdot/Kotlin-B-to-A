# Kotlin Introduction & Setup

## Table of Contents
1. [What is Kotlin?](#what-is-kotlin)
2. [History](#history)
3. [Kotlin vs Java](#kotlin-vs-java)
4. [Platform Targets](#platform-targets)
5. [Setup & Installation](#setup--installation)
6. [First Program](#first-program)
7. [Main Function Variations](#main-function-variations)
8. [Print & Comments](#print--comments)
9. [Practice Questions](#practice-questions)
10. [Interview Questions](#interview-questions)

---

## What is Kotlin?

Kotlin is a **modern, statically-typed** programming language developed by **JetBrains**. It runs on the **Java Virtual Machine (JVM)** and can also be compiled to JavaScript or native code. Kotlin is designed to be:

- **Concise** — Reduces boilerplate code significantly
- **Safe** — Eliminates entire classes of errors (e.g., NullPointerException)
- **Interoperable** — 100% interoperable with Java
- **Tool-friendly** — First-class IDE support in IntelliJ IDEA and Android Studio

---

## History

| Year | Milestone |
|------|-----------|
| **2011** | JetBrains unveils Project Kotlin |
| **2012** | Kotlin open-sourced under Apache 2.0 license |
| **2016** | Kotlin 1.0 released (first stable version) |
| **2017** | Google announces **first-class support** for Kotlin on Android at Google I/O |
| **2018** | Kotlin 1.3 — Coroutines stable, Kotlin/Native beta |
| **2019** | Google declares Kotlin as the **preferred language** for Android development |
| **2020** | Kotlin 1.4 — quality and performance improvements |
| **2021** | Kotlin 1.5 — JVM records, sealed interfaces, stable inline classes |
| **2023** | Kotlin 1.9 — K2 compiler beta, Kotlin Multiplatform stable |
| **2024** | Kotlin 2.0 — K2 compiler stable |

---

## Kotlin vs Java

| Feature | Kotlin | Java |
|---------|--------|------|
| Null safety | Built-in (`?` types) | Optional class (Java 8+) |
| Data classes | `data class` (1 line) | Boilerplate or records (Java 16+) |
| Extension functions | ✅ Supported | ❌ Not supported |
| Coroutines | ✅ Built-in | Threads / Virtual Threads (Java 21+) |
| Smart casts | ✅ Automatic | ❌ Manual casting |
| String templates | `"Hello, $name"` | `"Hello, " + name` or STR (Java 21+) |
| Semicolons | Optional | Required |
| `new` keyword | Not needed | Required |
| Type inference | Strong | Limited (var in Java 10+) |
| When expression | Powerful pattern matching | Switch (enhanced in Java 17+) |
| Default arguments | ✅ Supported | ❌ Need overloading |
| Checked exceptions | ❌ None | ✅ Mandatory handling |

### Code Comparison

**Java:**
```java
public class Person {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }
}
```

**Kotlin:**
```kotlin
data class Person(val name: String, val age: Int)
```

> **One line** replaces ~20 lines of Java and includes `toString()`, `equals()`, `hashCode()`, and `copy()`.

---

## Platform Targets

Kotlin compiles to multiple platforms:

### 1. Kotlin/JVM
- Runs on the Java Virtual Machine
- Full interoperability with Java libraries
- Primary target for Android and server-side development

### 2. Kotlin/JS
- Compiles to JavaScript
- Can use npm packages and JS frameworks
- Used for frontend web development

### 3. Kotlin/Native
- Compiles directly to native binaries (no VM needed)
- Targets: iOS, macOS, Linux, Windows, watchOS, tvOS, WebAssembly
- Uses LLVM backend

### 4. Kotlin Multiplatform (KMP)
- Share business logic across platforms
- Platform-specific implementations for UI
- `expect`/`actual` mechanism for platform abstractions

```
                    ┌─────────────┐
                    │ Kotlin Code │
                    └──────┬──────┘
           ┌───────────────┼───────────────┐
           ▼               ▼               ▼
      ┌─────────┐   ┌───────────┐   ┌──────────┐
      │ JVM     │   │ JavaScript│   │ Native   │
      │Bytecode │   │   Code    │   │ Binary   │
      └─────────┘   └───────────┘   └──────────┘
      Android,       Browser,        iOS, macOS,
      Server         Node.js         Linux, Windows
```

---

## Setup & Installation

### Option 1: IntelliJ IDEA (Recommended for learning)
1. Download IntelliJ IDEA (Community Edition is free) from [jetbrains.com](https://www.jetbrains.com/idea/)
2. Kotlin is bundled — no separate installation needed
3. Create new Kotlin project → JVM → Start coding

### Option 2: Android Studio (For Android development)
1. Download from [developer.android.com/studio](https://developer.android.com/studio)
2. Kotlin is built-in since Android Studio 3.0
3. Create new Android project → Kotlin is default

### Option 3: Command Line
```bash
# Install via SDKMAN (Linux/Mac)
sdk install kotlin

# Install via Homebrew (Mac)
brew install kotlin

# Install via Scoop (Windows)
scoop install kotlin

# Manual: Download from https://github.com/JetBrains/kotlin/releases
# Add bin/ to PATH
```

### Option 4: Kotlin Playground (Online)
- Visit [play.kotlinlang.org](https://play.kotlinlang.org)
- No installation needed — run Kotlin directly in browser

### Verify Installation
```bash
kotlinc -version    # Check Kotlin compiler version
kotlin -version     # Check Kotlin runner version
```

---

## First Program

### Writing & Running

**File: HelloWorld.kt**
```kotlin
fun main() {
    println("Hello, Kotlin!")
}
```

**Compile & Run:**
```bash
# Method 1: Compile to JAR
kotlinc HelloWorld.kt -include-runtime -d HelloWorld.jar
java -jar HelloWorld.jar

# Method 2: Direct run with kotlin
kotlin HelloWorld.kt

# Method 3: Kotlin scripting (.kts files)
# File: hello.kts
# println("Hello from script!")
kotlin hello.kts
```

### Kotlin REPL (Read-Eval-Print Loop)
```bash
kotlinc    # Start REPL
>>> println("Hello")
Hello
>>> val x = 42
>>> x * 2
84
>>> :quit
```

---

## Main Function Variations

```kotlin
// Standard main (most common)
fun main() {
    println("No args main")
}

// Main with command-line arguments
fun main(args: Array<String>) {
    println("Arguments: ${args.joinToString()}")
    if (args.isNotEmpty()) {
        println("First arg: ${args[0]}")
    }
}
```

> **Note:** Since Kotlin 1.3, `args: Array<String>` parameter is optional if you don't need command-line arguments.

---

## Print & Comments

### Output Functions
```kotlin
println("Prints with newline")     // Adds \n at end
print("Prints without newline")    // No \n
print(" - continues on same line\n")

// String templates
val name = "Kotlin"
println("Hello, $name!")                    // Variable reference
println("Length: ${name.length}")           // Expression in braces
println("Sum: ${10 + 20}")                 // Arithmetic expression
```

### Comments
```kotlin
// Single-line comment

/* 
 * Multi-line
 * comment
 */

/**
 * KDoc comment (Kotlin's documentation format)
 * @param name The person's name
 * @return A greeting string
 */
fun greet(name: String): String = "Hello, $name!"

/* Nested /* comments */ are allowed in Kotlin! */
// Unlike Java, Kotlin supports nested block comments
```

### Semicolons
```kotlin
// Semicolons are OPTIONAL (and discouraged)
val x = 10     // No semicolon needed
val y = 20     // Clean and concise

// Only needed for multiple statements on one line
val a = 1; val b = 2; println(a + b)
```

---

## Tips

1. **Start with Kotlin Playground** for quick experimentation without setup
2. **Use IntelliJ IDEA** — it has the best Kotlin support (made by the same company)
3. **Don't think in Java** — embrace Kotlin idioms (data classes, when, extensions)
4. **Kotlin is null-safe by default** — `String` cannot be null, `String?` can
5. **Read the official docs** at [kotlinlang.org/docs](https://kotlinlang.org/docs/)
6. **Use `.kts` scripts** for quick scripting tasks (no main() needed)
7. **Kotlin coroutines** are lighter than threads — learn them early for async work

---

## Practice Questions

### Q1. Write a program that prints your name, age, and favorite language using string templates.
<details><summary>Answer</summary>

```kotlin
fun main() {
    val name = "Alice"
    val age = 25
    val language = "Kotlin"
    println("Name: $name, Age: $age, Favorite Language: $language")
}
```
</details>

### Q2. What is the difference between `print()` and `println()`?
<details><summary>Answer</summary>

`print()` outputs text without a newline at the end. `println()` outputs text followed by a newline character (`\n`). Multiple `print()` calls output on the same line; `println()` moves to the next line.
</details>

### Q3. Write a program that calculates and prints the area of a rectangle (width=15, height=8) using string templates.
<details><summary>Answer</summary>

```kotlin
fun main() {
    val width = 15
    val height = 8
    println("Rectangle: ${width}x${height}, Area = ${width * height}")
}
```
</details>

### Q4. How do you compile and run a Kotlin file from the command line?
<details><summary>Answer</summary>

```bash
kotlinc MyFile.kt -include-runtime -d MyFile.jar
java -jar MyFile.jar
# OR simply:
kotlin MyFile.kt
```
</details>

### Q5. What are the three platform targets for Kotlin?
<details><summary>Answer</summary>

1. **Kotlin/JVM** — Compiles to JVM bytecode (Android, server-side)
2. **Kotlin/JS** — Compiles to JavaScript (web frontend)
3. **Kotlin/Native** — Compiles to native binaries via LLVM (iOS, desktop, embedded)
</details>

### Q6. Write a program using a multi-line string with `trimMargin()` to display a formatted address.
<details><summary>Answer</summary>

```kotlin
fun main() {
    val address = """
        |John Doe
        |123 Main Street
        |Springfield, IL 62704
        |USA
    """.trimMargin()
    println(address)
}
```
</details>

### Q7. Are semicolons required in Kotlin? When would you use one?
<details><summary>Answer</summary>

Semicolons are **optional** in Kotlin and generally discouraged. They are only needed when placing multiple statements on a single line: `val a = 1; val b = 2`
</details>

### Q8. What is the Kotlin REPL and how do you start it?
<details><summary>Answer</summary>

The REPL (Read-Eval-Print Loop) is an interactive Kotlin shell for testing code snippets. Start it by running `kotlinc` with no arguments in the terminal. Type `:quit` to exit.
</details>

### Q9. Write a main function that accepts command-line arguments and prints each one with its index.
<details><summary>Answer</summary>

```kotlin
fun main(args: Array<String>) {
    for ((index, arg) in args.withIndex()) {
        println("Arg $index: $arg")
    }
}
```
</details>

### Q10. What year did Google announce official support for Kotlin on Android?
<details><summary>Answer</summary>

**2017** — Google announced first-class support for Kotlin on Android at Google I/O 2017. In 2019, Google declared Kotlin as the **preferred language** for Android.
</details>

### Q11. Explain the difference between Kotlin scripts (.kts) and regular Kotlin files (.kt).
<details><summary>Answer</summary>

`.kt` files require a `main()` function as entry point and must be compiled. `.kts` (Kotlin Script) files are executed top-to-bottom without a `main()` function and run with `kotlin script.kts`. Scripts are useful for quick tasks and build scripts (Gradle uses `.kts`).
</details>

### Q12. Write a program that uses nested block comments (a feature Java doesn't support).
<details><summary>Answer</summary>

```kotlin
fun main() {
    /* This is a comment
       /* This nested comment is valid in Kotlin */
       Still inside the outer comment
    */
    println("Kotlin supports nested comments!")
}
```
</details>

### Q13. What is KDoc and how does it differ from Javadoc?
<details><summary>Answer</summary>

KDoc is Kotlin's documentation format, using `/** ... */` comments with tags like `@param`, `@return`, `@throws`, `@property`, `@sample`. It's processed by the **Dokka** tool (equivalent of Javadoc). Key difference: KDoc supports Markdown formatting and linking to Kotlin elements with `[ClassName]` syntax.
</details>

### Q14. Write a program that demonstrates string template expressions (not just variables).
<details><summary>Answer</summary>

```kotlin
fun main() {
    val items = listOf("Apple", "Banana", "Cherry")
    println("Items count: ${items.size}")
    println("First item uppercase: ${items[0].uppercase()}")
    println("Is list empty? ${items.isEmpty()}")
    println("Max of 10, 20: ${maxOf(10, 20)}")
}
```
</details>

### Q15. List three advantages of Kotlin over Java.
<details><summary>Answer</summary>

1. **Null safety** — Nullable types prevent NullPointerException at compile time
2. **Conciseness** — Data classes, type inference, default arguments reduce boilerplate
3. **Coroutines** — Lightweight concurrency without callback hell or complex thread management

Other valid answers: extension functions, smart casts, when expressions, no checked exceptions.
</details>

---

## Interview Questions

### Q1. What is Kotlin and who developed it?
<details><summary>Answer</summary>

Kotlin is a modern, statically-typed programming language developed by **JetBrains** (the company behind IntelliJ IDEA). It was first announced in 2011 and reached version 1.0 in 2016. It runs on the JVM, JavaScript, and native platforms. In 2017, Google announced official support for Kotlin on Android, and in 2019 declared it the preferred language for Android development.
</details>

### Q2. How is Kotlin interoperable with Java?
<details><summary>Answer</summary>

Kotlin compiles to JVM bytecode, making it 100% interoperable with Java:
- Kotlin code can call Java code directly and vice versa
- Kotlin can use all existing Java libraries and frameworks
- Kotlin and Java files can coexist in the same project
- Kotlin classes appear as regular Java classes when compiled
- Annotations like `@JvmStatic`, `@JvmField`, `@JvmOverloads` help customize Java interop
</details>

### Q3. What are the key advantages of Kotlin over Java?
<details><summary>Answer</summary>

1. **Null safety** — Compiler distinguishes nullable (`String?`) from non-nullable (`String`) types
2. **Conciseness** — Data classes, type inference, default/named arguments, string templates
3. **Coroutines** — Built-in lightweight concurrency
4. **Extension functions** — Add methods to existing classes without inheritance
5. **Smart casts** — Automatic casting after type checks
6. **Functional features** — Higher-order functions, lambdas as first-class citizens
7. **No checked exceptions** — Cleaner error handling
8. **Sealed classes** — Restricted class hierarchies for exhaustive when expressions
</details>

### Q4. What is Kotlin Multiplatform (KMP)?
<details><summary>Answer</summary>

Kotlin Multiplatform allows sharing code across platforms (Android, iOS, web, desktop, server). You write common business logic once and provide platform-specific implementations using the `expect`/`actual` mechanism. KMP is not a cross-platform UI framework — it focuses on shared logic while allowing native UI per platform.
</details>

### Q5. Explain the different Kotlin compilation targets.
<details><summary>Answer</summary>

- **Kotlin/JVM**: Compiles to JVM bytecode. Used for Android, server-side (Spring, Ktor), desktop
- **Kotlin/JS**: Compiles to JavaScript. Used for web frontend, Node.js
- **Kotlin/Native**: Uses LLVM to compile to native binaries. Used for iOS, embedded, desktop without JVM
- **Kotlin/Wasm**: Compiles to WebAssembly (experimental). Used for browser-based applications
</details>

### Q6. Is Kotlin a replacement for Java?
<details><summary>Answer</summary>

Kotlin is not designed to replace Java but to **complement** it. They coexist in the same project seamlessly. Kotlin addresses Java's verbosity and safety issues while maintaining full interoperability. For Android, Google recommends Kotlin for new projects, but existing Java code continues to work. On the server side, frameworks like Spring fully support both languages.
</details>

### Q7. What does "statically typed" mean in the context of Kotlin?
<details><summary>Answer</summary>

Statically typed means variable types are determined at **compile time**, not runtime. Every variable and expression has a known type before the program runs. Kotlin uses strong **type inference** — you don't always need to write types explicitly (`val x = 42` infers `Int`), but the compiler still knows and enforces types at compile time. This catches type errors early.
</details>

### Q8. What is the Kotlin REPL and when would you use it?
<details><summary>Answer</summary>

REPL (Read-Eval-Print Loop) is an interactive shell started by running `kotlinc`. You type Kotlin expressions and see results immediately. Use cases: learning the language, testing small code snippets, prototyping algorithms, checking API behavior. In IntelliJ, you can also use Kotlin Scratch Files and Worksheets for a similar experience with IDE features.
</details>

### Q9. How does Kotlin handle null safety differently from Java?
<details><summary>Answer</summary>

In Kotlin, the type system distinguishes nullable and non-nullable types:
- `String` — cannot hold null (compile error if you try)
- `String?` — can hold null
- Safe call operator `?.` — returns null instead of throwing NPE
- Elvis operator `?:` — provides default value for null
- Not-null assertion `!!` — throws NPE if null (use sparingly)

This eliminates most NullPointerExceptions at compile time, while Java allows any reference to be null.
</details>

### Q10. What is the purpose of the `fun` keyword?
<details><summary>Answer</summary>

`fun` is the keyword used to declare functions in Kotlin (short for "function"). Every function, including `main()`, is declared with `fun`. Example: `fun greet(name: String): String = "Hello, $name!"`. Unlike Java, Kotlin functions can be declared at the top level (outside classes), as local functions (inside other functions), and as extension functions.
</details>

### Q11. Explain string templates in Kotlin.
<details><summary>Answer</summary>

String templates allow embedding variables and expressions directly inside strings:
- `$variable` — inserts a variable's value: `"Hello, $name"`
- `${expression}` — evaluates an expression: `"Sum: ${a + b}"`
- Works in both regular (`"..."`) and raw (`"""..."""`) strings
- More readable and safer than Java's string concatenation
- The compiler optimizes templates to StringBuilder operations
</details>

### Q12. What are Kotlin's primary use cases?
<details><summary>Answer</summary>

1. **Android development** — Preferred language by Google
2. **Server-side** — Spring Boot, Ktor, Micronaut
3. **Multiplatform** — Shared KMP libraries for iOS/Android/Web
4. **Desktop** — Compose Multiplatform for desktop UIs
5. **Data science** — Kotlin notebooks, DataFrame library
6. **Scripting** — `.kts` files, Gradle build scripts
7. **Web frontend** — Kotlin/JS with various frameworks
</details>

### Q13. What build tools support Kotlin?
<details><summary>Answer</summary>

- **Gradle** — Primary build tool, supports Kotlin DSL (`build.gradle.kts`)
- **Maven** — Full support via kotlin-maven-plugin
- **IntelliJ IDEA** — Built-in build system for simple projects
- **Ant** — Supported via kotlin-ant tasks
- **Bazel** — Community support via rules_kotlin
- **kotlinc** — Command-line compiler for direct compilation
</details>

### Q14. What is the difference between `val` and `var` in brief?
<details><summary>Answer</summary>

- `val` — Read-only (immutable reference), like Java's `final`. Cannot be reassigned after initialization.
- `var` — Mutable, can be reassigned.
- Best practice: prefer `val` over `var` for immutability and thread safety.
```kotlin
val name = "Kotlin"   // Cannot reassign
var count = 0         // Can reassign: count = 1
```
</details>

### Q15. How does Kotlin compare to other JVM languages like Scala and Groovy?
<details><summary>Answer</summary>

| Aspect | Kotlin | Scala | Groovy |
|--------|--------|-------|--------|
| Typing | Static | Static | Dynamic/Static |
| Learning curve | Moderate | Steep | Easy |
| Compilation speed | Fast | Slow | Moderate |
| Java interop | Excellent | Good | Excellent |
| Primary use | Android, Server | Big Data, FP | Scripting, Gradle |
| Null safety | Built-in | Option type | Optional |
| Paradigm | OOP + FP | FP + OOP | OOP + Scripting |

Kotlin strikes a balance between Java familiarity and modern features, with faster compilation than Scala and stronger typing than Groovy.
</details>
