# 数字键盘输入法

一个简单的Android数字输入法应用，包含0-9数字键和四个功能键（复制、粘贴、清空、删除）。

## 功能特性

- **数字键盘**：包含0-9十个数字键，排列清晰，易于点击
- **功能键**：
  - 复制：复制选中文本或当前输入框所有文本
  - 粘贴：粘贴剪贴板中的文本
  - 清空：清除当前输入框的所有文本
  - 删除：删除光标前的一个字符
- **简洁设计**：深色主题，按键分组显示，操作直观

## 安装和使用

### 1. 构建应用
使用Android Studio打开项目，或者使用命令行构建：
```bash
./gradlew assembleDebug
```

### 2. 安装应用
将生成的APK文件安装到Android设备上。

### 3. 启用输入法
1. 打开应用，点击「启用输入法」按钮
2. 在系统设置中找到并启用"数字输入法"
3. 返回应用，点击「选择输入法」按钮
4. 选择"数字键盘"作为当前输入法

### 4. 使用键盘
在任何文本输入框中，数字键盘将自动显示，您可以：
- 点击数字键输入数字
- 使用功能键进行文本编辑操作

## 项目结构

```
NumberKeyboard/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/numberkeyboard/
│   │   │   ├── MainActivity.java              # 主活动（设置页面）
│   │   │   └── NumberInputMethodService.java # 输入法服务核心类
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml         # 主活动布局
│   │   │   │   └── keyboard.xml              # 键盘布局
│   │   │   ├── values/
│   │   │   │   └── strings.xml               # 字符串资源
│   │   │   └── xml/
│   │   │       └── method.xml                # 输入法配置
│   │   └── AndroidManifest.xml               # 应用清单
│   └── build.gradle                          # 应用构建配置
├── build.gradle                              # 项目构建配置
└── settings.gradle                           # Gradle设置
```

## 技术实现

- **InputMethodService**：继承Android输入法服务基类
- **自定义键盘布局**：使用XML布局定义键盘界面
- **点击事件处理**：通过InputConnection与目标应用交互
- **剪贴板操作**：使用ClipboardManager实现复制粘贴功能
- **文本编辑**：支持删除和清空操作

## 系统要求

- Android 5.0 (API Level 21) 及以上
- 支持所有屏幕尺寸的手机和平板设备

## 开发环境

- Android Studio 2022.3.1 或更高版本
- Java 8
- Android Gradle Plugin 7.4.2
- Compile SDK 33

## 许可证

本项目仅供学习和参考使用。
