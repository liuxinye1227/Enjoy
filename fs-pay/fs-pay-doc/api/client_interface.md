## 冻结二维码：  http://172.31.105.113:29315/pay/getCodeTest?test=0&amount=1.00
## 转账二维码： http://172.31.105.113:29315/pay/getTransferCode?test=0&amount=1.00&fromfsUid=1637


Method | QueryName | Argument | ResultType | Description
-----  |------     |----      |----        | -------
Query      | FHE/EM1APAY/PAYWallet/xxx        |         |           | 

#### 一、验证交易金额 (checkTransMoney) (需要token)
##### <i>1.入参</i>

<table>
<thead>
<tr>
<th>字段</th>
<th>类型</th>
<th>描述 </th>
</tr>
</thead>
<tbody>
<tr>
<td>amount</td>
<td>string</td>
<td>交易金额</td>
</tr>
<tr>
<td>cardNo</td>
<td>string</td>
<td>银行卡号</td>
</tr>
<tr>
<td>payWay</td>
<td>int</td>
<td>支付方式 1 零钱 2 快钱旧卡 3 微信 4 新卡支付</td>
</tr>
<tr>
<td>payType</td>
<td>int</td>
<td>业务类型0充值 1 提现  2 发红包</td>
</tr>
</tbody>
</table>

#####   <i>2.返回参数</i>

<table>
<thead>
<tr>
<th>参数名</th>
<th>数据类型</th>
<th>描述</th>
</tr>
</thead>
<tbody>
<tr>
<td>errorCode</td>
<td>int</td>
<td>错误代码  62060输入密码  62070 重新绑卡
62080 修改密码</td>
</tr>
<tr>
<td>errorMessage</td>
<td>string</td>
<td>错误信息</td>
</tr>
</tbody>
</table>


#### 二、转账接口 (userTransfer) (需要token)
##### <i>1.入参:</i>
<table>
<thead>
<tr>
<th>字段</th>
<th>类型</th>
<th>描述 </th>
</tr>
</thead>
<tbody>
<tr>  
<td>amount</td>
<td>String</td>
<td>交易金额</td>
</tr>
<tr>
<td>cardNo</td>
<td>string</td>
<td>银行卡号</td>
</tr>
<tr>  
<td>password</td>
<td>String</td>
<td>免密支付，可以为空</td>
</tr>
<tr>  
<td>payWay</td>
<td>int</td>
<td>支付方式 1 零钱 2 快钱旧卡 3 微信 4 新卡支付</td>
</tr>
<tr>  
<td>toFsUserId</td>
<td>int</td>
<td>受赠人纷享ID </td>
</tr>
<tr>  
<td>toEnterpriseAccount</td>
<td>string</td>
<td>受赠人企业号</td>
</tr>
<tr>  
<td>requestTime</td>
<td>long</td>
<td>请求时间 </td>
</tr>
<tr>  
<td>reamrk</td>
<td>String</td>
<td> 备注   </td>
</tr>
<tr>  
<td>merchantId</td>
<td>String</td>
<td>商户号</td>
</tr>
<tr>  
<td>wareId</td>
<td>String</td>
<td> 商品ID</td>
</tr>
<tr>  
<td>wareName</td>
<td>String</td>
<td> 商品名称</td>
</tr>
<tr>  
<td>payType</td>
<td>Integer</td>
<td> 支付类型，1为扫码支付，0为普通支付（默认值）</td>
</tr>
<tr>  
<td>sign</td>
<td>String</td>
<td> 签名（业务的服务方提供）</td>
</tr>
<tr>  
<td>expireTime</td>
<td>String</td>
<td> 过期时间，扫码支付特有。没有的就直接传""</td>
</tr>
<tr>  
<td>fromEa</td>
<td>String</td>
<td> 付款人的企业号，扫码支付需要业务方提供，其它的使用当前付款人</td>
</tr>
<tr>  
<td>fromUid</td>
<td>String</td>
<td> 付款人的ID，扫码支付需要业务方提供，其它的使用当前付款人</td>
</tr>
<tr>  
<td>limitPayer</td>
<td>String</td>
<td> 是否需要限制付款人。扫码支付特有，普通支付可不传，或者传“1”</td>
</tr>
</tbody>
</table> 
##### <i>2.返回参数:</i>

<table>
<thead>
<tr>
<th>参数名</th>
<th>数据类型</th>
<th>描述</th>
</tr>
</thead>
<tbody>
<tr>
<td>errorCode</td>
<td>int</td>
<td>错误代码 </td>
</tr>
<tr>
<td>errorMessage</td>
<td>string</td>
<td>错误信息</td>
</tr>
<tr>
<td>responseTime</td>
<td>long</td>
<td>响应时间</td>
</tr>
<tr>
<td>orderNo</td>
<td>string</td>
<td>订单号</td>
</tr>
<tr>
<td>phoneNo</td>
<td>string</td>
<td>手机号（带*号）</td>
</tr>
</tbody>
</table> 


#### 三、小黄条公告接口 (notice)  (需要token)
##### <i>1.入参:</i>
<table>
<thead>
<tr>
<th>字段</th>
<th>类型</th>
<th>描述 </th>
</tr>
</thead>
<tbody>
<tr>  
<td>noticeType</td>
<td>Integer</td>
<td>业务类型（必填,详情见3.业务类型枚举）</td>
</tr>
<tr>  
<td>currentTime</td>
<td>Long</td>
<td>时间戳（可选）</td>
</tr>
</tbody>
</table> 
##### <i>2.返回参数:</i>

