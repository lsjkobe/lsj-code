# 需要JDK:21

# 依赖install
## lsj-core-grpc-core目录
   1. 先mvn install lsj-core-spring-grpc-core
   2. 再mvn install lsj-core-spring-grpc-client和lsj-core-spring-grpc-server
## 2. lsj-core-spring-grpc-discovery目录
   每个注册中心的项目都以lsj-core-spring-grpc-discovery-[xx]-dir的命名规范,[xx]比如是nacos/etcd
   1. 先mvn install lsj-core-spring-grpc-discovery-[xx]
   2. 再mvn install lsj-core-spring-grpc-client-[xx]和lsj-core-spring-grpc-server-[xx]

# 例子使用
## lsj-core-grpc-test目录
   1. lsj-core-grpc-server-stub-test是服务端向客户端提供proto的stub的包，需要先mvn install
   2. 启动lsj-core-grpc-server-test
   3. 启动lsj-core-spring-grpc-client-test
   4. 访问http://localhost:7777/test/test即可测试
