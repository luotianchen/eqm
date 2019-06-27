<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>欢迎使用企业质量管理系统</title>
    <link rel="icon" type="image/x-icon" href="/assets/favicon.ico">
    <style>
        html,body{
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            min-width: 1092px;
            overflow-x: auto;
            font-family:  Helvetica, Tahoma, Arial, "PingFang SC", "Hiragino Sans GB", "Heiti SC", "Microsoft YaHei", "WenQuanYi Micro Hei", sans-serif;
        }
        ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
            color:    #BBB;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:    #BBB;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    #BBB;
        }
        :-ms-input-placeholder { /* Internet Explorer 10-11 */
            color:    #BBB;
        }
        a{
            color: #FFF;
            text-decoration: none;
        }
        .header{
            width: 100%;
            height: 80px;
            font-size: 16px;
            line-height: 80px;
            background-color: #001529;
            display: inline-block;
        }
        .logo{
            color: #FFF;
            margin-left: 40px;
            height: inherit;
            display: inline;
            font-weight: 500;
            float: left;
        }
        .logo h1{
            height: inherit;
            display: inline;
        }
        .header .nav{
            display: inline-block;
            margin-top: -2px;
            margin: 0;
            padding: 0;
            margin-left: 40px;
            float: left;
        }
        .header .nav li .submenu{
            display: none;
        }
        .header .nav li:hover .submenu{
            z-index: 100000000000000;
            display: block;
            float: right;
            width: 130px!important;
        }
        .header .nav li:hover .submenu li{
            width: 130px!important;
        }
        .header ul li{
            display: inline-block;
            width: 130px;
            text-align: center;
            color: #FFF;
            font-weight: 400;
            height: 80px;
            cursor: pointer;

        }
        .header ul li a{
            display: block;
            width: 130px;
            height: 80px;
            font-weight: 500;
        }
        .header .nav li:hover{
            background-color: #1890ff
        }
        .header .nav li.active{
            height: 76px;
            border-bottom: solid 4px rgb(0,151,255);
            margin-right: -5px;
        }
        .header .nav li.active a{
            height: 76px;
            background: #424c54;
        }

        @font-face {
            font-family: 'iconfont';  /* project id 313968 */
            src: url('https://at.alicdn.com/t/font_ly12yy1nf622zkt9.eot');
            src: url('https://at.alicdn.com/t/font_ly12yy1nf622zkt9.eot?#iefix') format('embedded-opentype'),
            url('https://at.alicdn.com/t/font_ly12yy1nf622zkt9.woff') format('woff'),
            url('https://at.alicdn.com/t/font_ly12yy1nf622zkt9.ttf') format('truetype'),
            url('https://at.alicdn.com/t/font_ly12yy1nf622zkt9.svg#iconfont') format('svg');
        }
        html, body, div, span, applet, object, iframe,
        h1, h2, h3, h4, h5, h6, p, blockquote, pre,
        a, abbr, acronym, address, big, cite, code,
        del, dfn, em, img, ins, kbd, q, s, samp,
        small, strike, strong, sub, sup, tt, var,
        b, u, i, center,
        dl, dt, dd, ol, ul, li,
        fieldset, form, label, legend,
        table, caption, tbody, tfoot, thead, tr, th, td,
        article, aside, canvas, details, embed,
        figure, figcaption, footer, header, hgroup,
        menu, nav, output, ruby, section, summary,
        time, mark, audio, video {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 100%;
            font: inherit;
            vertical-align: baseline;
        }
        ol, ul {
            list-style: none;
        }
        input:focus {
            outline: none;
        }
        .diviver{
            width: 70%;
            margin-left: 15%;
            text-align: center;
            color: #444;
            font-size: 20px;
            margin-top: 20px;
            border-bottom: solid 3px #CCC;
        }
        .diviver .underline{
            margin-top: 15px;
            margin-left: 40%;
            margin-bottom: -1px;
            width: 20%;
            background-color: #0692e1;
            height: 4px;
            box-shadow: 1px 0 0 #000;
        }
        footer{
            background-color: #001529;
            width: 100%;
            height: 250px;
            line-height: 180px;
            margin: 0;
        }
        .footer-logo{
            padding-top: 72px;
        }
        .footer-logo h1{
            margin-left: 115px;
            color: #FFF!important;
            font-size: 30px;
            margin-bottom: 20px;
            line-height: 40px;
        }
        .footer-logo p{
            margin-left: 115px;
            width: 500px;
            color:#FFF;
            line-height: 20px;
        }
        .footer .banquan{
            margin-left: 115px;
            color: #747474;
            margin-top: -40px;
        }
        .title{
            padding-left: 170px;
            padding-right: 170px;
            margin-top: 20px;
            margin-bottom: 20px;
        }
        .text{
            padding-left: 170px;
            padding-right: 170px;
            margin-top: 10px;
            font-size: 14px;
            color: #747474;
            margin-bottom: 10px;
        }
        .text strong{
            color:#000;
            font-size: 15px;
        }
        .indent{
            padding: 20px;
            border:1px #999 dashed;
            background: #f6f6f6;
            width: calc(100% - 340px);
            margin-left: 170px;
        }
        .indent .text{
            padding:0;
        }
    </style>
