<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>企业质量管理系统</title>
    <base href="/">
    <meta name="viewport" content="width=device-width, initial-scale=1 user-scalable=0">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico">
    <style>li.ant-menu-item.ng-star-inserted{outline:0}html,body{height: 100%;width: 100%;position: absolute;top: 0;bottom: 0;min-width:1200px;overflow:visible}.hidden{display:none!important}#loaders{z-index:1;position:absolute;left:0;top:0;right:0;bottom:0;margin:auto;display:block;width:160px;height:50px}.object{width:11px;height:11px;background-color:#00142a;float:left;margin-top:15px;margin-right:15px;-moz-border-radius:50% 50% 50% 50%;-webkit-border-radius:50% 50% 50% 50%;border-radius:50% 50% 50% 50%;-webkit-animation:object 1s infinite;animation:object 1s infinite}.object:last-child{margin-right:0}.object:nth-child(9){-webkit-animation-delay:.9s;animation-delay:.9s}.object:nth-child(8){-webkit-animation-delay:.8s;animation-delay:.8s}.object:nth-child(7){-webkit-animation-delay:.7s;animation-delay:.7s}.object:nth-child(6){-webkit-animation-delay:.6s;animation-delay:.6s}.object:nth-child(5){-webkit-animation-delay:.5s;animation-delay:.5s}.object:nth-child(4){-webkit-animation-delay:.4s;animation-delay:.4s}.object:nth-child(3){-webkit-animation-delay:.3s;animation-delay:.3s}.object:nth-child(2){-webkit-animation-delay:.2s;animation-delay:.2s}@-webkit-keyframes object{50%{-ms-transform:translate(0,-50px);-webkit-transform:translate(0,-50px);transform:translate(0,-50px)}}@keyframes object{50%{-ms-transform:translate(0,-50px);-webkit-transform:translate(0,-50px);transform:translate(0,-50px)}}.hideSlow{-webkit-animation-name:fadeOut;-webkit-animation-duration:.3s;-webkit-animation-iteration-count:1;-webkit-animation-delay:0s;opacity:0}@-webkit-keyframes fadeOut{0%{opacity:1}25%{opacity:.75}50%{opacity:.5}75%{opacity:.25}100%{opacity:0}}</style>
    <link rel="stylesheet" href="static/styles.fa15a2b118a64f83da65.css"></head>
<body>
<div id="loaders">
    <div class="object"></div>
    <div class="object"></div>
    <div class="object"></div>
    <div class="object"></div>
    <div class="object"></div>
</div>

<div id="ieTip" class="hidden" style="text-align:center;height: 510px;width: 100%;position:absolute; border-top:solid 80px;border-bottom:solid 80px;border-color: #001529">
        <a onclick="alert('This system only supports IE9 and above as well as other modern browsers such as Chrome 、Opera、Safari and Firefox. If you are using a dual-core browser, for instance, 360 Browser and QQ Browser, please switch to speed mode then refresh the page.')" style='color:#1890ff;position:absolute;right: 100px;top: 20px;'>English</a>
        <img src="assets/ie.jpeg" style="width: 350px;margin-top: 40px" draggable="false">
        <p>本系统只支持IE9及以上版本和其他现代浏览器，你的浏览器版本太low了,已经和时代脱轨了 :(</p>
        <p>推荐使用:<strong><span style="font-size: 16px"><a href='https://www.google.cn/chrome/' target='_blank' style='color:#1890ff;'>谷歌</a>、<a href='http://www.firefox.com.cn/' target='_blank' style='color:#1890ff;'>火狐</a></span></strong>及其他双核极速模式</p>
        <h3 style='margin:0'><strong>如果您的使用的是双核浏览器,请切换到极速模式访问</strong><small>（如何切换？<a href="https://jingyan.baidu.com/article/4f7d5712ffb6ce1a201927c4.html" target='_blank' style='color:#1890ff;font-size: 16px'>360浏览器</a>、<a href="https://jingyan.baidu.com/article/e75057f21f6dd3ebc81a8972.html" target='_blank' style='color:#1890ff;font-size: 16px'>QQ浏览器</a>）</small></h3>
    </div>
<script>
    (function(window) {
        console.log('%c\t\t\t\t\t\t\t\n\t企业质量管理系统\t\t⌃\t%c\t\t\t便捷安装\t\t全面管理\t\t功能强劲\t\t一应俱全\n%c\t\t\t\t\t\t\t%c\n\t\t\t\t\t\t\t\n\tCopyright © 2019\t\t%c\t\t\t\t欢迎使用企业质量管理系统\n%c\t\t\t\t\t\t\t\n%c\t\t\t\t\t\t\t\n\t雷恩博科技出品\t\t\t\n\t\t\t\t\t\t\t\n%c\t\t\t\t\t\t\t\n\t在线购买\t\t\t\t\t\n\t\t\t\t\t\t\t\n%c\t\t\t\t\t\t\t\n\t在线激活\t\t\t\t\t\n\t\t\t\t\t\t\t', 'color: #fff;background-color:#001529;font-size: 14px;font-weight: bold;line-height:16px','font-size:14px', 'color: #eee;background-color:#001529;font-size: 14px;font-weight: bold;line-height:16px','color: #eee;background-color:#000c17;font-size: 14px;font-weight: bold;line-height:16px','color:lightgreen;font-size:14px','color: #eee;background-color:#000c17;font-size: 14px;font-weight: bold;line-height:16px','color: #fff;background-color:#1890ff;font-size: 14px;font-weight: bold;line-height:16px','color: #eee;background-color:#000c17;font-size: 14px;font-weight: bold;line-height:16px', 'color: #eee;background-color:#000c17;font-size: 14px;font-weight: bold;line-height:16px');
        var theUA = window.navigator.userAgent.toLowerCase();
        if ((theUA.match(/msie\s\d+/) && theUA.match(/msie\s\d+/)[0]) || (theUA.match(/trident\s?\d+/) && theUA.match(/trident\s?\d+/)[0])) {
            var ieVersion = theUA.match(/msie\s\d+/)[0].match(/\d+/)[0] || theUA.match(/trident\s?\d+/)[0];
            if (ieVersion < 9) {
                document.getElementById("loaders").setAttribute("class","loaders hidden");
                document.getElementById("ieTip").setAttribute("class","");
                document.getElementById('ieTip').style.top = (Math.min(document.documentElement.clientHeight, document.body.clientHeight) - 670)/2+"px";
            }
        }
        if (/(iPhone|iPad|iPod|iOS|Android)/i.test(navigator.userAgent)) { //移动端
            document.getElementsByTagName('body')[0].style.minWidth = 320+"px";
            document.getElementsByTagName('html')[0].style.minWidth = 320+"px";
            document.getElementsByTagName('body')[0].className = "mobile";
        }else{
            document.getElementsByTagName('body')[0].className = "pc";
        }
    })(window);
</script>
<app-root></app-root>
<script type="text/javascript" src="static/runtime.2cfa6e7bc84229bec12b.js"></script><script type="text/javascript" src="static/polyfills.76a66040aea9937cacdf.js"></script><script type="text/javascript" src="static/main.54da6985a72475677d44.js"></script></body>
</html>
