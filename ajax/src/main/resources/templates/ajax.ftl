<title>ajax请求</title>

<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="/js/jquery-3.1.1.js"></script>
<script type="text/javascript" charset="utf8" src="/js/jquery-validation/dist/jquery.validate.js"></script>
<script type="text/javascript" charset="utf8" src="/js/jquery-validation/dist/localization/messages_zh.js"></script>
<script type="text/javascript" charset="utf8" src="/js/test.js"></script>


<form id="form1">
    姓名：<input type="text" name="username"/>
    年龄：<input type="text" name="age"/>
    <button type="button">提交</button>
</form>

<button type="button" id="button1">ajaxGet</button>
<br>
<button type="button" id="button10">ajaxGetParam</button>
<br>
<button type="button" id="button11">ajaxGetMap</button>
<br>
<button type="button" id="button111">ajaxGetMapNoRequestParam</button>
<br>
<button type="button" id="button12">ajaxGetModel</button>
<br>
<button type="button" id="button2">ajaxGetJson</button>
<br>
<button type="button" id="button21">ajaxGetJsonParam</button>
<br>
<button type="button" id="button3">postRequest</button>
<br>
<button type="button" id="button31">postModel</button>
<br>
<button type="button" id="button310">postModelConsumes</button>
<br>
<button type="button" id="button32">postMap</button>
<br>
<button type="button" id="button4">postJsonString</button>
<br>
<button type="button" id="button41">postJsonModel</button>
<br>
<button type="button" id="button410">postJsonModelNoRequestBody</button>
<br>
<button type="button" id="button42">postJsonMap</button>
<br>
<button type="button" id="button5">postJackson</button>
<br>
<hr>
<div id="ajaxHtml"></div>