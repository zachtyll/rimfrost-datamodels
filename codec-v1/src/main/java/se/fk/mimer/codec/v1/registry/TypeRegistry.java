package se.fk.mimer.codec.v1.registry;

import java.util.Set;

/**
 * Registry för koppling mellan Java-typer och JSON-LD {@code @type}-identifierare.
 *
 * <p>
 * {@code @type} måste innehålla tillräcklig information för att kunna:
 * <ul>
 *     <li>avgöra klass-typ</li>
 *     <li>avgöra codec-version (t.ex. 2.0, 3.0)</li>
 * </ul>
 */
public interface TypeRegistry
{
    /**
     * Returnerar {@code meta:typeId}-identifierare för given Java-klass.
     *
     * @param clazz Java-klass
     * @return type-id på URN-format
     */
    String typeIdForClass(Class<?> clazz);

    /**
     * Returnerar Java-klass för en given URN {@code meta:typeId}-sträng.
     *
     * @param typeId data {@code @type}
     * @return bas-klass eller null om okänd
     */
    Class<?> classForTypeId(String typeId);

    /**
     * Returnerar modellversion utifrån en URN {@code meta:typeId}-sträng.
     *
     * @param typeId {@code meta:typeId} på URN-format.
     * @return modellVersion eller {@code null} om okänd.
     */
    String modelVersionForTypeId(String typeId);

    /**
     * Returnerar Java-klass från ett {@code @type}-värde i JSON-LD-format.
     *
     * <p>Exempel: {@code "fk:Person"} -> {@code Person.class}
     *
     * @param shortTypeId {@code @type}-värde på formatet {@code "domän:klassnamn"}
     * @return Java-klass eller null om okänd
     */
    Class<?> classForShortType(String shortTypeId);

    /**
     * Returnerar {@code true} om klassen är registrerad i registret.
     *
     * @param clazz Java klass
     * @return {@code true} om klassen har en registrerad mapping
     */
    boolean isRegistered(Class<?> clazz);

    /**
     * Returnerar alla klasser registrerade i detta registry.
     *
     * @return oföränderlig samlig av registrerade klasser
     */
    Set<Class<?>> getRegisteredClasses();

    /**
     * Returnerar Java klass från ett variant namn.
     *
     * @param variantName variant namn från serialisering
     * @return Java klass, eller {@code null} om okänd
     */
    Class<?> classForVariantName(String variantName);
}
