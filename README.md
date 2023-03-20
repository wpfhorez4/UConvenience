# UConvenience</br>
### 개발 사양</br>
플랫폼 : Android Studio</br>
언어 : Kotlin</br>
서비스 : 편의성을 위한 라이브러리</br>
</br></br>
### 사용 방법</br>
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```
dependencies {
    implementation 'com.github.wpfhorez4:UConvenience:2.0'
}
```

</br></br>
### 참고 사항</br>
| 클래스 | 내용 | 최초 버전 | 최신 버전 |
| --- | --- | --- | ---- |
| USync | synchronized 간소화 | 1.0 | 1.01 |
| UText | 첫글자 강조 함수 추가(companion object method) | 2.0 | 2.0 |

</br></br>
### 패치 내용</br>
| 버전 | 내용                                        | 사용 여부 |
| --- |-------------------------------------------|-------|
| 1.0 | USync class 추가                            | 불허    |
| 1.01 | USync class wait time out interface 추가    | 불허    |
| 2.0 | UText class 추가 companion object method 작성 | 허용    | 
