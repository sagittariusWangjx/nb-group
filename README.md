# nb-group
nb 物联网相关的辅助相关软件

### 主要业务
1、信号的检测
2、信号的分布


### 主要特性
1. 集成spring boot 常用开发组件集、公共配置、AOP日志等
2. 集成mybatis plus快速dao操作
3. 快速生成后台代码: entity/param/vo/controller/service/mapper/xml
4. 集成swagger2，可自动生成api文档
5. 集成jwt、shiro/spring security权限控制
6. 集成redis、spring cache、ehcache缓存
7. 集成rabbit/rocket/kafka mq消息队列
8. 集成druid连接池，JDBC性能和慢查询检测
9. 集成spring boot admin，实时检测项目运行情况
10. 使用assembly maven插件进行不同环境打包部署,包含启动、重启命令，配置文件提取到外部config目录

## 项目架构
![spring-boot-plus-architecture.jpg](https://spring-boot-plus.gitee.io/img/spring-boot-plus-architecture.jpg)

### 项目环境 
中间件 | 版本 |  备注
-|-|-
JDK | 1.8+ | JDK1.8及以上 |
MySQL | 5.7+ | 5.7及以上 |
Redis | 3.2+ |  |

### 技术选型 
技术 | 版本 |  备注
-|-|-
Spring Boot | 2.2.0.RELEASE | 最新发布稳定版 |
Spring Framework | 5.2.0.RELEASE | 最新发布稳定版 |
Mybatis | 3.5.2 | 持久层框架 |
Mybatis Plus | 3.2.0 | mybatis增强框架 |
Alibaba Druid | 1.1.20 | 数据源 |
Fastjson | 1.2.62 | JSON处理工具集 |
swagger2 | 2.6.1 | api文档生成工具 |
commons-lang3 | 3.9 | 常用工具包 |
commons-io | 2.6 | IO工具包 |
commons-codec | 1.13 | 加密解密等工具包 |
commons-collections4 | 4.4 | 集合工具包 |
reflections | 0.9.11 | 反射工具包 |
hibernate-validator | 6.0.17.Final | 后台参数校验注解 |
Shiro | 1.4.1 | 权限控制 |
JWT | 3.8.3 | JSON WEB TOKEN |
hutool-all | 5.0.3 | 常用工具集 |
lombok | 1.18.10 | 注解生成Java Bean等工具 |
mapstruct | 1.3.1.Final | 对象属性复制工具 |

