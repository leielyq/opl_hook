package com.leielyq.opl_hook;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by leiel on 2018/3/19 0019.
 */

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Hello SystemUI, Loaded app: " + lpparam.packageName);
        if (lpparam.packageName.equals("net.oneplus.launcher")) {
            XposedBridge.log("Hello SystemUI, Loaded app: " + lpparam.packageName);
            XposedHelpers.findAndHookMethod("net.oneplus.launcher.Utilities", lpparam.classLoader, "hasCompatibleOpLib", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
                    param.setResult(true);
                }
            });
            XposedHelpers.findAndHookMethod("net.oneplus.launcher.Utilities", lpparam.classLoader, "isDarkModeTheme", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(true);
                }
            });

            XposedHelpers.findAndHookMethod("net.oneplus.launcher.Utilities", lpparam.classLoader, "isAllowRotationPrefEnabled", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
            XposedHelpers.findAndHookMethod("net.oneplus.launcher.Utilities", lpparam.classLoader, "isFirstReboot", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
            XposedHelpers.findAndHookMethod("net.oneplus.launcher.Utilities", lpparam.classLoader, "registerNotificationService", Context.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    param.setResult(false);
                }
            });
        }
    }
}
