## 第三方分享及登录 [ShareUtil](https://github.com/shaohui10086/ShareUtil)

## 使用说明

### 步骤一
在app的build.gradle里面，找到defaultConfig，在此节点下增加qq id的信息，目的是去修改AndroidManifest.xml中变量

```groovy
defaultConfig {
    ...

    manifestPlaceholders = [
            //替换成申请下来的qq_id(qq_id没有tencent前缀)
            qqappid: "123456789"
    ]
}
```

### 步骤二
在包名目录下创建wxapi文件夹，新建一个名为WXEntryActivity的activity继承WXCallbackActivity。
