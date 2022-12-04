今天内容:
        1.jsp简单介绍
        2.客户端会话技术--cookie
        3.服务器端会话技术部--session
        

---

复习:
		response:可以处理所有的http响应信息.
		常用API:
			setStatus(int code);
			setHeader()
			setDataHeader();
			setIntHeader();
			ServletOutputStream getOutputStream();
			PrintWriter getWriter();
			重定向  sendRedirect(String location); 注意:它是浏览器在一次发送请求，这时的路径要写"/工程名/资源名称"
			response.setContentType("text/html;charset=utf-8");
			request:可以处理所有的http请求信息.
			1请求方式
				getMethod();
			2.获取路径
				getRequestURI();
				getRequestURL();
				getProtocol();
				getRemoteAddr();获取客户机ip地址
				getContextPath();获取虚拟目录名称
			3.获取请求头
				getHeader();
				getHeaders();
				getHeaderNames();
				getDataHeader();
				getIntHeader();
			4.获取请求参数
				public String getParameter(String name)
					获取请求参数中指定名称的值.
				public String[] getParameterValues(String name);
				public Enumeration getParameterNames();
				public Map<String,String[]> getParametrMap();
			5.关于获取的数据校验
				1.非空校验
				2.规则校验	
			6.关于乱码问题:
				主要原因:请求数据的编码与服务器解码使了不同的编码表。
				new String(msg.getBytes("iso8859-1"),"utf-8");
				关于post请求方式可以直接:setCharacterEncoding();
			7.request域		
					请求转发：
					getParameter() 从请求信息中获取指定名称的值

​			getAttribute();它一般是在请求转发中使用，它是从request域中获取信息。

1.jsp
		jsp是什么？它有什么作用?
			jsp  java server page 这是用于展示信息操作。
		为了解决servlet展示信息的不方便，引入jsp。	
		jsp本质上也是一个servlet（******************）
		jsp就是在html页面中嵌入了java代码.
​		
​		jsp原理:查看图
​		
---
​		怎样在jsp中书写java代码:
​			
​			<%!  %> 它用于定义成员  可以声明成员属性，成员方法，内部类
​			<%   %> 它用于定义局部  声明的变量在局部位置  _jspservice方法中。
​			<%=  %> 它用于输出  out.print(内容);
​		
---
​	2.会话技术
​		什么是会话？它可以解决什么问题?
​			会话可简单理解为：用户开一个浏览器，点击多个超链接，访问服务器多个web资源，然后关闭浏览器，整个过程称之为一个会话。
​			
​			会话技术解决了http协议无状态。
​			简单说：使用会话技术，可以保存我们用户在一次会话过程中，所产生数据。
​				
​		java中的会话技术分成两种;
​			浏览器端会话技术:cookie
​			服务器端会话技术:session.
​			
​	---------------------------------------------
​	3.cookie会话技术
​		
​		Cookie类
​			1.构造方法
​				new Cookie(String name,String value);
​				要想将cookie信息携带到浏览器端，需要使用response.addCookie(Cookie c);
​				
​			2.要想在服务器端获取所有的cookie
​				Cookie[] cs=request.getCookies();
​				
​				Cookie类中提供方法
​				
​					getName(); 获取cookie名称					
​					getValue(); 获取cookie值
​			

示例：显示用户上一次访问时间

​		常用API:
​			new Cookie(String name,String value)
​			
​			getName()获取名称
​			getValue(); 获取值
​			
​			setMaxAge();Cookie创建时默认是会话级别，它是存储在浏览器内存中，如果关闭浏览器，这时cookie就被删除。
​			            使用这个方法，可以让cookie变成持久化，也就是说，可以保存在硬盘上的。 
​			
​			setPath();  setPath("/");
​				用于设置cookie在访问什么样的资源时会携带，简单就是设置cookie的路径 。
​				
​			删除cookie：
​				setMaxAge(0); 立即删除cookie
​				setMaxAge(-1);关闭浏览器后删除。
​				
​				注意事项:path一定要一致.
​				

