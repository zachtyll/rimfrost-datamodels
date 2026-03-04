package se.fk.mimer.codec.v1.registry;

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
     * Returnerar {@code @type}-identifierare för given Java-klass.
     *
     * @param clazz Java-klass
     * @return type-id
     */
    String typeIdForClass(Class<?> clazz);

    /**
     * Returnerar {@code @type}-identifierare för payloadens root/envelope för given modellversion.
     *
     * @param modelVersion modellversion, t.ex. {@code 2.0}
     * @return payload type-id
     */
    String payloadTypeId(String modelVersion);

    /**
     * Returnerar bas-klass för en given data-{@code @type}
     *
     * @param typeId data {@code @type}
     * @return bas-klass eller null om okänd
     */
    Class<?> classForTypeId(String typeId);

    /**
     * Returnerar modellversion utifrån {@code @type}.
     *
     * @param typeId {@code @type}-identifierare.
     * @return modellVersion eller null om okänd.
     */
    String modelVersionForTypeId(String typeId);
}
