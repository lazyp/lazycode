
/**
 * cookie操作工具类
 * <pre>
 *   使用范例:
 *   var cookie = new HXL.cookies();
 *   cookie.addCookie('username' , '123' , null);//or cookie.addCookie('username','123');
 *   cookie.delCookie('username');
 *   cookie.getCookie('username');
 *   cookie.modfiyCookie('username','value' , {expireDays :  30 , path : '/index.jsp'});
 * </pre>
 * @author hxl
 */
if (typeof window['HXL'] == 'undefined') 
    HXL = {};
(function(){
    HXL.cookies = function(){
        var cookiesAr = document.cookie.split(";");//私有
        if (typeof cookiesAr != 'undefined' && cookiesAr != null && cookiesAr != '') {
            var len = cookiesAr.length;
            for (var i = 0; i < len; i++) {
                var cookie = cookiesAr[i].split("=");
                this.Cookies[cookie[0]] = cookie[1];
            }
        }
        
    };//构造
    HXL.cookies.prototype = {
        Cookies: {},//储存cookies值的对象
        setCookie: function(name, value, options){
            var str = name + "=" + escape(value);
            var flag = 0;//0表示添加,-1表示删除
            if (options) {
                if (options.expireDays) {
                    var date = new Date();
                    date.setTime(date.getTime() + options.expireDays * 24 * 3600 * 1000);
                    str += ";expires=" + date.toGMTString();
                }
                for (var o in options) {
                    if (o == "expireDays") 
                        continue;
                    str + ";" + o + "=" + options[o];
                    if (options.expireDays < 0) {
                        this.Cookies[o] = undefined;
                        flag = -1;
                    }
                    else 
                        this.Cookies[o] = options[o];
                }
            }
            if (flag == 0) 
                this.Cookies[name] = escape(value);
            else 
                if (flag == -1) 
                    this.Cookies[name] = undefined;
            document.cookie += str;
        },
        addCookie: function(name, value, options){
            this.setCookie(name, value, options);
        },
        getCookie: function(name){
            if (this.Cookies[name]) 
                return unescape(this.Cookies[name]);
            return "";
        },
        modifyCookie: function(name, value, options){
            this.setCookie(name, value, options);//调用setCookie方法，修改cookie属性，其实就是重新赋值
        },
        delCookie: function(name){
            this.setCookie(name, "", {
                expireDates: -1
            });//删除cookie的值，其实就是将该值设置为过期的一个时间，所以还是调用setCookie方法
        }
    }
})();
