# 远程连接

1. 镜像源：http://mirrors.aliyun.com/ubuntu/
2. 选择lvm

3. 查看本机ip   ip a

    如果没有网卡en33，配置如下：
    	若ubuntu为16.04版本：请查阅有关资料
    	若ubuntu为18.04版本：
    		1.vim /etc/netplan/50-cloud-init.yaml
    		2.填入以下信息并保存：
    			network:
    			    ethernets:
    			        ens33:
    			          	addresses: [192.168.254.2/24]
    			          	gateway4: 192.168.254.254
    			          	nameservers: 
    			            	addresses: [114.114.114.114]
    			            	search: []
    			          	dhcp4: no
    			          	optional: true
    			    version: 2

      		4. 重启服务：netplan apply

# 让root用户远程连接

1. vim /etc/ssh/sshd_config
2. 启用PermitRootogin
3. 将后面的值改为yes，保存，退出
4. 重启服务：service ssh restart



