       /**
	    *@author hxl
		*@date 2011-03-11
		*sample：
		*HXL.drag("id" , [[0 , 10] , [0 , 50]] , true or false)();
		*注意：存在bug（边界未检测，当托出当前显示窗口的边界，然后拖动游览器的滚动条存在bug）
	    */
	   if (typeof window['HXL'] == 'undefined') 
            HXL = {};
        (function(){
            //     if (typeof HXL['drag'] == 'undefined') 
            //        HXL.drag = {};
            
            /**
             * drag函数里面定义了一个拖动事件的所有事件
             * 把drag函数定义为一个闭包，使里面的变量变为私有的，外界不能访问，更加安全，和面向对象
             * @param _moveId 需要添加浮动事件元素的id号
			 * @param _range 需要响应事件操作的范围:
			      a)._range[0]表示x坐标的最小值和最大值_range[0][0]:最小;_range[0][1]: 最大
		          b)._range[1]表示y坐标的最小值和最大值_range[1][0]:最小;_range[1][1]:最大
             * @param _scrollflag 是否浮动元素随着滚动条的滚动而滚动,true or false
             */
            HXL.drag = function(_moveId, _range, _scrollflag){
            
                if (arguments.length < 3) {
                    alert("error: arguments < 3");
                    throw new Error("arguments < 3");
                }
                
				/**
				 *判断_range是否为数组
				 */
                if (!_range instanceof Array && _range.length < 2 && _range[0].length < 2 && _range[1].length < 2) {
                    alert("error: _range is  illegality argument");
                    throw new Error("_range is  illegality argument");
                }
                
                var mousedownflag = 0;
                var x = 0;
                var y = 0;
                var mousedown = function(e){
                    //	alert("aa");
                    this.mousedownflag = 1;
                    //  this.x = e.offsetX ? e.offsetX : e.layerX;
                    //  this.y = e.offsetY ? e.offsetY : e.layerY;
                };
                var mousemove = function(e){
                
                    if (this.mousedownflag) {
                        //   alert(flag);
                        var movey = (e.offsetY ? e.offsetY : e.layerY) - this.y;
                        var movex = (e.offsetX ? e.offsetX : e.layerX) - this.x;
                        //   var scrollX = document.documentElement.scrollLeft;
                        // var scrollY = document.documentElement.scrollTop;
                        
                        var obj = document.getElementById(_moveId);
                        var top = obj.style.top;
                        var left = obj.style.left;
                        //obj.style.position = "absolute";
                        var xx = (movex + parseInt(left.substr(0, left.length - 2)));
                        var yy = (movey + parseInt(top.substr(0, top.length - 2)));
                        obj.style.left = xx + "px";
                        obj.style.top = yy + "px";
                    }
                };
                var mouseup = function(){
                    this.mousedownflag = 0;
                    //alert(obj.style.width);
                };
                var mouseout = function(){
                    this.mousedownflag = 0;
                };
                
                /**
                 *获得事件源对象
                 *@param e 事件对象
                 */
                var getTarget = function(e){
                    return e.target || e.srcElement;
                };
                
				/**
				 *检查发生事件源的位置是否在规定范围内
				 */
                var checkTarget = function(e){
                    if (getTarget(e).id != _moveId) 
                        return false;
                    
                    //记录按键的位置
                    this.x = e.offsetX ? e.offsetX : e.layerX;
                    this.y = e.offsetY ? e.offsetY : e.layerY;
                    // alert(_range.join(","));
					if(_range.length > 0){
                        if (this.x < _range[0][0] || this.x > _range[0][1] || this.y < _range[1][0] || this.y > _range[1][1])//判断发生事件的位置是否在规定的范围内部 
                            return false;
					}
                    
                    return true;
                };
                
                /**
                 * 外界真正调用的函数，调用拖动事件
                 */
                var handler = function(){
                    //   alert("dadsdsadsadsad");
                    //  alert(id);
                    
                    var obj = document.getElementById(_moveId);
                    obj.onmousedown = function(event){
                        event = event || window.event;
                        //alert(!!checkTarget(event));
                        if (!!checkTarget(event)) 
                            mousedown(event);
                    };
                    obj.onmousemove = function(event){
                        event = event || window.event;
                        mousemove(event)
                    };
                    obj.onmouseup = function(){
                        mouseup()
                    };
                    obj.onmouseout = function(event){
                        mouseout()
                    };
                    if (_scrollflag) {
                        /**
                         * 兼容IE和FireFox的onscroll滚动事件
                         */
                        window.onscroll = function(){
                            HXL.scrollEvent.fixedFloatDiv(_moveId);
                        };
                    };
                    
                    return this;
                };
                return handler;
            };
            /**
             * 处理拖动事件的类,封装了拖动过程发生的事件的处理函数
             * @param {Object} e
             */
            HXL.scrollEvent = {
                preScrollX: 0,
                preScrollY: 0,
                fixedFloatDiv: function(_moveId){
                    // alert('dadsa');
                    var obj = document.getElementById(_moveId);
                    var scrollX = 0;
                    var scrollY = 0;
                    // 如果浏览器支持 pageYOffset, 通过 pageXOffset 和 pageYOffset 获取页面和视窗之间的距离 ,chrome是这种方式
                    if (typeof window.pageYOffset != 'undefined') {
                        scrollX = window.pageXOffset;
                        scrollY = window.pageYOffset;
                    }
                    // 如果浏览器支持 compatMode, 并且指定了 DOCTYPE, 通过 documentElement 获取滚动距离作为页面和视窗间的距离
                    // IE 中, 当页面指定 DOCTYPE, compatMode 的值是 CSS1Compat, 否则 compatMode 的值是 BackCompat
                    else {
                        if (typeof document.compatMode != 'undefined' && document.compatMode != 'BackCompat') {
                            scrollX = document.documentElement.scrollLeft;
                            scrollY = document.documentElement.scrollTop;
                        }
                        // 如果浏览器支持 document.body, 可以通过 document.body 来获取滚动高度
                        else {
                            if (typeof document.body != 'undefined') {
                                scrollX = document.body.scrollLeft;
                                scrollY = document.body.scrollTop;
                            }
                        }
                    }
                    var top = obj.style.top;
                    var left = obj.style.left;
                    //	alert(parseInt(top.substr(0, top.length-2)));
                    obj.style.top = (parseInt(top.substr(0, top.length - 2)) + scrollY - this.preScrollY) + "px";//当前div块距离浏览器top的距离+（浏览器滚动条移动的距离=当前浏览器滚动的距离-上次浏览器滚动的距离）
                    obj.style.left = (parseInt(left.substr(0, left.length - 2)) + scrollX - this.preScrollX) + "px";
                    this.preScrollY = scrollY;//保存这次浏览器滚动的y轴长度
                    this.preScrollX = scrollX;//保存这次浏览器滚动的x轴长度
                }
            };
        })();