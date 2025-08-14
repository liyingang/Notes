Kubernetes
    kubernetes的基础与创建
        一、简介
            1、Kubernetes 是什么
            2、Kubernetes 特性
        二、集群架构与组件
            1、Master Node
            2、Node
        三、核心概念
        四、集群搭建 —— 平台规划
            常见部署方案
            1、生产环境 K8S 平台规划
            2、操作系统初始化
        五、集群搭建 —— 部署Etcd集群
            1、自签证书
            2、自签 Etcd SSL 证书
            3、Etcd 数据库集群部署
        六、集群搭建 —— 部署Master组件
            1、自签 ApiServer SSL 证书
            2、部署 kube-apiserver 组件
            3、部署 kube-controller-manager 组件
            4、部署 kube-scheduler 组件
            5、查看集群状态
            6、检查启动结果
        七、集群搭建 —— 部署Node组件
            1、安装 Docker
            2、Node 节点证书
            3、安装 kubelet
            4、安装 kube-proxy
            5、部署其它Node节点
            6、部署K8S容器集群网络(Flannel)
            7、部署内部 DNS 服务
        八、集群搭建 —— 部署 Dashboard
            1、部署 K8S Dashboard
            2、登录授权
        九、集群搭建 —— 多 Master 部署
            1、部署Master2组件
            2、部署 Nginx 负载均衡
            3、部署 KeepAlive
        十、kubectl的使用
        十一 、k8s 组件调用流程
    Kubernetes使用
        1.资源管理
            1.1**资源管理方式**
            1.1.1 命令式对象管理
        2.基本使用
            1.Namespace
                1.1.1 查看
                1.1.2 创建
                1.1.3 删除
                1.1.4 配置方式
            2. Pod
                2.1 创建并运行
                2.2 查看Pod信息
                2.3 访问Pod
                2.4 删除指定Pod
                2.5 配置方式
            3.Label
                3.1 命令方式
                3.2 配置方式
            4.Deployment
                4.1 **命令操作**
                4.2 配置操作
            5.Service
                5.1 创建集群内部可访问的Service
                5.2 创建集群外部也可访问的Service
                5.3 删除Service
                5.4 配置方式
    Kubernetes私有化
    k8s 中的 hostNetwork 和 NetworkPolicy（网络策略）
    flannel 网络架构
    理解CNI和CNI插件
    Pod详解
        1、Pod介绍
            1.1、Pod结构
            1.2、Pod定义
        2、Pod配置
            2.1、基本配置
            2.2、镜像拉取
            2.3、启动命令
            2.4、环境变量
            2.5、端口设置
            2.6、资源配额
        3 Pod生命周期
            3.1 创建和终止
            3.2 初始化容器
            3.3 钩子函数
            3.4 容器探测
            3.5 重启策略
        4 Pod调度
            4.1 定向调度
            4.2 亲和性调度
            4.3 污点和容忍
    Pod控制器详解
        1 Pod控制器介绍
        2 ReplicaSet(RS)
        3 Deployment(Deploy)
            3.1 创建deployment
            3.2 扩缩容
            3.3 版本回退
            3.4 金丝雀发布
        4 Horizontal Pod Autoscaler(HPA)
            4.1 安装metrics-server
            4.2 准备deployment和servie
            4.3 部署HPA
            4.4 测试
        5 DaemonSet(DS)
        6 Job
        7 CronJob(CJ)
    Service详解
        1 Service介绍
            1.1 userspace 模式
            1.2 iptables 模式
            1.3 ipvs 模式
        2 Service类型
        3 Service使用
            3.1 实验环境准备
            3.2 ClusterIP类型的Service
            3.3 Endpoint
            3.4 HeadLiness类型的Service
            3.5 NodePort类型的Service
            3.6 LoadBalancer类型的Service
            3.7 ExternalName类型的Service
        4 Ingress介绍
        5 Ingress使用
            5.1 环境准备 搭建ingress环境
            5.2 准备service和pod
            5.3 Http代理
            5.4 Https代理
    数据存储
        1 基本存储
            1.1 EmptyDir
            1.2 HostPath
            1.3 NFS
        2 高级存储
            2.1 PV
            2.2 PVC
            2.3 生命周期
        3 配置存储
            3.1 ConfigMap
            3.2 Secret
    安全认证
        1 访问控制概述
        2 认证管理
        3 授权管理
        4 准入控制
    DashBoard
        1 部署Dashboard
        2 使用DashBoard
    附录
