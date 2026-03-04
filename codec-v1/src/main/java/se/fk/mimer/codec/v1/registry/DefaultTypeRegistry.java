package se.fk.mimer.codec.v1.registry;


import java.util.Map;
import java.util.Objects;

/**
 * Defaultimplementation av {@link TypeRegistry} med interna URN-baserade type-id:n.
 *
 * <p>
 * Format:
 * <ul>
 *     <li>Data: {@code urn:mimer:typ:<TypToken>:<ModelVersion>}</li>
 *     <li>Payload: {@code urn:mimer:payload:<ModelVersion>}</li>
 * </ul>
 */
public class DefaultTypeRegistry implements TypeRegistry
{
    private static final String URN_PREFIX = "urn:mimer:";
    private static final String KIND_TYP = "typ";
    private static final String KIND_PAYLOAD = "payload";

    private final String modelVersion;

    private final Map<Class<?>, String> classToTypeToken;
    private final Map<String, Class<?>> typeTokenToClass;

    public DefaultTypeRegistry( String modelVersion, Map<Class<?>, String> classToTypeToken,
                                Map<String, Class<?>> typeTokenToClass )
    {
        this.modelVersion = requireNonBlank(modelVersion, "modelVersion");
        this.classToTypeToken = Map.copyOf( classToTypeToken );
        this.typeTokenToClass = Map.copyOf( typeTokenToClass );
    }

    @Override
    public String typeIdForClass( Class<?> clazz )
    {
        Objects.requireNonNull( clazz, "clazz must not be null" );
        String typeName = classToTypeToken.get( clazz );
        if (typeName == null || typeName.isBlank()) {
            throw new IllegalArgumentException("No typeToken mapping for class: " + clazz.getName());
        }
        return dataTypeId(typeName, modelVersion);
    }

    @Override
    public String payloadTypeId( String modelVersion )
    {
        return buildPayloadTypeId( requireNonBlank( modelVersion, "modelVersion" ) );
    }

    @Override
    public Class<?> classForTypeId( String typeId )
    {
        Parsed parsed = parse(typeId);
        if (parsed == null || parsed.kind != Kind.DATA) return null;
        return typeTokenToClass.get(parsed.typeToken);
    }

    @Override
    public String modelVersionForTypeId( String typeId )
    {
        Parsed parsed = parse(typeId);
        return parsed == null ? null : parsed.modelVersion;
    }

    private static String dataTypeId(String typeName, String modelVersion) {
        return URN_PREFIX + KIND_TYP + ":" + typeName + ":" + modelVersion;
    }

    private static String buildPayloadTypeId(String modelVersion) {
        return URN_PREFIX + KIND_PAYLOAD + ":" + modelVersion;
    }

    private static Parsed parse(String typeId) {
        if (typeId == null || typeId.isBlank()) return null;
        if (!typeId.startsWith( URN_PREFIX )) return null;

        String[] parts = typeId.split( ":" );
        if (parts.length < 4) return null;

        String typ = parts[2];

        if (KIND_PAYLOAD.equals( typ )) {
            if (parts.length != 4) return null;
            return new Parsed( Kind.PAYLOAD, null, parts[3] );
        }

        if (KIND_TYP.equals( typ )) {
            if (parts.length != 5) return null;
            return new Parsed(Kind.DATA, parts[3], parts[4]);
        }

        return null;
    }

    private enum Kind { DATA, PAYLOAD };

    private record Parsed(Kind kind, String typeToken, String modelVersion) {}

    private static String requireNonBlank(String s, String name) {
        Objects.requireNonNull(s, name + " must not be null");
        if (s.isBlank()) throw new IllegalArgumentException(name + " must not be blank");
        return s;
    }
}
