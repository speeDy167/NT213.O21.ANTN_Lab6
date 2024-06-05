# Giữ các lớp ứng dụng và phụ thuộc của nó
-keep class com.example.myapplication.** { *; }

# Quy tắc cho Retrofit và Gson (nếu không sử dụng, bạn có thể bỏ qua các quy tắc này)
 -keep class com.squareup.okhttp.** { *; }
 -dontwarn com.squareup.okhttp.**
 -keep class retrofit.** { *; }
 -dontwarn retrofit.**
 -keep class com.google.gson.** { *; }
 -dontwarn com.google.gson.**


# Ngăn chặn làm khó hiểu các lớp mô hình (e.g., User)
-keep class com.example.myapplication.User { *; }


# Giữ tên của các phương thức được sử dụng trong vòng đời Android
-keepclassmembers class * {
    public void onCreate(android.os.Bundle);
    public void onDestroy();
    public void onPause();
    public void onResume();
    public void onStart();
    public void onStop();
    public void onRestart();
    public void onActivityResult(int, int, android.content.Intent);
}

# Giữ các phương thức native
-keepclasseswithmembernames class * {
    native <methods>;
}

# Giữ các phương thức và trường của các lớp Parcelable
-keepclassmembers class * implements android.os.Parcelable {
    static ** CREATOR;
}

# Các tối ưu hóa và quy tắc liên quan đến Android
-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# Tối ưu hóa để loại bỏ mã không sử dụng
-dontoptimize
-optimizationpasses 5
-allowaccessmodification
-keepattributes *Annotation*

# Không làm khó hiểu các lớp R được tạo tự động (cần thiết cho tài nguyên Android)
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Không làm khó hiểu các lớp mở rộng từ Activity, Fragment, Service, BroadcastReceiver
-keep class * extends android.app.Activity { *; }
-keep class * extends android.app.Fragment { *; }
-keep class * extends android.app.Service { *; }
-keep class * extends android.content.BroadcastReceiver { *; }

# Quy tắc cho các thư viện bên thứ ba được sử dụng (nếu có)
# -dontwarn okio.**
# -dontwarn javax.annotation.**

# Quy tắc cho SQLiteDatabase và SQLiteOpenHelper (giữ lại để tránh lỗi)
-keep class android.database.sqlite.SQLiteOpenHelper { *; }
-keep class android.database.sqlite.SQLiteDatabase { *; }

# Quy tắc cho lớp SQLiteConnector
-keep class com.example.myapplication.SQLiteConnector { *; }

# Quy tắc để giữ lại các lớp và phương thức quan trọng khác (nếu có)
# Ví dụ:
# -keep class com.example.myapplication.YourOtherImportantClass { *; }
