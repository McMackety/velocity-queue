package org.mcmackety.vqueue.config;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J3\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00060\f\u00a2\u0006\u0002\u0010\rJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0001R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lorg/mcmackety/vqueue/config/YAMLParse;", "", "()V", "mapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "parseFile", "T", "path", "Ljava/nio/file/Path;", "fileName", "", "dto", "Lkotlin/reflect/KClass;", "(Ljava/nio/file/Path;Ljava/lang/String;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "writeFile", "", "obj", "vqueue"})
public final class YAMLParse {
    private static final com.fasterxml.jackson.databind.ObjectMapper mapper = null;
    @org.jetbrains.annotations.NotNull()
    public static final org.mcmackety.vqueue.config.YAMLParse INSTANCE = null;
    
    @org.jetbrains.annotations.NotNull()
    public final <T extends java.lang.Object>T parseFile(@org.jetbrains.annotations.NotNull()
    java.nio.file.Path path, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    kotlin.reflect.KClass<T> dto) {
        return null;
    }
    
    public final void writeFile(@org.jetbrains.annotations.NotNull()
    java.nio.file.Path path, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    java.lang.Object obj) {
    }
    
    private YAMLParse() {
        super();
    }
}