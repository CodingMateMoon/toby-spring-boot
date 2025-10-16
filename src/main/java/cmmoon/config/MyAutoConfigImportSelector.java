package cmmoon.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {
    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    // 자동 구성 설정 후보
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> autoConfigs = new ArrayList<>();
        ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);
/*
        for(String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)){
            autoConfigs.add(candidate);
        }
*/
        return autoConfigs.toArray(new String[0]);
//        return Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);
    }
}
