# 觅摊 API 接口文档

## 基础信息

**Base URL**: `http://your-domain.com`

**认证方式**: Bearer Token (JWT)

**请求头**:
```
Content-Type: application/json
Authorization: Bearer {token}
```

---

## 1. 认证模块 (Auth)

### 1.1 账号密码登录

**接口**: `POST /api/auth/login`

**描述**: 用户使用账号密码登录

**请求参数**:
```json
{
  "username": "string",  // 用户名/手机号，必填
  "password": "string"   // 密码，必填，6-20位
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "userInfo": {
      "id": 1,
      "username": "user123",
      "phone": "13800138000",
      "nickname": "觅食者",
      "avatar": "https://example.com/avatar.jpg",
      "createTime": "2024-01-01 12:00:00"
    }
  }
}
```

**错误码**:
- `400`: 参数错误
- `401`: 用户名或密码错误
- `403`: 账号已被禁用

---

### 1.2 微信登录

**接口**: `POST /api/auth/wx/login`

**描述**: 用户使用微信授权登录

**请求参数**:
```json
{
  "code": "string"  // 微信登录凭证，必填
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "userInfo": {
      "id": 1,
      "openid": "oXXXX",
      "nickname": "微信用户",
      "avatar": "https://wx.qlogo.cn/...",
      "createTime": "2024-01-01 12:00:00"
    }
  }
}
```

**错误码**:
- `400`: 参数错误
- `401`: 微信授权失败
- `403`: 账号已被禁用

---

### 1.3 用户注册

**接口**: `POST /api/auth/register`

**描述**: 新用户注册账号

**请求参数**:
```json
{
  "phone": "string",      // 手机号，必填，11位
  "password": "string",   // 密码，必填，6-20位
  "code": "string",       // 验证码，必填，6位
  "nickname": "string"    // 昵称，可选，2-20位
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "注册成功",
  "data": {
    "userId": 1
  }
}
```

**错误码**:
- `400`: 参数错误
- `409`: 手机号已注册
- `410`: 验证码错误或已过期

---

### 1.4 发送短信验证码

**接口**: `POST /api/auth/sms/send`

**描述**: 发送短信验证码

**请求参数**:
```json
{
  "phone": "string",  // 手机号，必填，11位
  "type": "string"    // 类型：register-注册，reset-重置密码，必填
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "发送成功",
  "data": {
    "expireTime": 300  // 验证码有效期（秒）
  }
}
```

**错误码**:
- `400`: 参数错误
- `429`: 发送频率过快，请稍后再试

---

### 1.5 重置密码

**接口**: `POST /api/auth/password/reset`

**描述**: 通过手机号重置密码

**请求参数**:
```json
{
  "phone": "string",      // 手机号，必填，11位
  "password": "string",   // 新密码，必填，6-20位
  "code": "string"        // 验证码，必填，6位
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "密码重置成功"
}
```

**错误码**:
- `400`: 参数错误
- `404`: 手机号未注册
- `410`: 验证码错误或已过期

---

### 1.6 退出登录

**接口**: `POST /api/auth/logout`

**描述**: 用户退出登录

**请求头**: 需要携带 Token

**返回数据**:
```json
{
  "code": 200,
  "message": "退出成功"
}
```

---

### 1.7 刷新Token

**接口**: `POST /api/auth/token/refresh`

**描述**: 刷新访问令牌

**请求头**: 需要携带当前 Token

**返回数据**:
```json
{
  "code": 200,
  "message": "刷新成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

**错误码**:
- `401`: Token无效或已过期

---

## 2. 用户模块 (User)

### 2.1 获取用户信息

**接口**: `GET /api/user/profile`

**描述**: 获取当前登录用户的个人信息

**请求头**: 需要携带 Token

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "username": "user123",
    "phone": "138****8000",
    "nickname": "觅食者",
    "avatar": "https://example.com/avatar.jpg",
    "gender": 1,  // 0-未知，1-男，2-女
    "birthday": "1990-01-01",
    "signature": "寻觅人间烟火",
    "followCount": 10,
    "createTime": "2024-01-01 12:00:00"
  }
}
```

---

### 2.2 更新用户信息

**接口**: `PUT /api/user/profile`

**描述**: 更新当前登录用户的个人信息

**请求头**: 需要携带 Token

