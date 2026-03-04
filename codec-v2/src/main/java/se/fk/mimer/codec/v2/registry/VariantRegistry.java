package se.fk.mimer.codec.v2.registry;

import java.util.Map;

/**
 * Registry för variant-baserad polymorfism.
 *
 * <p>
 * Kopplar variant-strängar till implementationstyper inom en given bas-typ.
 * */
public interface VariantRegistry
{
    /**
     * Returnerar variant för en implementationstyp.
     *
     * @param clazz implementationstyp
     * @return variant-sträng
     */
    String variantForClass(Class<?> clazz);

    /**
     * Returnerar implementationstype för given variant inom en bas-typ.
     *
     * @param variant variant-sträng
     * @param baseType bas-typ
     * @return implementationstyp eller null
     */
    <T> Class<? extends T> classForVariant(String variant, Class<T> baseType);

    /**
     * Returnerar en tom registry implementation, användbart för testning.
     *
     * @return En tom {@link DefaultVariantRegistry}
     */
    static VariantRegistry empty() {
        return new DefaultVariantRegistry( Map.of(), Map.of() );
    }
}
