#### 1.查询商品详情（queryGoodsInfo）
判断单价范围时，需要考虑用户已购买的数量
###### 1.1入参 ： 

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
goodsId|string|是|商品ID、应用appId
###### 1.2 出参

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
errorCode|int|是|错误码
errorMessage|string|是|错误描述
priceType|int|是|计价类型 1：助手类 数量和期限都有 2：培训助手 流量时长 小时
subject|string|是|商品名称
minYears|string|否|年限范围值
maxYears|string|否|年限范围值
hours|list<String>|否|课时列表
quantity|int|是|已购买数量
priceRanges|map|否|单价区间信息： eg1： {0-100:3000元/年;101-500:6000元/年;ALL:3000-6000元/年}  eg2: {ALL:20元/人/年} eg3: {ALL:0.5元/小时}

 
#### 2.计算订单应付金额（calculateTotalPrice）
###### 2.1入参 ： 

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
goodsId|string|是|商品ID、应用appId
priceType|int|是|计价类型
quantity|int|是|数量，人数
years|int|是|购买年数
###### 2.2 出参

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
errorCode|int|是|错误码
errorMessage|string|是|错误描述
amount|long|是|应付金额，总价，单位分

#### 3.生成订单（createOrder）
###### 3.1入参 ： 

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
walletId|string|否|企业钱包ID
merchantCode|string|否|商户号，默认为纷享商户号
goodsId|string|是|商品ID、应用appId
priceType|int|是|计价类型
quantity|int|是|数量，人数
years|int|是|购买年数
amount|long|是|总价，单位分
subject|string|否|商品名称


###### 3.2 出参

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
errorCode|int|是|错误码
errorMessage|string|是|错误描述
orderNo|string|是|订单号
merchantCode|string|是|商户号
remark|string|否|备注
subject|string|是|商品名称
body|string|是|商品描叙
toEA|string|否|收款的企业号，为空默认为纷享
toEAWalletId|string|否|收款企业账号ID
toEAName|string|是|企业名称
amount|long|是|总价，单位分
wareId|string|是|支付用商品ID
sign|string|是|支付签名串



#### 4.查询单价（queryPrice）
###### 4.1入参 ： 

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
goodsId|string|是|商品ID、应用appId
priceType|int|是|计价类型
quantity|int|是|数量，人数
years|int|是|购买年数
###### 4.2 出参

字段名称|类型|是否必填| 描述 
 ---- | ----|---- | ----- 
errorCode|int|是|错误码
errorMessage|string|是|错误描述
price|string|是|单价 eg: 0.5元/小时


