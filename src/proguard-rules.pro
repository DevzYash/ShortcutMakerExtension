# Add any ProGuard configurations specific to this
# extension here.

-keep public class com.shortcutmaker.Shortcutmaker {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'com/shortcutmaker/repack'
-flattenpackagehierarchy
-dontpreverify