</head>
<body  style="background-color: #f5f7fa">
<header>
    <div class="header">
        <div class="logo" style="cursor: pointer;" onclick="window.location.assign('/')">
            <img src="assets/logo.png" style="width: 30px;margin-bottom: -7px">企业质量管理系统
        </div>
        <ul class="nav">
            <li> <a href="/install">在线激活</a></li>
        </ul>
    </div>
</header>

<div style="width: 80%;margin-left: 10%;padding-top: 30px;margin-top: 40px;padding-bottom: 30px;color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;">
    <div style="text-align: center">恭喜您已经安装成功，激活后即可开始使用此系统！</div>
</div>
<div style="width: 80%;margin-left: 10%;background-color: #FFF;padding-top: 30px;margin-top: 40px;padding-bottom: 30px">
    <div class="diviver" style="margin-top: 30px;">
        <div>- 常见问题 -</div>
        <div class="underline"></div>
    </div>
    <p class="title">1.如何激活？</p>
    <p class="text">输入localhost/install后会弹出以下界面（服务器端），在路径选择栏（第一栏）选择安装位置下apache-tomcat-8.5.40\webapps文件夹，在激活码输入框输入购买的激活码，点击激活按钮后提示激活成功则完成激活步骤。</p>
    <p style="text-align: center">
        <img src="assets/install/install-1.png">
    </p>
    <p class="title">2.产品质量计划、产品材料清单等pdf无法正常生成</p>
    <p class="text">首先请按照下列提示完成JAVA环境变量配置，若仍然不能使用，请检查是否已在服务器安装Microsoft Office产品（2007版需安装excel->SaveAsPDFandXPS组件）</p>
    <div class="indent">
        <p class="text"><strong>1、新建系统变量</strong></p>
        <p class="text">我的电脑（或计算机、此电脑）右键选择属性，高级设置，环境变量。点击选择【新建系统变量】--弹出“新建系统变量”对话框，在“变量名”文本框输入“JAVA_HOME”,在“变量值”文本框输入软件的安装路径加上"\apache-tomcat-8.5.40\bin\Java\jdk1.8.0_181"。</p>
        <p class="text">例如：C:\Program Files\eqm\apache-tomcat-8.5.40\bin\Java\jdk1.8.0_181</p>
        <p class="text"><strong>2、path变量增加值</strong></p>
        <p class="text">在“系统变量”选项区域中查看PATH变量，如果不存在，则新建变量 PATH，否则选中该变量，单击“编辑”按钮，在“变量值”文本框的起始位置添加“%JAVA_HOME%\bin;%JAVA_HOME%\jre\bin;”或者是直接“%JAVA_HOME%\bin;”，单击确定按钮。建议复制到记事本中进行编辑。</p>
        <p class="text"><strong>3、CLASSPATH 变量值的增加</strong></p>
        <p class="text">在“系统变量”选项区域中查看CLASSPATH 变量，如果不存在，则新建变量CLASSPATH，否则选中该变量，单击“编辑”按钮，在“变量值”文本框的起始位置添加“.;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar;”。</p>
    </div>
    <p class="title">3.登录时提示"权限信息初始化失败"</p>
    <p class="text">系统尚未启动完时进入系统会提示此问题，等待系统启动完毕后重新登录即可。</p>
</div>
<footer style="margin-top: 40px">
    <div class="footer">
        <div class="footer-logo">
            <h1>企业质量管理系统</h1>
            <div class="banquan">Copyright ©2019 雷恩博科技 版权所有</div>
        </div>
    </div>
</footer>
</body>
</html>