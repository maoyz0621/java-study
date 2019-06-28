## GET请求
+ get请求时, Accept: application/json, text/javascript, \*/\*; 以表单形式提交; 所有的请求参数都是/path?username=maoyz&age=12;
+ HttpServletRequest接收参数, 接收的参数值类型都是String类型; request.getParameterMap() 获取所有请求参数;
+ Map 接收参数, 必须加@RequestParam, 否则Map无法获取请求参数值
+ POJO 接收参数值
+ @RequestParam Param接收请求参数
+ get请求参数通过JSON.stringify(param), 请求时,400错误;

## POST请求

### form请求 
+ 请求体: username=maoyz&age=12
+ request 可以正常获取参数值,同get请求一样;
+ 请求数据类型默认设置: contentType: "application/x-www-form-urlencoded"

### json请求 
+ 请求体: {"username":"maoyz","age":12}
+ request.getParameter()无法获取请求参数; 
+ 请求数据类型必须设置: contentType: "application/json;charset=UTF-8";
+ 接收参数值@RequestBody,否则获取的值都是null

##  @RequestMapping(value = "/path", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
+ 通过设置consumes,指定处理请求的提交内容类型;若类型不符,415错误;
+ 通过设置produces,指定返回数据类型


## json序列化和反序列化

### 依赖

```
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.9.9</version>
    </dependency>
```

### 常用注解及说明

@JsonProperty 用于属性上，作用是把该属性的名称序列化为另外一个名称, 默认字段名称, 当指定之后只能接收别名
@JsonIgnore 用于属性或者方法上（最好是属性上）,json忽略此属性, 不管序列化还是反序列化都忽略
@JsonIgnoreProperties(ignoreUnknown = true, value = {"user_name"})，注解写在类上之后，就会忽略类中不存在的字段。同时value还可以指定要忽略的字段
@JsonFormat 时间格式转换
@JsonSerialize 用于属性或者getter方法上，用于在序列化时嵌入我们自定义的代码，比如序列化一个double时在其后面限制两位小数点。
@JsonDeserialize 用于属性或者setter方法上，用于在反序列化时可以嵌入我们自定义的代码，类似于上面的@JsonSerialize

### JackConfig 关于ObjectMapper的配置, JacksonUtil使用工具类