**请求参数**:
```json
{
  "nickname": "string",   // 昵称，可选，2-20位
  "gender": 0,            // 性别，可选，0-未知，1-男，2-女
  "birthday": "string",   // 生日，可选，格式：YYYY-MM-DD
  "signature": "string"   // 个性签名，可选，最多100字
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "username": "user123",
    "phone": "138****8000",
    "nickname": "觅食者",
    "avatar": "https://example.com/avatar.jpg",
    "gender": 1,
    "birthday": "1990-01-01",
    "signature": "寻觅人间烟火",
    "followCount": 10,
    "createTime": "2024-01-01 12:00:00"
  }
}
```

---

### 2.3 更新用户头像

**接口**: `PUT /api/user/avatar`

**描述**: 更新当前登录用户的头像

**请求头**: 需要携带 Token

**请求参数**:
```json
{
  "avatar": "string"  // 头像URL，必填
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "avatar": "https://example.com/avatar.jpg"
  }
}
```

---

### 2.4 获取收藏列表

**接口**: `GET /api/user/follows`

**描述**: 获取当前用户收藏的摊位列表

**请求头**: 需要携带 Token

**请求参数**:
```
?page=1&size=10
```

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "total": 10,
    "page": 1,
    "size": 10,
    "list": [
      {
        "id": 1,
        "stallId": 1,
        "stallName": "老王烧烤",
        "stallImage": "https://example.com/stall.jpg",
        "category": "烧烤",
        "address": "北京市朝阳区xxx",
        "status": 1,  // 0-休息，1-营业
        "followTime": "2024-01-01 12:00:00"
      }
    ]
  }
}
```

---

### 2.5 收藏摊位

**接口**: `POST /api/user/follow`

**描述**: 收藏指定摊位

**请求头**: 需要携带 Token

**请求参数**:
```json
{
  "stallId": 1  // 摊位ID，必填
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "收藏成功"
}
```

**错误码**:
- `404`: 摊位不存在
- `409`: 已收藏该摊位

---

### 2.6 取消收藏

**接口**: `DELETE /api/user/follow/{stallId}`

**描述**: 取消收藏指定摊位

**请求头**: 需要携带 Token

**路径参数**:
- `stallId`: 摊位ID

**返回数据**:
```json
{
  "code": 200,
  "message": "取消收藏成功"
}
```

---

### 2.7 获取消息通知

**接口**: `GET /api/user/notifications`

**描述**: 获取当前用户的消息通知列表

**请求头**: 需要携带 Token

**请求参数**:
```
?page=1&size=10&type=all
```

**参数说明**:
- `type`: 消息类型，all-全部，system-系统通知，stall-摊位通知

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "total": 5,
    "page": 1,
    "size": 10,
    "unreadCount": 2,
    "list": [
      {
        "id": 1,
        "type": "system",  // system-系统通知，stall-摊位通知
        "title": "系统通知",
        "content": "欢迎使用觅摊",
        "isRead": 0,  // 0-未读，1-已读
        "createTime": "2024-01-01 12:00:00"
      }
    ]
  }
}
```

---

### 2.8 标记消息已读

**接口**: `PUT /api/user/notifications/{notificationId}/read`

**描述**: 标记指定消息为已读

**请求头**: 需要携带 Token

**路径参数**:
- `notificationId`: 消息ID

**返回数据**:
```json
{
  "code": 200,
  "message": "操作成功"
}
```

---

### 2.9 标记所有消息已读

**接口**: `PUT /api/user/notifications/read-all`

**描述**: 标记所有消息为已读

**请求头**: 需要携带 Token

**返回数据**:
```json
{
  "code": 200,
  "message": "操作成功"
}
```

---

## 3. 摊位模块 (Stall)

### 3.1 获取附近摊位

**接口**: `GET /api/stall/nearby`

**描述**: 根据地理位置获取附近摊位列表

**请求参数**:
```
?latitude=39.9042&longitude=116.4074&radius=1000&page=1&size=10
```

