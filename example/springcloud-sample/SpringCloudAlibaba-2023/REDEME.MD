### SpringCloudAlibaba

#### 一、项目依赖
| 项目  | 版本         |
|-----|------------|
|spring-boot-dependencies| 3.2.x      |
|spring-cloud-dependencies| 2023.0.x   |
|spring-cloud-alibaba-dependencies| 2023.0.1.0 |

#### 一、项目结构
```angular2html
ding-project
├── settings.gradle.kts
├── build.gradle.kts (全局配置，如仓库等)
├── ding-project-parent
│   └── build.gradle.kts (父项目配置，依赖管理版本)
├── ding-dependencies
│   └── build.gradle.kts (依赖管理模块)
├── ding-boot-starter
│   └── build.gradle.kts (自定义Spring Boot Starter)
├── ding-service-component
│   ├── component-a
│   │   └── build.gradle.kts
│   ├── component-b
│   │   └── build.gradle.kts
│   ├── component-c
│   │   └── build.gradle.kts
│   └── component-d
│       └── build.gradle.kts
└── ding-project-business
    ├── business-a
    │   ├── ding-A-entity
    │   │   └── build.gradle.kts
    │   ├── ding-A-dto
    │   │   └── build.gradle.kts
    │   ├── ding-A-service
    │   │   └── build.gradle.kts
    │   ├── ding-A-api
    │   │   └── build.gradle.kts
    │   └── ding-A-boot
    │       └── build.gradle.kts
    ├── business-b
    │   ... (相似结构)
    └── business-c
... (相似结构)
```