---
示例:显示上次访问商品记录
		
---
复习:
	cookie是客户端的一种会话技术。
	会话：打开浏览器，进行一系列操作，一至到关闭浏览器这个过程叫会话技术。
	问题:关于cookie中携带中文。我们可以将中文的utf-8码存储到cookie中。
	示例:记住用户名.
		将汉字的码值存储到cookie中，在页面上通过javascript或通过jsp进行转码显示。
Session:
		服务器端的会话技术
		问题1:session怎样获取，怎样使用?
			session的类型是HttpSession.
			要想获取一个session可以通过 request.getSession()方法获取到.
			session是一个域对象。
				setAttribute()
				getAttribute();
				removeAttribute();
		问题2:session怎样保证一个浏览器有一个专属的session.
			通过将session的id值存储到cookie中，每一次请求时，都将会id 值传递到
			服务器端，服务器端通过request.gtSession()时会判断是否存在这样id值的

session对象，如果存在，会直接获取，如果不存在，会重新创建。

示例:使用session实现购物车.
​	----------------------------------------------------------
​	关于禁用cookie后，session失效问题:
​		因为session它是使用cookie来保存jsesionid值，如果禁用了cookie，session使用不了。
​		
​		解决方案:
​			我们人为将jsessionid的值添加到请求中。就可以。
​		    http://localhost:8080/day9_2/demo2;jsessionid=E4303E9F23E0EB23EEB13B318E9F1C2A
​			
​			手动完成这个事不合适：可以通过url重写完成。


​			
​			使用以下两个操作就可以完成url重写:
​				response. encodeRedirectURL(java.lang.String?url) 
​				用于对sendRedirect方法后的url地址进行重写。
​				response. encodeURL(java.lang.String?url)
​				用于对表单action和超链接的url地址进行重写
​				
​				注意：以上操作，如果检测到浏览器禁用了cookie,会在路径上添加jsessionid值。
​				否则不会添加。
​				
---
​	session失效问题:
​		
​		1.服务器停止  session一定没了。
​		2.session有默认销毁时间.
​				tomcat服务器的conf/web.xml文件中配置了session默认销毁时间
​			    <session-config>
​					<session-timeout>30</session-timeout>
​				</session-config>
​				
​		3.HttpSession类中提供一个方法.
​			invalidate()用于销毁当前的session。例如：注销操作就是使用它完成的。


​		
​		4.可以通过HttpSession来设置session过期时间。
​			public void setMaxInactiveInterval(int interval)
​			可以用于设置session过期时间.参数代表秒值。
​			
---
​	当我们关闭浏览器后，session就会销毁。
​		session的销毁与关闭浏览器无关，而关闭浏览器后，不能使用session是因为  cookie是会话级别的问题。
​		
---
​	示例:一次性验证码
​		
​		在ImageCodeServlet类，它就是一个验证码。图片上显示的信息都是我们人为设定的。
​		那么当图片产生时，我们可以将验证中的信息保存到session中。
​		
​		在注册(登录窗口)操作中，向servlet提交请求时，
​			request.getParameter()  获取输入的验证
​			request.getSession().getAttribute();去获取生成的验证码。
​			
​			request.getSession().removeAttribute();获取完成后，马上移除。
​			
---
​	关于Servlet中的三个域对象。
​		
​		1.ServletContext  作用域:整个web应用
​		2.HttpSession     作用域:一次会话
​		3.HttpServletRequest 作用域:在重定向时产生的请求链中。
​		
​		对于域对象常用操作
​			Object getAttribute(String name);
​			void setAttribute(String name,Object obj);
​			removeAttribute(String name);
​			
​		这三个域对象我们怎样使用？
​			能用request域解决的全都用它。
​			
---
​	作业:
​		完成一个注册登录操作.	


​		
---

​	RequestDispatcher 
​		forward()请求转发现。
​		include() 包含。
​		
​		include是用于执行包含操作，它一般用于设定框架。
​		注意：被包含的信息，一般情况下，只需要保留基本信息.


​			