**参数说明**:
- `latitude`: 纬度，必填
- `longitude`: 经度，必填
- `radius`: 搜索半径（米），可选，默认1000
- `page`: 页码，可选，默认1
- `size`: 每页数量，可选，默认10

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "total": 20,
    "page": 1,
    "size": 10,
    "list": [
      {
        "id": 1,
        "name": "老王烧烤",
        "image": "https://example.com/stall.jpg",
        "category": "烧烤",
        "address": "北京市朝阳区xxx",
        "latitude": 39.9042,
        "longitude": 116.4074,
        "distance": 500,  // 距离（米）
        "status": 1,  // 0-休息，1-营业
        "rating": 4.8,
        "followCount": 100
      }
    ]
  }
}
```

---

### 3.2 获取摊位详情

**接口**: `GET /api/stall/{id}`

**描述**: 获取指定摊位的详细信息

**路径参数**:
- `id`: 摊位ID

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "vendorId": 1,
    "name": "老王烧烤",
    "image": "https://example.com/stall.jpg",
    "images": [
      "https://example.com/stall1.jpg",
      "https://example.com/stall2.jpg"
    ],
    "category": "烧烤",
    "address": "北京市朝阳区xxx",
    "latitude": 39.9042,
    "longitude": 116.4074,
    "description": "正宗东北烧烤，炭火烤制...",
    "phone": "138****8000",
    "businessHours": "18:00-02:00",
    "status": 1,
    "rating": 4.8,
    "followCount": 100,
    "isFollowed": true,
    "createTime": "2024-01-01 12:00:00"
  }
}
```

---

## 4. 摊主模块 (Vendor)

### 4.1 摊主登录

**接口**: `POST /api/vendor/login`

**描述**: 摊主使用手机号/邮箱和密码登录

**请求参数**:
```json
{
  "username": "string",  // 手机号/邮箱，必填
  "password": "string"   // 密码，必填
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "登录成功",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
    "userInfo": {
      "id": 1,
      "name": "老王烧烤",
      "phone": "13800138000",
      "avatar": "https://example.com/avatar.jpg",
      "stallCount": 1,
      "rating": 4.8
    }
  }
}
```

**错误码**:
- `400`: 参数错误
- `401`: 用户名或密码错误
- `403`: 账号已被禁用

---

### 4.2 获取商家信息

**接口**: `GET /api/vendor/info`

**描述**: 获取当前登录商家的基本信息

**请求头**: 需要携带 Token

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "name": "老王烧烤",
    "phone": "13800138000",
    "avatar": "https://example.com/avatar.jpg",
    "stallCount": 1,
    "rating": 4.8
  }
}
```

---

### 4.3 获取摊位列表

**接口**: `GET /api/vendor/stalls`

**描述**: 获取当前商家的摊位列表

**请求头**: 需要携带 Token

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": [
    {
      "id": 1,
      "name": "老王烧烤",
      "location": "泰山学院小吃街",
      "openingHours": "10:00-22:00",
      "status": "open",
      "rating": 4.8
    }
  ]
}
```

---

### 4.4 获取摊位详情

**接口**: `GET /api/vendor/stalls/{id}`

**描述**: 获取指定摊位的详细信息

**请求头**: 需要携带 Token

**路径参数**:
- `id`: 摊位ID

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": 1,
    "name": "老王烧烤",
    "location": "泰山学院小吃街",
    "openingHours": "10:00-22:00",
    "status": "open",
    "rating": 4.8
  }
}
```

---

### 4.5 创建摊位

**接口**: `POST /api/vendor/stalls`

**描述**: 创建新摊位

**请求头**: 需要携带 Token

**请求参数**:
```json
{
  "name": "新摊位",  // 摊位名称，必填
  "location": "位置",  // 位置，必填
  "openingHours": "营业时间"  // 营业时间，必填
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "创建成功",
  "data": {
    "id": 2,
    "name": "新摊位",
    "location": "位置",
    "openingHours": "营业时间",
    "status": "open",
    "rating": 0.0
  }
}
```

---

### 4.6 更新摊位

**接口**: `PUT /api/vendor/stalls/{id}`

**描述**: 更新摊位信息

**请求头**: 需要携带 Token

**路径参数**:
- `id`: 摊位ID

**请求参数**:
```json
{
  "name": "更新的名称",  // 摊位名称，必填
  "location": "更新的位置",  // 位置，必填
  "openingHours": "更新的营业时间"  // 营业时间，必填
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "更新成功",
  "data": {
    "id": 1,
    "name": "更新的名称",
    "location": "更新的位置",
    "openingHours": "更新的营业时间",
    "status": "open",
    "rating": 4.8
  }
}
```

---

### 4.7 删除摊位

**接口**: `DELETE /api/vendor/stalls/{id}`

**描述**: 删除指定摊位

**请求头**: 需要携带 Token

**路径参数**:
- `id`: 摊位ID

**返回数据**:
```json
{
  "code": 200,
  "message": "摊位删除成功"
}
```

---

### 4.8 更新摊位状态

**接口**: `PUT /api/vendor/stalls/{id}/status`

**描述**: 更新摊位营业状态

**请求头**: 需要携带 Token

**路径参数**:
- `id`: 摊位ID

**请求参数**:
```json
{
  "status": "open"  // 状态：open-营业，closed-休息
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "状态更新成功",
  "data": {
    "id": 1,
    "status": "open",
    "name": "老王烧烤",
    "location": "泰山学院小吃街",
    "openingHours": "10:00-22:00",
    "rating": 4.8
  }
}
```

---

### 4.9 获取订单列表

**接口**: `GET /api/vendor/orders`

**描述**: 获取订单列表，支持按状态筛选

**请求头**: 需要携带 Token

**请求参数**:
```
?status=pending  // 订单状态：pending-待处理，processing-处理中，completed-已完成
```

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": [
    {
      "id": "20260326001",
      "time": "2026-03-26 10:30",
      "items": "羊肉串×2, 烤鸡翅×1",
      "amount": 45,
      "status": "completed"
    }
  ]
}
```

