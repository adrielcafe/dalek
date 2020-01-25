![Banner](https://github.com/adrielcafe/dalek/blob/master/banner.jpg?raw=true)

[![JitPack](https://img.shields.io/jitpack/v/github/adrielcafe/dalek.svg?style=for-the-badge)](https://jitpack.io/#adrielcafe/dalek)
[![Github Actions](https://img.shields.io/github/workflow/status/adrielcafe/dalek/main/master?style=for-the-badge)](https://github.com/adrielcafe/dalek/actions)
[![Android API](https://img.shields.io/badge/api-16%2B-brightgreen.svg?style=for-the-badge)](https://android-arsenal.com/api?level=16)
[![Kotlin](https://img.shields.io/github/languages/top/adrielcafe/dalek.svg?style=for-the-badge)](https://kotlinlang.org/)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![License MIT](https://img.shields.io/github/license/adrielcafe/dalek.svg?style=for-the-badge&color=yellow)](https://opensource.org/licenses/MIT)

# Dalek
Dalek is a [tiny](https://github.com/adrielcafe/Dalek/blob/master/dalek/src/main/java/cafe/adriel/dalek/Dalek.kt) (~10 LOC) [finite state machine](https://en.wikipedia.org/wiki/Finite-state_machine) that helps you manage the UI state of your app when running async/breakable tasks.

It's powered by [Coroutines Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/).

#### Platform compatibility

| JVM | Android | iOS | JS | Native |
|-----|---------|-----|----|--------|
| ✔   | ✔       | ✗   | ✗  | ✗      |

*Working on multiplatform support*

#### Why *Dalek*?
[Daleks](https://en.wikipedia.org/wiki/Dalek) are a fictional extraterrestrial race of mutants portrayed in [Doctor Who](https://en.wikipedia.org/wiki/Doctor_Who).

They are creatures that live inside a tank-like robotic shell, and this is basically how this library works: your code (the "creature") is wrapped and executed inside a state machine (the "robotic shell").

## Motivation
Let's imagine the following scenario: you need to retrieve a `Post` object from a REST API, something like:

```kotlin
class PostRepository {

    suspend fun getPost(id: String): Post {
        // async api call
        // serialization
        // error handling
    }
}
```

It's an asynchronous operation that can **take a while** to complete and can **fail** (`TimeoutException`, `IOException`, `SerializationException`...). But wait, there's more: you need to keep the UI updated so your user knows what's going on.

This is a nightmare, right? Calm down! Dalek is here to help :robot:

## Usage

### 1. Wrap your code inside Dalek
To start using Dalek, you just need to wrap your existing code inside it.

Dalek will run your code inside a `suspend fun` and return a `Flow<DalekEvent<T>>`, where `T` is the output of your code (yes, you can return `null`, `Unit` and `Any`).

```kotlin
class MyViewModel(repository: PostRepository) : ViewModel() {

    fun getPost(id: String): Flow<DalekEvent<Post>> =
        Dalek(Dispatchers.IO) {
            repository.getPost(id)
        }
}
```

### 2. Handle the UI events
The emitted events by Dalek are:

* `Start`: always emitted
* `Success(value: T)`: only emitted when your code is successfully executed
* `Failure(exception: Throwable)`: only emitted when an exception is thrown
* `Finish`: always emitted

```kotlin
class MyActivity : AppCompatActivity() {

    private fun loadPost(id: String) {
        viewModel.getPost(id)
            .launchAndCollect(lifecycleScope) { event ->
                when (event) {
                    is Start -> setLoading(true)
                    is Success -> showPost(event.value)
                    is Failure -> showError(event.exception)
                    is Finish -> setLoading(false)
                }
            }
    }
}
```

## Import to your project
1. Add the JitPack repository in your root build.gradle at the end of repositories:
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

2. Next, add the library to your module:
```gradle
dependencies {
    implementation "com.github.adrielcafe.dalek:dalek:$currentVersion"
}
```
Current version: [![JitPack](https://img.shields.io/jitpack/v/github/adrielcafe/dalek.svg?style=flat-square)](https://jitpack.io/#adrielcafe/dalek)