


# get 和 post 请求



## 解释表单提交数据原理

### HTML 标签提交数据条件
1. HTML 元素中，只有 表单元素 才可以向服务器提交数据
2. 表单元素 需要放在 form 元素内
3. 表单元素 必须具有 name 属性


### HTML FORM ELEMENT （HTML表单元素）
- 只有放在 form 表单中的 & 带name属性的 & HTML表单元素，才有资格向服务器提交数据。
- 但并不是所有的HTML表单元素都能提交数据，比如`<label></labele>`标签就不行。
- [表单元素参考链接](https://developer.mozilla.org/en-US/docs/Web/HTML/Element#Forms)

![HTML Form Elements](htmlFormEl.png)




## get 请求

### 特点
1. 没有请求报文体
2. 请求参数都在url中以键值对的方式进行传递。例如：`http://localhost:9000/?name=steve&age=18`

```http
GET /?name=steve&age=18 HTTP/1.1
Host: foo.com
Connection: keep-alive
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate, sdch
Accept-Language: zh-CN,zh;q=0.8,en;q=0.6
```


### get 请求参考解释
```
1. The GET method is the method used by the browser to ask the server to send back a given resource: "Hey server, I want to get this resource." In this case, the browser sends an empty body. Because the body is empty, if a form is sent using this method the data sent to the server is appended to the URL.

2. The data is appended to the URL as a series of name/value pairs. After the URL web address has ended, we include a question mark (?) followed by the name/value pairs, each one separated by an ampersand (&).
```





## post 请求

### 特点
1. 请求 url 中没有参数
2. 有请求报文体
3. 可以发送大量数据到服务器

```http
POST / HTTP/1.1
Host: foo.com
Connection: keep-alive
Content-Length: 13
Cache-Control: max-age=0
Origin: null
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36
Content-Type: application/x-www-form-urlencoded
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.8,en;q=0.6

name=steve&age=18
```


### post 请求参考解释：
```
1. The POST method is a little different. It's the method the browser uses to talk to the server when asking for a response that takes into account(考虑到) the data provided in the body of the HTTP request: "Hey server, take a look at this data and send me back an appropriate result." If a form is sent using this method, the data is appended to the body of the HTTP request.

2. When the form is submitted using the POST method, you get no data appended to the URL

3. If you need to send a password (or any other sensitive piece of data), never use the GET method or you risk displaying it in the URL bar, which would be very insecure.

4. If you need to send a large amount of data, the POST method is preferred because some browsers limit the sizes of URLs. In addition, many servers limit the length of URLs they accept.
```

### 注意

post 请求因为提交的数据量可能会比较大，所以可能会分多次进行上传数据，所以服务器也会采用一点一点接收数据的方式。




## get 请求 和 post 请求的区别（参考连接）
- https://www.oschina.net/news/77354/http-get-post-different
- http://www.w3school.com.cn/tags/html_ref_httpmethods.asp
- http://www.cnblogs.com/hyddd/archive/2009/03/31/1426026.html



## node.js中通过代码获取 get 请求 和 post 请求提交的数据


### get 请求具体实现思路
```javascript
// 1. 加载url模块
var url = require('url');

var server = http.createServer(function (req, res) {
  // 1. 获取用户请求的url, 并转化为url对象
  var urlObj = url.parse(req.url, true);
  // 获取用户请求中的url部分, 不含参数
  // 比如：用户请求的地址是 '/index?id=10&name=steve', pathname 获取到的就是 '/index'
  reqUrl = urlObj.pathname.toLowerCase();

  // 获取查询字符串（get 请求的参数）对象
  // {id: 10, name: 'steve'}
  var query = urlObj.query;
});


// 开启监听服务
server.listen(9000, function () {
  // body...
  console.log('please visit: http://localhost:9000');
});
```



### post 请求具体实现思路：
```javascript
// 1. 为request对象注册data事件，接收用户提交过来的post数据
// 2. 为requ对象注册end事件，当数据接收完毕后对数据进行操作

 // 创建一个空的Buffer对象
 var array = [];

 // 每次接到用户的数据都拼接到 array 中
 req.on('data', function (chunk) {

   // 每个chunk就是一个buffer对象
   // 把每个buffer对象都push到array数组中
   array.push(chunk);
 });

 // 数据接收完毕后，将 buffers 中的内容转换为字符串
 req.on('end', function () {

   var buffers = Buffer.concat(array);
   
   // 直接输出buffer对象
   // console.log(buffers);

   // 将buffer对象转换为字符串，然后输出
   // console.log(buffers.toString('utf8'));

   // 将提交过来的请求字符串"title=aaa%E5%95%8A%E5%95%8A&url=bbb&text=cccc"，转换为json对象
   // 通过Node.js内置模块querystring来解析，将请求的"查询字符串" 解析为对象
   var reqBody = querystring.parse(buffers.toString('utf8'));
 });
```