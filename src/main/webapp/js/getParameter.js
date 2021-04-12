//根据传递过来的参数name获取对应的值
/*
这个正则是寻找&+url参数名字=值+&, &可以不存在.
^ 和 [^指定字符串] 之间的区别:
^ 指的是匹配字符串开始的位置
[^指定字符串] 指的是除指定字符串以外的其他字符串
match() 方法可在字符串内检索指定的值，或找到一个或多个正则表达式的匹配
()表示分组
 */
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
    var r = location.search.substr(1).match(reg);
    if (r!=null) {
         return (r[2]);
    }
    return null;
}