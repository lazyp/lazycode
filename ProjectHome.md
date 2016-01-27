code component<br />
javascript Common function encapsulation<br />
javascript常用函数封装<br />
和一些学习资源的共享<br />

ajax封装/ajax.js这个文件是一个针对ajax的GET和POST请求的简单封装<br />
```
AjaxUtils.sendByGet(url , callback);
AjaxUtils.sendByPost(url , param , callback);
```
<font color='red'>
callback可以为一个函数，共ajax回调时处理一些事情<br />
也可以是一个类，但是必须得有个callback回调函数<br />
</font>
如：
```
handler ={
  callback:function(){},
  args : []
}
AjaxUtils.sendByGet(url , handler );
```
其中param post请求的param必须是键值对的js对象<br />