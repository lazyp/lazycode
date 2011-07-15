
/**
 * 发送Ajax请求
 * @author hxl
 */
(function(){

    AjaxUtils = {
        async: true,
        postSeparate: '\n',
        createXHR: function(){
            var xhr;
            var XMLHTTP = ["Msxml2.XMLHTTP.6.0", "Msxml2.XMLHTTP.5.0", "Msxml2.XMLHTTP.4.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"];
            if (window.XMLHttpRequest) 
                xhr = new XMLHttpRequest();
            else 
                if (window.ActiveXObject) {
                    for (var i = 0; i < XMLHTTP.length; i++) {
                        try {
                            xhr = new ActiveXObject(XMLHTTP[i]);
                            break;
                        } 
                        catch (ex) {
                        }
                    }
                }
            return xhr;
        },
        
        sendByGet: function(url, callback){
            var xhr = this.createXHR();
            xhr.open("GET", url, this.async);
            xhr.send(null);
            xhr.onreadystatechange = function(){
                AjaxUtils.stateChange(xhr, callback);
            };
        },
        /**
         *
         * @param {String} url
         * @param {Object} params 键值对形式
         * @param {Object} callback
         */
        sendByPost: function(url, params, callback){
            var xhr = this.createXHR();
            xhr.open("POST", url, this.async);
            xhr.setRequestHeader("content-type", "text/plain");//后台解析必须的以流的形式解析
            var paramStream = '';
            for (var key in params) {
                key = encodeURIComponent(key);
                var value = encodeURIComponent(params[key]);
                
                paramStream += (key + '=' + value + this.postSeparate);
            }
            xhr.send(paramStream);
            xhr.onreadystatechange = function(){
                AjaxUtils.stateChange(xhr, callback);
            };
        },
        stateChange: function(xhr, args){
        
            var argar = [];
            if (typeof args != 'undefined' && args != null) {
                var callback = null;
                
                if (typeof args == 'function') 
                    callback = args;
                else {
                    if (typeof args == 'object') {
                    
                        callback = args.callback || null;
                        for (arg in args) {
                            if (arg == 'callback') 
                                continue;
                            argar.push(args[arg]);
                        }
                    }
                }
            }
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    var reply = xhr.responseText;
                    var replyar = [];
                    replyar = eval(reply);//解析json字符串为数组对象
                  //  argar.unshift(replyar);//IE下面无法正常工作
                    if (typeof callback == 'function') {
                     //   eval(callback.apply(window, argar));
                    	eval(callback.apply(window, replyar));
                    }
                }
            }
        }
    }
    
    
})();
