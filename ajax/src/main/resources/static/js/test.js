$(document).ready(function () {
    var ajaxHtml = $("#ajaxHtml");

    var param = {
        username: 'maoyz',
        age: 12
    };

    /* get请求 */
    $("#button1").off("click").on("click", function () {
        $.ajax({
            type: "GET",
            url: "/ajax/get",
            contentType: "application/json", // 发送数据到服务器时所使用的内容类型,默认是："application/x-www-form-urlencoded"
            data: param,   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据，xml、html、script、json、jsonp、text
            success: function (result, textStatus) {
                console.log(result);
                console.log(textStatus);  //success
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    /* get请求使用JSON.stringify() ，结果400错误*/
    $("#button2").off("click").on("click", function () {
        $.ajax({
            type: "GET",
            url: "/ajax/getJson",
            contentType: "application/json", // 发送数据到服务器时所使用的内容类型,默认是："application/x-www-form-urlencoded"
            data: JSON.stringify(param),   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据
            success: function (result) {
                console.log(result);
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    /* POST 默认表单请求, contentTyp一定要使用默认 */
    $("#button3").off("click").on("click", function () {
        $.ajax({
            type: "POST",
            url: "/ajax/postRequest",
            // contentType: "application/json", // 发送数据到服务器时所使用的内容类型,post请求时不能使用"application/json"
            data: param,   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据
            success: function (result) {
                console.log(result);
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    /* POST 默认表单请求, contentTyp一定要使用默认 */
    $("#button31").off("click").on("click", function () {
        $.ajax({
            type: "POST",
            url: "/ajax/postModel",
            data: param,   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据
            success: function (result) {
                console.log(result);
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    /* POST 默认表单请求, contentTyp一定要使用默认 */
    $("#button32").off("click").on("click", function () {
        $.ajax({
            type: "POST",
            url: "/ajax/postMap",
            data: param,   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据
            success: function (result) {
                console.log(result);
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    /*POST 请求JSON,使用JSON.stringify() */
    $("#button4").off("click").on("click", function () {
        $.ajax({
            type: "POST",
            url: "/ajax/postJsonString",
            contentType: "application/json;charset=UTF-8", // 发送数据到服务器时所使用的内容类型,JSON.stringify()必须使用"application/json"
            data: JSON.stringify(param),   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据
            success: function (result) {
                console.log(result);
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    /*POST 请求JSON,使用JSON.stringify() */
    $("#button41").off("click").on("click", function () {
        $.ajax({
            type: "POST",
            url: "/ajax/postJsonModel",
            contentType: "application/json;charset=UTF-8", // 发送数据到服务器时所使用的内容类型,JSON.stringify()必须使用"application/json"
            data: JSON.stringify(param),   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据
            success: function (result) {
                console.log(result);
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });

    /*POST 请求JSON,使用JSON.stringify() */
    $("#button42").off("click").on("click", function () {
        $.ajax({
            type: "POST",
            url: "/ajax/postJsonMap",
            contentType: "application/json;charset=UTF-8", // 发送数据到服务器时所使用的内容类型,JSON.stringify()必须使用"application/json"
            data: JSON.stringify(param),   // 发送到服务器的数据
            dataType: "json", // 返回JSON数据
            success: function (result) {
                console.log(result);
                ajaxHtml.html(result.data.username + ":" + result.data.age);
            },
            error: function (error) {
                console.log(error);
            }
        });
    });
});