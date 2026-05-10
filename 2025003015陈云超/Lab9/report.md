# Lab9 实验报告

## 一、ViewModel 在 Android 架构中的作用
ViewModel 是 Jetpack 组件，用于**分离 UI 与业务逻辑、独立管理应用状态**。
它的生命周期与 Activity/Fragment 解绑，屏幕旋转、配置变化时不会重建，能自动保存状态；同时将数据、逻辑集中管理，让 UI 只负责展示，代码更清晰、易测试、易维护。

## 二、DessertUiState 字段设计说明
DessertUiState 是数据类，统一封装 UI 所需全部状态：
- revenue：总收入
- dessertsSold：已售甜品数量
- currentDessertIndex：当前甜品索引
- currentDessertImageId：当前甜品图片
- currentDessertPrice：当前甜品单价
所有字段提供默认值，保证应用初始状态正确。

## 三、DessertViewModel 设计思路
1. 状态管理：使用 mutableStateOf 包装 DessertUiState，对外只读、对内可写，保证数据安全。
2. 业务逻辑：将 determineDessertToShow 移入 ViewModel，UI 不再关心切换规则。
3. 事件处理：提供 onDessertClicked 方法处理点击，UI 只需触发事件。
4. 状态更新：使用 copy() 生成新状态，驱动 Compose 自动刷新界面。

## 四、MainActivity 重构前后对比
重构前：
- 所有状态写在 Composable 内
- 业务逻辑与 UI 混合
- 屏幕旋转状态丢失

重构后：
- 无任何状态变量
- 无业务逻辑
- 只观察 ViewModel 状态并展示
- 代码简洁、职责单一

## 五、重构感受
重构后代码结构清晰，UI 与逻辑完全解耦。ViewModel 让状态管理更安全，旋转屏幕不丢数据，代码可测试性大幅提升，是 Compose 开发标准最佳实践。

## 六、遇到的问题与解决
1. 导入 viewModel() 失败
   解决：添加 lifecycle-viewmodel-compose 依赖。

2. 状态无法更新
   解决：使用 copy() 更新 UiState，而非直接修改变量。

3. 分享功能数据错误
   解决：将分享参数改为 uiState 中的