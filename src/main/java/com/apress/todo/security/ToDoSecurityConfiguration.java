package com.apress.todo.security;

import com.apress.todo.annotation.Algorithm;
import com.apress.todo.annotation.EnableToDoSecurity;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

public class ToDoSecurityConfiguration implements ImportSelector {
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        AnnotationAttributes attributes =
                AnnotationAttributes.fromMap(
                        annotationMetadata.getAnnotationAttributes(EnableToDoSecurity.class.getName(), false));
        Algorithm algorithm = attributes.getEnum("algorithm");
        switch(algorithm){
            case PBKDF2:
                return new String[] {"com.apress.todo.security.Pbkdf2Encoder"};
            case BCRYPT:
            default:
                return new String[] {"com.apress.todo.security.BCryptEncoder"};
        }
    }
}
