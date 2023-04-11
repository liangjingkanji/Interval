<p align="center"> <strong>强大的Android计时器</strong> </p>

<br>
<p align="center">
<img src="https://user-images.githubusercontent.com/21078112/178807127-6859cae5-290d-4350-bbde-cecca9e30276.png" width="400"/>
</p>

<p align="center">
<a href="https://github.com/liangjingkanji/Interval/releases/download/1.0.3/interval.apk">下载体验</a>
</p>

<p align="center">
<a href="https://jitpack.io/#liangjingkanji/Interval"><img src="https://jitpack.io/v/liangjingkanji/Interval.svg"/></a>
<img src="https://img.shields.io/badge/language-kotlin-orange.svg"/>
<img src="https://img.shields.io/badge/license-Apache-blue"/>
<img src="https://raw.githubusercontent.com/liangjingkanji/liangjingkanji/master/img/group.svg"/>
</p>


<br>
<p align="center">
<img align="center" src="https://user-images.githubusercontent.com/21078112/178810331-69eb172c-4fda-4342-9f56-b51b4f11b45b.jpg" width="250"/>
</p>

<br>

本工具为弥补Android没有完善的计时器而生, 使用协程Coroutine实现计时功能, 本工具从[Net](https://github.com/liangjingkanji/Net/)中剥离单独发布仓库, 如果已经依赖Net无需再依赖此工具


## 特点

- [x] 倒计时/正计时
- [x] 开始/暂停/继续/结束/取消/重置
- [x] 定时/结束事件
- [x] 初始间隔/每次间隔
- [x] 动态修改计数器
- [x] 生命周期自动取消
- [x] 页面不可见暂停/可见继续


## 使用

监听轮询器

```kotlin
// 自定义计数器个数的轮询器
interval = Interval(100, 1, TimeUnit.SECONDS).life(this)
// 每秒回调一次, 不会自动结束
interval = Interval(1, TimeUnit.SECONDS)
// 倒计时轮询器, 当[start]]比[end]值大, 且end不等于-1时, 即为倒计时
interval = Interval(1, 1, TimeUnit.SECONDS, 5).life(this)

interval.subscribe {
    tvFragment.text = it.toString()
}.finish {
    tvFragment.text = "计时完成" // 最后一位数时同时回调 subscribe/finish
}.start()
```

## 函数

使用的函数非常简单

| Interval方法 | 描述 |
|-|-|
| start | 开始轮询器 |
| stop | 停止 |
| cancel | 取消, 区别于stop, 此函数执行后并不会回调finish |
| pause | 暂停 |
| resume | 继续 |
| reset | 重置 |
| switch | 切换开关 |
| count | 当前计数器 |
| end | 结束计数器 |
| state | 当前状态 |
| subscribe | 每次计时都会执行该回调 |
| finish | 当计时器完成时执行该回调, 执行stop后也会回调 |
| life | 指定生命周期自动取消轮询器 |
| onlyResumed | 当界面不可见时暂停, 当界面可见时继续 |




## 安装

添加远程仓库根据创建项目的 Android Studio 版本有所不同

Android Studio Arctic Fox以下创建的项目 在项目根目录的 build.gradle 添加仓库

```groovy
allprojects {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

Android Studio Arctic Fox以上创建的项目 在项目根目录的 settings.gradle 添加仓库

```kotlin
dependencyResolutionManagement {
    repositories {
        // ...
        maven { url 'https://jitpack.io' }
    }
}
```

然后在 module 的 build.gradle 添加依赖框架

```groovy
implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0" // 协程(版本自定)
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0'

implementation 'com.github.liangjingkanji:Interval:1.0.3'
```



## License

```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
