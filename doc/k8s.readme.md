三大指标
 高可用，高性能，高并发
 技术是为业务服务的
 高性能
        RPC 通信
        kyro 高速序列化
        hikariCP 连接池
        SQL 优化
        Redis 缓存
        JVM 优化
        GC 优化
 高并发
     垂直拓展+水平拓展，+负载均衡+集群
 高可用  --->一直可以用
    解决单点故障问题，实现崩溃恢复
    自动重启，
    自动拓展
    5  --> 5000Qps   自动扩容，自动缩容
    k8s 建立在容器引擎上的
    服务要更新，金丝雀发布，滚动更新，版本回滚
    DevOps :自动化运维--> AIOps
    docker 三剑客

k8s 容器编排系统
https://funtl.com/zh/service-mesh-kubernetes/#%E6%9C%AC%E8%8A%82%E8%A7%86%E9%A2%91

TiDB  强一致性，和高可用，支持我先的水平拓展，还支持分析处理，无锡繁琐的ETL 过程
    100% OLTP 的场景和80%OLAP的场景
PD Service
TiKV Service    