# android-addons
Aplicación que permite mostrar una lista de opciones a partir de otras aplicaciones instaladas en el dispositivo, cuyas Activities tengan un intent-filter category determinado.

* Addons: Proyecto que define un Activity como un addon:

  ### AndroidManifest.xml
  ```xml
  <activity
      android:name=".FisrtAddon"
      android:label="@string/app_name" >
      <intent-filter>
          <action android:name="android.intent.action.MAIN" />
          <category android:name="com.cfag.addons.intent.category.ADDON" />
      </intent-filter>
  </activity>
  ```

* AddonTest: Proyecto que indentifica los addons instalados en el dispositivo.

  ### MainActivity
  ```java
  Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
  mainIntent.addCategory("com.cfag.addons.intent.category.ADDON");

  PackageManager pm = getPackageManager();
  List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);
  ```