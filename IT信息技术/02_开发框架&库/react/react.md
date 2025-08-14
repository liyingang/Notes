2019-7-31  起始
props：表示组件接受的参数
render：渲染方法
state:状态
constructor：构造函数，必须存在props和super(props);
{}中写的时JS代码

```react
const element = (
<h1 className="greeting">
    Hello, world!
  </h1>
);
```
与
```react
const element = React.createElement(
  'h1',
  {className: 'greeting'},
  'Hello, world!'
);
```
完全等效

React 元素是不可变对象。一旦被创建，你就无法更改它的子元素或者属性。
一个元素就像电影的单帧：它代表了某个特定时刻的 UI。
React只进行部分更新


函数组件
```react
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}
```
该函数是一个有效的 React 组件，因为它接收唯一带有数据的 “props”（代表属性）对象与并返回一个 React 元素。这类组件被称为“函数组件”，因为它本质上就是 JavaScript 函数。

class组件
```react
class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}
```
state&生命周期
当组件第一次被渲染到 DOM 中的时候，就为其设置一个计时器。这在 React 中被称为“挂载（mount）”。
当 DOM 中组件被删除的时候，应该清除计时器。这在 React 中被称为“卸载（unmount）”。

state只能用setState函数更新，直接赋值不会更新组件

this.props和this.state是异步的，不要相互依赖
解决办法：state传入函数值
如
```react
this.setState((state, props) => ({
  counter: state.counter + props.increment
}));
```
组件可以选择把它的 state 作为 props 向下传递到它的子组件中：

```<h2>It is {this.state.date.toLocaleTimeString()}.</h2>```

在 React 中另一个不同点是你不能通过返回 false 的方式阻止默认行为。你必须显式的使用 preventDefault 

在 JavaScript 中，class 的方法默认不会绑定 this。如果你忘记绑定 this.handleClick 并把它传入了 onClick，当你调用这个函数的时候 this 的值为 undefined。通常情况下，如果你没有在方法后面添加 ()，例如 onClick={this.handleClick}，你应该为这个方法绑定 this。

// 为了在回调中使用 `this`，这个绑定是必不可少的
```this.handleClick = this.handleClick.bind(this);```

实验性的 public class fields 语法
```react
handleClick = () => {
    console.log('this is:', this);
  }
```
如果你没有使用 class fields 语法，你可以在回调中使用箭头函数：
```react
 render() {
    // 此语法确保 `handleClick` 内的 `this` 已被绑定。
    return (
      <button onClick={(e) => this.handleClick(e)}>
        Click me
      </button>
    );
  }
```
如果该回调函数作为 prop 传入子组件时，这些组件可能会进行额外的重新渲染。！！！降低效能！！！
React 的事件对象 e 会被作为第二个参数传递。如果通过箭头函数的方式，事件对象必须显式的进行传递，而通过 bind 的方式，事件对象以及更多的参数将会被隐式的进行传递。

### 传值方法

+   父传子

    ```react
    class Father extends Component {
        render(){
            return (
            	<div>
                	<Child name={"child"} />
                </div>
            )
        }
    }
    
    
    class Child extends COmponet{
        render(){
            return(
            	<div>
                	{this.props.name}
                </div>
            )
        }
    }
    ```

+   子传父

    ```react
    class Father extends Component {
        constructor(props){
            super(props);
            this.state={
                name:"null"
            }
        }
    	get=(name)=>{
            this.setState({
                name
            })
        }
        render(){
            return (
            	<div>
                	<Child toFather={this.get} />
                </div>
            )
        }
    }
    
    
    class Child extends COmponet{
        constructor(props){
            super(props);
            this.state={
                name:"default"
            }
        }
        render(){
            return(
            	<Button onClick={this.props.toFather.bind(this,this.state.name)}/>
            )
        }
    }
    ```

+   同级相传

    +   通过共同父类传递（略）
    +   通过PubSub
        1.  yarn add pubsub-js
        2.  import PubSub from ‘pubsub-js’
        3.  创建发送函数发布消息

    ```react
    class A extends COmponet{
        constructor(props){
            super(props);
            this.state={
                name:"default"
            }
        }
        
        send=()=>{
            PubSub.publish("evt",this.state.name);
        }
        
        render(){
            return(
            	<Button onClick={this.send} />
                
            )
        }
    }
    
    class B extends COmponet{
        constructor(props){
            super(props);
            this.state={
                name:"default"
            }
            PubSub.subscribe("evt",(msg,name)=>{
                this.setState({
                    name
                })
            })
        }
        
        render(){
            return(
                <p>{this.state.name}</p>
            )
        }
    }
    ```

# React-Router-dom

1.实现路由要现在最顶层容器上包围标签BrowserRouter或HashRouter

```react
import {BrowserRouter as Router} from 'react-router-dom'

ReactDOM.render(<Router><App /></Router>, document.getElementById('root'));

```

2.Route

```react
import React from 'react';
import Home from "./conponents/Home";
import {Route,Link,Redirect,withRouter} from 'react-router-dom'
import New from "./conponents/New";
import Phone from "./conponents/Phone";
import Fooda from "./conponents/Fooda";
import Foodb from "./conponents/Foodb";
function App(props) {
    {/* withRouter为组件提供props下history，location，match三个属性 */}
    {/* listen函数监听url变化 */}
    props.history.listen((url)=>{
        console.log(url.pathname);
    });
  return (
    <div className="App">
          {/* Link标签为超链接，to为url */}
         <Link to={"/home"}>toHome</Link>
         <Link to={"/a/link"}>toA</Link>
          {/* to可以传递对象，并用query接受参数 */}
         <Link to={{pathname:"/b",query:{name:"query:link"}}}>toB</Link>
          {/* Route为定义映射关系，path为路径，component为渲染的组件 exact为精确匹配 */}
        <Route path={"/"} exact component={Home}/>
        <Route path={"/home"} component={Home}/>
          {/* 含有参数的url，单个一般用:引出 */}
        <Route path={"/a/:id"} component={Fooda}/>
        <Route path={"/b"} component={Foodb}/>
          {/* 重定向，若访问/，则重定向到/a */}
         <Redirect from={"/"} to={"/a"} exact/>
          {/* 事件跳转，push内参数和to用法相同 */}
         <button onClick={()=>{props.history.push("/home")}}>go home</button>
         <button onClick={()=>{props.history.push("/a/button")}}>go a</button>
         <button onClick={()=>{props.history.push({pathname:"/b",query:{name:"query:button"}})}}>go b</button>
    </div>
  );
}
```

# redux













学习ant-ui
学习redux

