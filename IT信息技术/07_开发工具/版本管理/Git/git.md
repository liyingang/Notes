<h1>
    <center>git</center>
</h1>

 git --version 可查看当前git版本 



 **配置用户名和邮箱:** 

```bash
git config --global user.name "Your Name"  
git config --global user.email "email@example.com"  
```

 使用 --global 修饰后设置的全局的用户,如果设置单个项目的用户,可cd到项目根目录下,执行如下命令: 

```bash
git config user.name "Your Name"  
git config user.email "email@example.com" 
```

 使用命令:git config --list 可查看当前用户信息以及其他的一些信息 

```bash
$ git config --list  
core.excludesfile=/Users/mac/.gitignore_global  
difftool.sourcetree.cmd=opendiff "$LOCAL" "$REMOTE"  
difftool.sourcetree.path=  
mergetool.sourcetree.cmd=/Applications/SourceTree.app/Contents/Resources/
			opendiff-w.sh "$LOCAL" "$REMOTE" -ancestor "$BASE" -merge "$MERGED"  
mergetool.sourcetree.trustexitcode=true  
http.postbuffer=524288000  
https.postbuffer=524288000  
user.email=你的邮箱@qq.com  
user.name=你的用户名  
macdeMacBook-Pro:~ Artron_LQQ$   
```

gti init 创建了一个空的本地仓库. 



 将项目的所有文件添加到缓存中:  

​		git add .  

 将缓存中的文件Commit到git库 

​	 git commit -m "添加你的注释,一般是一些更改信息" 

 不添加注释 git commit  ,但是这样会进入vim(vi)编辑器 



 将本地的库链接到远 

 	git remote add origin HTTPS链接 

 上传代码到远程库,上传之前最好先Pull一下 

​	 git pull origin master 

 将代码成功提交到远程库

​	 git push origin master 

**分支管理**

新建分支

```
$ git branch newbranch
```

查看分支

```
$ git branch
```

输出:

```
* master
  newbranch
```

*代表当前所在的分支

切换分支

```
$ git checkout new branch
```

输出

```
Switched to branch 'newbranch'
```

切换后可用git branch查看是否切换到当前分支

```
master
* newbranch
```

提交改动到当前分支

```
$ git add .
$ git commit -a
```

可使用git status查看提交状态

接着切回主分支

```
$ git checkout master
```

输出:

```
Switched to branch 'master'
```

将新分支提交的改动合并到主分支上

```
$ git merge newbranch
```

