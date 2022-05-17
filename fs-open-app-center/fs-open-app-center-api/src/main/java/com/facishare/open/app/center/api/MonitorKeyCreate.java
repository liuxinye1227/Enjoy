package com.facishare.open.app.center.api;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MonitorKeyCreate {

    public static void scanning(List<Class<?>> classes, String packagePath) throws ClassNotFoundException {
        scanning(classes, packagePath, false);
    }

    public static void scanning(List<Class<?>> classes, String packagePath, boolean subPackage)
            throws ClassNotFoundException {
        File file = null;
        if (packagePath.endsWith(".class")) {
            file = new File(packagePath.replace('.', '/').substring(0, packagePath.length() - 6) + ".class");
        } else {
            file = new File(packagePath.replace('.', '/'));
        }
        if (null == file || !file.exists()) {
            return;
        }
        String className = file.getName();
        if (file.isFile() && className.endsWith(".class") && !className.contains("$")) {
            String pack = packagePath.substring(packagePath.lastIndexOf("/") + 1);
            pack = pack.substring(0, pack.length() - 6);
            classes.add(Class.forName(pack));
        } else if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (null != files) {
                for (File f : files) {
                    if (!subPackage && f.isDirectory()) {
                        continue;
                    }
                    scanning(classes, packagePath + "." + f.getName(), subPackage);
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        MonitorKeyCreate.scanning(classes, "fs-open-app-center-api/target/classes/com.facishare.open.app.center.api.service", true);
        for (Class<?> clazz : classes) {
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                System.out.println(String.format("open.center.%s.%s.total.sum", clazz.getSimpleName(), method.getName()));
                System.out.println(String.format("open.center.%s.%s.time.avg", clazz.getSimpleName(), method.getName()));
                System.out.println(String.format("open.center.%s.%s.fail.sum", clazz.getSimpleName(), method.getName()));
            }
        }
    }
}