<table>
<thead>
<tr>
<th>参数名</th>
<th>数据类型</th>
<th>描述</th>
</tr>
</thead>
<tbody>
<tr>
<td>errorCode</td>
<td>int</td>
<td>错误代码 </td>
</tr>
<tr>
<td>errorMessage</td>
<td>string</td>
<td>错误信息</td>
</tr>
<tr>
<td>title</td>
<td>string</td>
<td>公告标题</td>
</tr>
<tr>
<td>content</td>
<td>string</td>
<td>公告内容</td>
</tr>
<tr>
<td>url</td>
<td>string</td>
<td>链接(url不为空时公告可以点击，否则公告不能点击)</td>
</tr>
<tr>
<td>noticeType</td>
<td>string</td>
<td>公告所属业务(多个业务,隔开)</td>
</tr>
<tr>
<td>beginTime</td>
<td>string</td>
<td>公告开始时间</td>
</tr>
<tr>
<td>endTime</td>
<td>string</td>
<td>公告结束时间</td>
</tr>
<tr>
<td>createTime</td>
<td>string</td>
<td>公告创建时间</td>
</tr>
</tbody>
</table> 
## 备注：没有公告的时候，只返回errorCode,errorMessage两个参数

##### <i>3.业务类型枚举：</i>
<table>
<thead>
<tr>
<th>ID</th>
<th>类型</th>
</tr>
</thead>
<tbody>
<tr>  
<td>-1</td>
<td>支付</td>
</tr>
<tr>  
<td>0</td>
<td>充值</td>
</tr>
<tr>  
<td>1</td>
<td>提现</td>
</tr>
<tr>  
<td>2</td>
<td>红包</td>
</tr>
</tbody>
</table>


#### 四、查询用户绑定银行卡 (queryUserCard)
##### <i>1.入参:</i>
<table>
<thead>
<tr>
<th>字段</th>
<th>类型</th>
<th>描述 </th>
</tr>
</thead>
<tbody>
<tr>  
<td>无</td>
<td></td>
<td></td>
</tr>
</tbody>
</table> 
##### <i>2.返回参数:</i>
<table>
<thead>
<tr>
<th>字段</th>
<th>类型</th>
<th>描述 </th>
</tr>
</thead>
<tbody>
<tr>  
<td>cardNo</td>
<td>String</td>
<td>卡号</td>
</tr>
<tr>  
<td>cardNoDes</td>
<td>String</td>
<td>卡号带*号</td>
</tr>
<tr>  
<td>bankName</td>
<td>String</td>
<td>开户行名称</td>
</tr>
<tr>  
<td>singlePayLimit</td>
<td>Long</td>
<td>限额  单笔限额(修改为  充值单笔限额) </td>
</tr>
<tr>  
<td>dayPayLimit</td>
<td>Long</td>
<td>单日限额 （修改为充值单日限额）</td>
</tr>
<tr>  
<td>minPayLimit</td>
<td>Long</td>
<td>单笔提现限额（最低交易金额）</td>
</tr>
<tr>  
<tr>  
<td>withDrawSingleLimit</td>
<td>Long</td>
<td>单笔提现限额（最高交易金额） 新增</td>
</tr>
<tr>  
<tr>  
<td>withDrawDayLimit</td>
<td>Long</td>
<td>单日提现限额（最高交易金额）新增 </td>
</tr>
<tr>  
<td>createTime</td>
<td>String</td>
<td>创建时间</td>
</tr>
<tr>  
<td>remark</td>
<td>String</td>
<td>备注</td>
</tr>
<tr>  
<td>bgColor</td>
<td>String</td>
<td>背景颜色</td>
</tr>
<tr>  
<td>iconKey</td>
<td>String</td>
<td>icon key</td>
</tr>
<tr>  
<td>iconKey3</td>
<td>String</td>
<td>icon key</td>
</tr>
<tr>  
<td>cardType</td>
<td>int</td>
<td>银行卡类型  1 储蓄卡  2 信用卡</td>
</tr>
<tr>  
<td>chargeStatus</td>
<td>Integer</td>
<td>状态： 1可用0不可用 改为 0可用 1 不可用</td>
</tr>
<tr>  
<td>withDrawStatus</td>
<td>Integer</td>
<td>状态： 1可用0不可用 改为 0可用 1 不可用</td>
</tr>
</tbody>
</table> 
## 备注：当状态为0不可用时。银行卡置灰，显示备注remark

#### 五、提现申请 (userGetMoneyApply) (需要token)
##### <i>1.入参:</i>
<table>
<thead>
<tr>
<th>字段</th>
<th>类型</th>
<th>描述 </th>
</tr>
</thead>
<tbody>
<tr>  
<td>amount </td>
<td>string</td>
<td>提现金额</td>
</tr>
<tr>  
<td>requestTime </td>
<td>long</td>
<td>请求时间戳</td>
</tr>
<tr>  
<td>cardNo </td>
<td>string</td>
<td>银行卡号</td>
</tr>
<tr>  
<td>password </td>
<td>string</td>
<td>支付密码</td>
</tr>
</tbody>
</table> 
##### <i>2.返回参数:</i>
<table>
<thead>
<tr>
<th>字段</th>
<th>类型</th>
<th>描述 </th>
</tr>
</thead>
<tbody>
<tr>  
<td>errorCode</td>
<td>int</td>
<td>错误码</td>
</tr>
<tr>  
<td>errorMessage</td>
<td>string</td>
<td>错误信息</td>
</tr>
<tr>  
<td>remark</td>
<td>string</td>
<td>提现提示语: 如  预计04-08 24点前到账</td>
</tr>
<tr>  
</tbody>
</table> 
## 备注：新增 remark 字段 