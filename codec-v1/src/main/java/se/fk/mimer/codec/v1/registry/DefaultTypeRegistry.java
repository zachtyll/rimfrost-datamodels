package se.fk.mimer.codec.v1.registry;


import java.util.*;

/**
 * Defaultimplementation av {@link TypeRegistry} med interna URN-baserade type-id:n.
 *
 * <p>
 * Format:
 * <ul>
 *     <li>Data: {@code urn:fk:typ:<TypToken>:<ModelVersion>}</li>
 * </ul>
 */
public class DefaultTypeRegistry implements TypeRegistry
{
    private static final String URN_PREFIX = "urn:fk:";
    private static final String KIND_TYP = "typ";

    private final String modelVersion;

    private final Map<Class<?>, String> classToTypeToken;
    private final Map<String, Class<?>> typeTokenToClass;

    public DefaultTypeRegistry( String modelVersion, Map<Class<?>, String> classToTypeToken,
                                Map<String, Class<?>> typeTokenToClass)
    {
        this.modelVersion = requireNonBlank(modelVersion, "modelVersion");
        this.classToTypeToken = Map.copyOf( classToTypeToken );
        this.typeTokenToClass = Map.copyOf( typeTokenToClass );
    }

    @Override
    public String typeIdForClass( Class<?> clazz )
    {
        Objects.requireNonNull( clazz, "clazz must not be null" );
        String token = classToTypeToken.get( clazz );
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("No typeToken mapping for class: " + clazz.getName());
        }
        return buildTypeId(token, modelVersion);
    }

    @Override
    public Class<?> classForTypeId( String typeId )
    {
        Parsed parsed = parse(typeId);
        if (parsed == null) return null;
        return typeTokenToClass.get(parsed.typeToken);
    }

    @Override
    public String modelVersionForTypeId( String typeId )
    {
        Parsed parsed = parse(typeId);
        return parsed == null ? null : parsed.modelVersion;
    }

    @Override
    public Class<?> classForShortType(String shortTypeId)
    {
        if (shortTypeId == null || shortTypeId.isBlank()) return null;
        String token = shortTypeId.contains(":")
                ? shortTypeId.substring(shortTypeId.indexOf(':') + 1)
                : shortTypeId;
        return typeTokenToClass.get(token);
    }

    @Override
    public boolean isRegistered(Class<?> clazz) {
        return clazz != null && classToTypeToken.containsKey(clazz);
    }

    @Override
    public Set<Class<?>> getRegisteredClasses() {
        return Set.copyOf(classToTypeToken.keySet());
    }

    @Override
    public Class<?> classForVariantName(String variantName) {
        if (variantName == null || variantName.isBlank()) {
            return null;
        }
        return typeTokenToClass.entrySet().stream()
                .filter(e -> e.getKey().equalsIgnoreCase(variantName))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    private static String buildTypeId(String token, String version) {
        return URN_PREFIX + KIND_TYP + ":" + token + ":" + version;
    }

    private static Parsed parse(String typeId) {
        if (typeId == null || typeId.isBlank()) return null;
        if (!typeId.startsWith( URN_PREFIX )) return null;

        String[] parts = typeId.split( ":" );
        if (parts.length != 5) return null;
        if (!KIND_TYP.equals(parts[2])) return null;

        return new Parsed(parts[3], parts[4]);
    }

    private record Parsed(String typeToken, String modelVersion) {}

    private static String requireNonBlank(String s, String name) {
        Objects.requireNonNull(s, name + " must not be null");
        if (s.isBlank()) throw new IllegalArgumentException(name + " must not be blank");
        return s;
    }
}
