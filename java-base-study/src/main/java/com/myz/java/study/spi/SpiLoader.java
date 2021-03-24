/*
 * Copyright (C) 2021, All rights Reserved, Designed By
 * @author: maoyz
 * @Copyright: 2021-03-03 16:07  Inc. All rights reserved.
 */
package com.myz.java.study.spi;

import org.apache.commons.lang3.StringUtils;
import org.junit.internal.Checks;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author maoyz
 */
public class SpiLoader<T> {
    private static final String SERVICE_DIRECTORY = "META-INF/services/";
    private static final String COMMON_DIRECTORY = "META-INF/common/";

    private final Class<T> type;
    private final String defaultExtension;

    private final Set<WrapperClassInfo<?>> wrapperClasses = new TreeSet<>();
    private final Map<String, Class<? extends T>> extensionClasses = new HashMap<>();
    private final Set<FeatureInfo> featuresCache = new TreeSet<>();
    private final ConcurrentHashMap<String, T> extensionCache = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Class<?>, SpiLoader<?>> LOADER_CACHE = new ConcurrentHashMap<>();

    private SpiLoader(Class<T> type) {
        this.type = type;

        // 是否有注解SPI
        if (type.isAnnotationPresent(SPI.class)) {
            this.defaultExtension = type.getAnnotation(SPI.class).value();
        } else {
            this.defaultExtension = null;
        }

        loadFromDir(SERVICE_DIRECTORY);
        loadFromDir(COMMON_DIRECTORY);
    }


    public static <T> Optional<T> getDefault(Class<T> type) {
        return cache(type).getDefault();
    }

    public static <T> Optional<T> getByName(Class<T> type, String name) {
        return cache(type).getByName(name);
    }

    public Optional<T> getDefault() {
        if (StringUtils.isBlank(defaultExtension)) {
            return Optional.empty();
        }
        return getByName(defaultExtension);
    }

    public Optional<T> getByName(String name) {
        if (StringUtils.isBlank(name) && StringUtils.isBlank(name = defaultExtension)) {
            return Optional.empty();
        }
        return Optional.ofNullable(extensionCache.computeIfAbsent(name, this::newExtension));
    }

    public static <T> SpiLoader<T> cache(Class<T> type) {
        return (SpiLoader<T>) LOADER_CACHE.computeIfAbsent(type, t -> new SpiLoader<>(type));
    }

    private T newExtension(String name) {
        Class<? extends T> extensionClass = extensionClasses.get(name);
        if (extensionClass == null) {
            return null;
        }
        try {
            T instance = extensionClass.newInstance();
            for (WrapperClassInfo<?> wrapperClassInfo : wrapperClasses) {
                instance = (T) wrapperClassInfo.getClazz().getConstructor(type).newInstance(instance);
            }
            return instance;
        } catch (Throwable t) {
            throw new IllegalStateException("Extension instance of class (" + type + ") couldn't be instantiated", t);
        }
    }

    private void loadFromDir(String dir) {
        String fileName = dir + type.getName();
        try {
            Enumeration<URL> urls;
            final ClassLoader classLoader = getClassLoader();

            urls = classLoader.getResources(fileName);
            if (urls != null) {
                while (urls.hasMoreElements()) {
                    URL resourceUrl = urls.nextElement();
                    parseResource(classLoader, resourceUrl);
                }
            }
        } catch (Throwable t) {
            throw new IllegalStateException("An exception occurs when loading directory: " + fileName, t);
        }
    }

