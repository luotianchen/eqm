<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>企业质量管理系统</title>
    <base href="/">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" type="image/x-icon" href="favicon.ico">
    <style>li.ant-menu-item.ng-star-inserted{outline:0}html,body{min-width:1200px;overflow-x:auto}.hidden{display:none!important}#loaders{z-index:1;position:absolute;left:0;top:0;right:0;bottom:0;margin:auto;display:block;width:160px;height:50px}.object{width:11px;height:11px;background-color:#00142a;float:left;margin-top:15px;margin-right:15px;-moz-border-radius:50% 50% 50% 50%;-webkit-border-radius:50% 50% 50% 50%;border-radius:50% 50% 50% 50%;-webkit-animation:object 1s infinite;animation:object 1s infinite}.object:last-child{margin-right:0}.object:nth-child(9){-webkit-animation-delay:.9s;animation-delay:.9s}.object:nth-child(8){-webkit-animation-delay:.8s;animation-delay:.8s}.object:nth-child(7){-webkit-animation-delay:.7s;animation-delay:.7s}.object:nth-child(6){-webkit-animation-delay:.6s;animation-delay:.6s}.object:nth-child(5){-webkit-animation-delay:.5s;animation-delay:.5s}.object:nth-child(4){-webkit-animation-delay:.4s;animation-delay:.4s}.object:nth-child(3){-webkit-animation-delay:.3s;animation-delay:.3s}.object:nth-child(2){-webkit-animation-delay:.2s;animation-delay:.2s}@-webkit-keyframes object{50%{-ms-transform:translate(0,-50px);-webkit-transform:translate(0,-50px);transform:translate(0,-50px)}}@keyframes object{50%{-ms-transform:translate(0,-50px);-webkit-transform:translate(0,-50px);transform:translate(0,-50px)}}.hideSlow{-webkit-animation-name:fadeOut;-webkit-animation-duration:.3s;-webkit-animation-iteration-count:1;-webkit-animation-delay:0s;opacity:0}@-webkit-keyframes fadeOut{0%{opacity:1}25%{opacity:.75}50%{opacity:.5}75%{opacity:.25}100%{opacity:0}}</style>
</head>
<body>
<div id="loaders">
    <div class="object"></div>
    <div class="object"></div>
    <div class="object"></div>
    <div class="object"></div>
    <div class="object"></div>
</div>

<div id="ieTip" class="hidden" style="text-align:center;margin-top: 60px;">
    <img src="assets/ie.jpeg">
    <h2><strong>本系统只支持IE9以上版本及其他现代浏览器，你的浏览器版本太low了,已经和时代脱轨了 :(</strong></h2>
    <h2><strong>推荐使用:<a href='https://www.google.cn/chrome/' target='_blank' style='color:blue;'>谷歌</a>、<a href='http://www.firefox.com.cn/' target='_blank' style='color:blue;'>火狐</a>其他双核极速模式</strong></h2>
    <h2 style='margin:0'><strong>如果您的使用的是双核浏览器,请切换到极速模式访问</strong></h2>
</div>
<script>
    (function(window) {
        var theUA = window.navigator.userAgent.toLowerCase();
        if ((theUA.match(/msie\s\d+/) && theUA.match(/msie\s\d+/)[0]) || (theUA.match(/trident\s?\d+/) && theUA.match(/trident\s?\d+/)[0])) {
            var ieVersion = theUA.match(/msie\s\d+/)[0].match(/\d+/)[0] || theUA.match(/trident\s?\d+/)[0];
            if (ieVersion < 9) {
                document.getElementById("loaders").setAttribute("class","loaders hidden");
                document.getElementById("ieTip").setAttribute("class","");
            }
        }
    })(window);
</script>
<app-root></app-root>
<script type="text/javascript" src="static/runtime.js"></script><script type="text/javascript" src="static/polyfills.js"></script><script type="text/javascript" src="static/styles.js"></script><script type="text/javascript" src="static/vendor.js"></script><script type="text/javascript" src="static/main.js"></script></body>
</html>