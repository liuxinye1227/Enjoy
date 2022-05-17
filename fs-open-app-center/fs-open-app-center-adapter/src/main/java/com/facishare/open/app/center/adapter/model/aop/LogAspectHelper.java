package com.facishare.open.app.center.adapter.model.aop;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by xialf on 2017/04/26.
 *
 * @author xialf
 * @since 2017/04/26 4:17 PM
 */
@Slf4j
public class LogAspectHelper {
    private static final Joiner JOINER = Joiner.on(",").skipNulls();
    private final Map<Method, String> methodParamsFmt = new ConcurrentHashMap<>();
    private final Map<Method, String> signature = new ConcurrentHashMap<>();

    public String getParamsFmt(final ProceedingJoinPoint point) {
        final MethodSignature methodSignature = (MethodSignature)point.getSignature();
        final Method method = methodSignature.getMethod();
        if (methodParamsFmt.containsKey(method)) {
            return methodParamsFmt.get(method);
        }

        List<String> parameterNames = Arrays.stream(method.getParameters())
                .map(Parameter::getName)
                .collect(Collectors.toList());
        log.info("init method parameter names: method[{}.{}], paramNames[{}]",
                method.getDeclaringClass().getSimpleName(),
                method.getName(),
                parameterNames);

        final List<String> fmt = parameterNames.stream()
                .map(name -> name + "=%s")
                .collect(Collectors.toList());
        methodParamsFmt.putIfAbsent(method, JOINER.join(fmt));
        return methodParamsFmt.get(method);
    }

    public String getSignature(final ProceedingJoinPoint point) {
        final MethodSignature methodSignature = (MethodSignature)point.getSignature();
        final Method method = methodSignature.getMethod();
        if (signature.containsKey(method)) {
            return signature.get(method);
        }

        String className = method.getDeclaringClass().getSimpleName();
        if (className.endsWith("Impl")) {
            className = className.substring(0, className.length() - 4);
        }
        final String sigName = className + "." + methodSignature.getName();
        signature.putIfAbsent(method, sigName);
        return signature.get(method);
    }

    /**
     * 获取一个UUID一半长度的字符串，取其奇数位上的字符.
     */
    public static String getShortUUID() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        final char[] chars = new char[uuid.length() / 2];
        for (int i = 0; i < uuid.length(); i++) {
            if (i % 2 == 0) {
                chars[i / 2] = uuid.charAt(i);
            }
        }
        return new String(chars);
    }
}