    public static ClassLoader getClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ignored) {
        }
        if (cl == null) {
            cl = SpiLoader.class.getClassLoader();
        }
        return cl;
    }

    private void parseResource(ClassLoader classLoader, URL resourceUrl) {

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resourceUrl.openStream(), StandardCharsets.UTF_8))) {
            String line;

            while ((line = reader.readLine()) != null) {

                final int ci = line.indexOf('#');
                if (ci >= 0) {
                    // Get string before '#'
                    line = line.substring(0, ci);
                }
                line = line.trim();
                if (line.length() > 0) {
                    try {
                        String name = null;
                        int i = line.indexOf('=');
                        if (i > 0) {
                            name = line.substring(0, i).trim();
                            line = line.substring(i + 1).trim();
                        }
                        if (line.length() > 0) {
                            Class<?> clazz = Class.forName(line, true, classLoader);
                            if (!type.isAssignableFrom(clazz)) {
                                continue;
                            }
                            putInCache(name, (Class<? extends T>) clazz);
                        }
                    } catch (Throwable t) {
                        if (!(t instanceof ClassNotFoundException)) {
                            throw new IllegalStateException("Failed to load extension class: " + line, t);
                        }
                    }
                }
            }
        } catch (Throwable t) {
            throw new IllegalStateException("Load extension class catch an exception: " + t.getMessage(), t);
        }
    }

    private void putInCache(String name, Class<? extends T> clazz) {
        if (isWrapperClass(clazz, type)) {
            Feature wrapperFeature = clazz.getAnnotation(Feature.class);
            wrapperClasses.add(wrapperFeature == null ? new WrapperClassInfo<>(clazz) : new WrapperClassInfo<>(clazz,
                    wrapperFeature.order()));
        } else {
            if (StringUtils.isBlank(name)) {
                // Use full name of class as name
                name = clazz.getName();
            }

            Class<? extends T> oldClass = extensionClasses.get(name);
            if (oldClass != null) {
                throw new IllegalStateException("Duplicate extension name: '" + name + "' on "
                        + oldClass.getName() + " and " + clazz.getName());
            }
            extensionClasses.put(name, clazz);

            Feature feature = clazz.getAnnotation(Feature.class);
            featuresCache.add(new FeatureInfo(name, feature));
        }
    }

    private boolean isWrapperClass(Class<?> clazz, Class<?> type) {
        try {
            clazz.getConstructor(type);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    private static class WrapperClassInfo<T> implements Comparable<WrapperClassInfo<T>> {

        final Class<? extends T> clazz;
        final int order;

        WrapperClassInfo(Class<? extends T> clazz) {
            this(clazz, 0);
        }

        WrapperClassInfo(Class<? extends T> clazz, int order) {
            this.clazz = clazz;
            this.order = order;
        }

        Class<? extends T> getClazz() {
            return clazz;
        }

        public int getOrder() {
            return order;
        }

        @Override
        public int compareTo(WrapperClassInfo<T> info) {
            // not allow to add same wrapper class
            if (this.clazz.equals(info.getClazz())) {
                return 0;
            }
            return this.order <= info.getOrder() ? -1 : 1;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WrapperClassInfo)) {
                return false;
            }
            return this.clazz.equals(((WrapperClassInfo<T>) obj).clazz);
        }

        @Override
        public int hashCode() {
            return this.clazz.hashCode();
        }
    }

    private static class FeatureInfo implements Comparable<FeatureInfo> {

        final String name;
        final String[] groups;
        final Map<String, String> tagsMap;
        final Map<String, String> excludeTagsMap;
        final int order;

        FeatureInfo(String name, Feature feature) {
            this.name = name;
            if (feature == null) {
                this.groups = new String[0];
                this.tagsMap = Collections.emptyMap();
                this.excludeTagsMap = Collections.emptyMap();
                this.order = 0;
            } else {
                this.groups = feature.groups();
                this.tagsMap = initTags(feature.tags());
                this.excludeTagsMap = initTags(feature.excludeTags());
                this.order = feature.order();
            }
        }

        @Override
        public int compareTo(FeatureInfo info) {
            return this.order <= info.order ? -1 : 1;
        }

        private Map<String, String> initTags(String[] tags) {
            if (tags == null || tags.length == 0) {
                return Collections.emptyMap();
            }
            Map<String, String> parsed = new HashMap<>(tags.length);
            for (String tag : tags) {
                if (StringUtils.isBlank(tag)) {
                    continue;
                }
                String[] tagKeyValueArr = parseSingleTag(tag);
                parsed.put(tagKeyValueArr[0], tagKeyValueArr[1]);
            }
            return parsed;
        }

        private String[] parseSingleTag(String featureTag) {
            String key = featureTag.trim();
            String value = "";
            if (featureTag.contains(":")) {
                String[] keyValueArr = featureTag.split(":");
                key = keyValueArr[0].trim();
                // if featureTag = "key:"
                if (keyValueArr.length == 1) {
                    value = "";
                } else {
                    value = keyValueArr[1].trim();
                }
            }
            return new String[]{key, value};
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof FeatureInfo)) {
                return false;
            }
            return this.name.equals(((FeatureInfo) obj).name);
        }

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }
    }
}