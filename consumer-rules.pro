#
# ------------------------------ 第三方 ------------------------------
#

-dontwarn com.orhanobut.logger.**
-keep class com.orhanobut.logger.**{*;}
-keep interface com.orhanobut.logger.**{*;}

-dontwarn com.google.gson.**
-keep class com.google.gson.**{*;}
-keep interface com.google.gson.**{*;}

# 科大讯飞
-dontwarn com.iflytek.**
-keep class com.iflytek.**{*;}
-keepattributes Signature

# 腾讯
-dontwarn com.tencent.**
-keep class com.tencent.**{*;}

-dontwarn com.lidroid.xutils.**
-keep class com.lidroid.xutils.**{*;}
-keep class com.google.zxing.**{*;}
-keep class com.amap.api.**{*;}
-keep class com.loc.**{*;}
-keep class com.autonavi.aps.amapapi.model.**{*;}
-keep class com.qq.**{*;}
-keep class tencent.tls.**{*;}
-keep class org.bouncycastle.**{*;}

-keep class com.nodepp.smartnode.model.**{*;}
-keep class com.nodepp.smartnode.dtls.**{*;}
-keep class com.nodepp.smartnode.esptouch.**{*;}
-keep class nodepp.**{*;}
-keep class outnodepp.**{*;}
-keep class dsig.**{*;}
-keep class msig.**{*;}

# 极光推送
-dontwarn cn.jpush.**
-keep class cn.jpush.** { *; }
-keep class * extends cn.jpush.android.helpers.JPushMessageReceiver { *; }

-dontwarn cn.jiguang.**
-keep class cn.jiguang.** { *; }
