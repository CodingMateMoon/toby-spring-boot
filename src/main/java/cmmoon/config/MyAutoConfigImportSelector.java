package cmmoon.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    // 자동 구성 설정 후보
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        ImportCandidates candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
    }
}
