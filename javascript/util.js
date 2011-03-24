
/**
 * @author hxl
 */
if (typeof window['HXL'] == 'undefined') 
    HXL = {};
(function(){
    HXL.util = {};
    HXL.util = {
        /**
         * @param {Object} str 目标字符串
         * @param {Object} oldStr 需要替换的子字符串或者是一个数组
         * @param {Object} newStr 代替旧子字符串的新子字符串或者是一个数组
         */
        _replaceAll: function(str, oldStr, newStr){
            if (oldStr instanceof Array) {
                var len = oldStr.length;
                if (newStr instanceof Array) {
                    if (newStr.length == len) {
                        for (i = 0; i < len; i++) {
                            str = str.replace(/oldStr[i]/g, newStr[i]);
                        }
                    }
                    else {
                        alert("newStr is Invalid argument.");
                        throw new Error("newStr is Invalid argument.");
                    }
                }
                else {
                    for (i = 0; i < len; i++) {
                        str = str.replace(/oldStr[i]/g, newStr);
                    }
                }
                return str;
            }
            else {
                if (newStr instanceof Array) 
                    throw new Error("newStr is Invalid argument");
                return str.replace(/oldStr/g, newStr);
            }
            
        },
        /**
         * 获得id元素对象
         * @param {Object} id
         */
        _$: function(id){
            return document.getElementById(id);
        },
        _removeAttribute: function(obj, attributeName){
            // alert(obj.getAttribute('id'));
            obj.setAttribute(attributeName, '');
        },
        _addAttribute: function(obj, attributeName, attributeValue){
            obj.setAttribute(attributeName, attributeValue);
        }
		/**
		 *将一个number数据强制转化为int
		 */
		_caseInt : function(v){
			if(typeof v != "number")
			   return v;
			return v >> 0;
		}
    }
})();
