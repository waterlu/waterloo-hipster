### ${name}

#### 接口说明

${desc}

#### 请求方式

```
${method} ${url}
```

#### 请求参数说明

|  参数名    |  是否必须  |  类型   |   说明   |
| :-----:   | :-----:  | :----:  | :----:  |
<#if request.params?exists>
<#list request.params as item>
| ${item.name!} | ${item.required?string("是","否")} | ${item.type!} | ${item.desc!} |
</#list>
</#if>

#### 请求数据示例

```
${(request.sample)!}
```

#### 返回数据说明

**整体数据格式**

- 返回HTTP状态码固定为200，具体信息封装在返回数据中
- 返回数据为JSON格式
- 返回数据固定包括code、message、data三个字段

```
code：200表示调用成功，其余表示失败
message：出错时错误信息的具体说明
data：有效数据载荷
```

**data数据格式**

|  参数名    |    类型   |   说明   |
| :-----:   | :-----:  | :----:  |
<#if response.result?exists>
<#list response.result as item>
| ${item.name!}  | ${item.type!} | ${item.desc!} |
</#list>
</#if>

#### 返回数据示例

``` json
${response.sample!}
```
---

