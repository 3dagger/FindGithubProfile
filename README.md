# Find Github Profile 
간단한 Github 사용자 프로필 조회 어플리케이션
## Tech Stack & Open-source libraries
- Kotlin
- Minimum SDK Version 22
- Coroutine Flow (for asynchronous)
- Hilt (for Dependency Injection)
- Retrofit2 & OkHttp3 (for construct REST APIs)
- Glide (for User Profile Image)
- Logger (for Pretty Logging)
- Material Components (for Material Design)
- Moshi (A modern JSON library for Kotlin and Java)
- Architecture
  - MVVM Architecture (Model-View-ViewModel)
  - Multi Modules (App Module, Data Module, Domain Module)
  - Data Binding (Two-way data binding)

## How to use?
- Find Github Profile을 사용하기 위해서는 Github API Key가 필요합니다.
- Root Project의 apikey.properties 파일 안에 GITHUB_API_KEY="" 부분의 해당 KEY를 입력해주세요. 

## Open API
- Find Github Profile 은 [Github REST API](https://docs.github.com/en/rest)를 사용하였습니다.
