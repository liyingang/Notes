# yarn  

yarn其一是为了解决npm多次下载同一包的问题

yarn安装时会检查nodejs，有时会卡一下

## 基本操作：

### 安装成功：

yarn --version

### 初始化项目：

yarn init

输入后出现

question name (Test): 
		question version (1.0.0):
		question description: test
		question entry point (index.js):
		question repository url:
		question author: li
		question license (MIT):
		question private:
		success Saved package.json
		Done in 61.09s.
		You can type answers for each of these or you can just hit enter/return to use the default or leave it blank.
		目标文件下会生成一个package.json

add, upgrade, or remove dependencies will automatically update package.json and yarn.lock files.

对于@后的version,有明确版本如1.2.3；有小版本范围依赖如~1.2.3,意思为下载小版本最近的更新，即1.2.X；有大版本范围依赖如^1.2.3,意思为下载大版本的最近更新，即1.X.X。

tag：举个例子 beta,next, or latest
### 添加依赖：  
yarn add [package]
		yarn add [package]@[version]
		yarn add [package]@[tag]

### 添加到不同项目级别：
​	yarn add [package] --dev/-D   #devDependencies
​			yarn add [package] --peer/-P   #peerDependencies
​			yarn add [package] --optional/-O   #optionalDependencies

解释^和~，翻译水平有限，直接贴上原味解释

**^**   Using --exact or -E installs the packages as exact versions. The default is to use the most recent release with the same major version. For example, yarn add foo@1.2.3 would accept version 1.9.1, but yarn add foo@1.2.3 --exact would only accept version 1.2.3.

**~**   Using --tilde or -T installs the most recent release of the packages that have the same minor version. The default is to use the most recent release with the same major version. For example, yarn add foo@1.2.3 --tilde would accept 1.2.9 but not 1.3.0.

###依赖升级：
​	yarn upgrade ...

###移除依赖：
​	yarn remove [package]

### 安装项目全部依赖：

Installing all dependencies

yarn
		or  yarn install

Installing one and only one version of a package: 
		yarn install --flat

Forcing a re-download of all packages: 
		yarn install --force

Installing only production dependencies: 
		yarn install --production

## 设置镜像源

1、查看一下当前源

```
`yarn config get registry`
```

2、切换为淘宝源

```
`yarn config set registry https:``//registry.npm.taobao.org`
```

3、或者切换为自带的

```
`yarn config set registry https:``//registry.yarnpkg.com`
```

### 工作流:

#### 基本工作流：

1.创建一个新项目
		2.添加/更新/删除依赖项
		3.安装/重新安装依赖项
		4.使用版本管理工具（例如 git）
		5.持续集成