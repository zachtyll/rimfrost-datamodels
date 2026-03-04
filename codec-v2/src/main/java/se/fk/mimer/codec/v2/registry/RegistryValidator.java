package se.fk.mimer.codec.v2.registry;

import java.util.Objects;

public class RegistryValidator
{
    private RegistryValidator() {}

    public static void ValidateCoreOrThrow( TypeRegistry typeRegistry,
                                            Class<?> yrkandeBaseClass,
                                            Class<?> handlaggningBaseClass) {
        Objects.requireNonNull(typeRegistry, "typeRegistry must not be null");
        Objects.requireNonNull( yrkandeBaseClass, "yrkandeBaseClass must not be null" );
        Objects.requireNonNull( handlaggningBaseClass, "handlaggningBaseClass must not be null" );

        validateTypeMapping(typeRegistry, yrkandeBaseClass);
        validateTypeMapping(typeRegistry, handlaggningBaseClass);
    }

    private static void validateTypeMapping(TypeRegistry typeRegistry, Class<?> baseClass) {
        String typeId = typeRegistry.typeIdForClass( baseClass );
        if (typeId == null || typeId.isBlank()) {
            throw new IllegalStateException("Missing typeIdForClass mapping for: " + baseClass.getName());
        }

        Class<?> resolvedBase = typeRegistry.classForTypeId( typeId );
        if (resolvedBase == null) {
            throw new IllegalStateException("Missing classForTypeId mapping for: " + typeId);
        }
        if (!resolvedBase.equals( baseClass )) {
            throw new IllegalStateException("TypeRegistry mismatch: typeId" + typeId
            + " resolved to " + resolvedBase.getName()
            + " but expected " + baseClass.getName());
        }

        String modelVersion = typeRegistry.modelVersionForTypeId( typeId );
        if (modelVersion == null || modelVersion.isBlank()) {
            throw new IllegalStateException("Could not derive modelVersion from typeId " + typeId);
        }
    }
}
