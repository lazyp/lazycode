// JavaScript Document
    if (typeof window['HXL'] == 'undefined') 
                HXL = {};
            (function(){
                HXL.tab = {};
                HXL.tab._encodeHtml = false;/*是否格式化html标签*/
                HXL.tab._contentId = '';/*传显示主内容元素的id值*/
                HXL.tab.setEncodeHtml = function(b){
                    HXL.tab._encodeHtml = b;
                };
                HXL.tab.setContentId = function(id){
                    HXL.tab._contentId = id;
                };
				/**
				 *改变显示内容
				 *@contentId 显示内容元素的id值
				 *@content 可以说字符串或者函数
				 *   说明：一般是在content函数里面再调用change，此时传content为字符串
				 */
                HXL.tab.change = function(contentId, content, encodeHtml){
                    var contentStr = '';
                    if (typeof content == 'function') {
                        // if (typeof content.callback == 'undefined') 
                        content();
                        return;
                        //   else {
                        //  
                        //   }
                    
                    }
                    else 
                        if (typeof content == 'string') 
                            contentStr = content;
                    if (typeof encodeHtml == 'undefined' || encodeHtml == null) 
                        encodeHtml = this._encodeHtml;
                    
                    if (!!encodeHtml) {
                        content = HXL.util._replaceAll(content, [/&/g, /"/g, /</g, />/g], ['&amp', '&quot;', '&lt;', '&gt;']);
                    }
					if(contentId == null || typeof contentId == 'undefined')
					    contentId = this._contentId;
						
                    HXL.util._$(contentId).innerHTML = contentStr;
                };
                HXL.tab.event = function(ulId, contentId, cssId, content, encodeHtml){
                
                    HXL.util._$(ulId).onmouseover = function(e){
                        var obj = HXL.util._$(cssId);//获得目前显示菜单对象
                        e = e || window.event;
                        var target = e.srcElement || e.target;
                        
                        if (typeof target.nodeName != 'undefined' && (target.nodeName.toLowerCase() == 'li' || target.nodeName.toLowerCase() == 'ol')) {
                            HXL.util._removeAttribute(obj, 'id');//移除目前显示菜单对象的cssid
                            HXL.util._addAttribute(target, 'id', cssId);//给当前鼠标停留的菜单添加cssid
                            // alert(typeof content);
                            //if (content != null || typeof content != 'undefined') 
                            HXL.tab.change(contentId, content, encodeHtml);//改变显示的内容
                            //  else 
                            //    if (typeof content == 'function') 
                            //     content();
                            //    else 
                            //   if (typeof content == 'object') 
                            //       new content();
                        }
                    };
                }
            })();