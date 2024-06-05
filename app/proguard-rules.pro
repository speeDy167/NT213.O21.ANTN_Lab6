# Keep - Applications can keep certain classes, fields, or methods from being obfuscated or shrunk.
-keep class com.example.myapplication.** { *; }

# Avoid removing public constructors in activities, etc.
-keepclassmembers class * {
    public <init>(android.content.Context);
}

# Keep classes that are referenced in XML layouts
-keep class * {
    public <init>(android.util.AttributeSet);
}

# Keep methods that could be used in event handlers (callbacks)
-keepclassmembers class * {
    public void *(android.view.View);
}

# Keep enum values, ProGuard tends to strip these out sometimes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
