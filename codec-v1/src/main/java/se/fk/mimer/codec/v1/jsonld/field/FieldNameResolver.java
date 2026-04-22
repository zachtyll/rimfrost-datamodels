package se.fk.mimer.codec.v1.jsonld.field;

import se.fk.mimer.codec.v1.jsonld.context.JsonLdPrefix;

import java.util.Set;

/**
 * Mappar JSON-fältnamn till prefixat JSON-LD-fältnamn.
 */
public class FieldNameResolver {

    private static final Set<String> META_FIELDS = Set.of(
            "revision",
            "typeId"
    );

    private static final Set<String> COMMON_FIELDS = Set.of(
            "from",
            "tom"
    );

    public String resolve (String fieldName) {
        if (META_FIELDS.contains(fieldName)) return JsonLdPrefix.META.apply(fieldName);
        if (COMMON_FIELDS.contains(fieldName)) return JsonLdPrefix.COMMON.apply(fieldName);
        return JsonLdPrefix.DOMAIN.apply(fieldName);
    }
}