---

### 4.10 获取订单详情

**接口**: `GET /api/vendor/orders/{id}`

**描述**: 获取指定订单的详细信息

**请求头**: 需要携带 Token

**路径参数**:
- `id`: 订单ID

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "id": "20260326001",
    "time": "2026-03-26 10:30",
    "items": "羊肉串×2, 烤鸡翅×1",
    "amount": 45,
    "status": "completed"
  }
}
```

---

### 4.11 更新订单状态

**接口**: `PUT /api/vendor/orders/{id}/status`

**描述**: 更新订单状态

**请求头**: 需要携带 Token

**路径参数**:
- `id`: 订单ID

**请求参数**:
```json
{
  "status": "processing"  // 状态：processing-处理中，completed-已完成
}
```

**返回数据**:
```json
{
  "code": 200,
  "message": "状态更新成功",
  "data": {
    "id": "20260326001",
    "status": "processing",
    "time": "2026-03-26 10:30",
    "items": "羊肉串×2, 烤鸡翅×1",
    "amount": 45
  }
}
```

---

### 4.12 获取统计数据

**接口**: `GET /api/vendor/statistics`

**描述**: 获取销售趋势数据、关键指标和热门商品数据

**请求头**: 需要携带 Token

**请求参数**:
```
?period=day  // 统计周期：day-今日，week-本周，month-本月，year-本年
```

**返回数据**:
```json
{
  "code": 200,
  "message": "获取成功",
  "data": {
    "metrics": {
      "orders": 12,
      "revenue": 388,
      "average": 32
    },
    "salesTrend": {
      "labels": ["09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"],
      "data": [2, 5, 8, 12, 7, 3, 2, 4, 6, 9, 11, 8]
    },
    "popularItems": [
      {
        "id": 1,
        "name": "羊肉串",
        "sales": 25,
        "revenue": 150
      },
      {
        "id": 2,
        "name": "烤鸡翅",
        "sales": 18,
        "revenue": 108
      }
    ]
  }
}
```

---

## 5. 统一错误码

| 错误码 | 说明 |
|-------|------|
| 200 | 成功 |
| 400 | 请求参数错误 |
| 401 | 未授权（未登录或Token失效） |
| 403 | 禁止访问（权限不足或账号被禁用） |
| 404 | 资源不存在 |
| 409 | 资源冲突（如重复注册） |
| 410 | 资源已过期（如验证码过期） |
| 429 | 请求频率过快 |
| 500 | 服务器内部错误 |

---

## 5. 统一返回格式

所有接口统一返回以下格式：

```json
{
  "code": 200,
  "message": "操作成功",
  "data": {}
}
```

**字段说明**:
- `code`: 状态码，200表示成功
- `message`: 提示信息
- `data`: 返回数据，可能为对象或数组

---

## 6. 开发注意事项

### 6.1 Token 管理
- Token 有效期为 7 天
- Token 过期后需要调用刷新接口或重新登录
- 前端应在请求拦截器中自动携带 Token
- 前端应在响应拦截器中处理 401 错误，自动跳转登录页

### 6.2 请求频率限制
- 短信验证码：同一手机号 60 秒内只能发送 1 次
- 接口请求：同一 IP 每分钟最多 100 次请求

### 6.3 数据安全
- 密码传输需使用 HTTPS 加密
- 敏感信息（手机号）需脱敏显示
- Token 应存储在安全位置，避免 XSS 攻击

---

## 7. 联系方式

如有问题，请联系前端开发团队。
