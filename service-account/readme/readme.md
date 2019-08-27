## 使用说明

### 启动
```
1、启动命令：java -jar XXXX.jar --spring.profiles.active=dev
备注：增加启动参数，指定运行环境，运行环境：
dev：开发环境（默认环境）
test: 测试环境
prod：生产环境

```

### 测试接口
```
1、地址
http://localhost:7100/test
```

### 打包部署
```
1、使用spring-boot-maven-plugin打包，会将依赖的Jar 一起打进入
```