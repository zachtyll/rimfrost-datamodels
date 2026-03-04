package se.fk.mimer.codec.v1.jsonld;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import se.fk.mimer.codec.v1.registry.ContextProvider;
import se.fk.mimer.codec.v1.registry.TypeRegistry;

import java.util.List;

@RequiredArgsConstructor
public class JsonLdEnvelopeBuilder
{
    private final TypeRegistry typeRegistry;
    private final ContextProvider contextProvider;

    public ObjectNode buildRoot(
            ObjectNode dataNode,
            Class<?> dataClass,
            ObjectNode rawDataNode,
            String producerId,
            String modelVersion,
            JsonLdTaggingMode mode,
            ObjectNode rootOut
    )
    {
        if( dataNode == null || rawDataNode == null )
        {
            throw new IllegalArgumentException( "dataNode/rawDataNode must not be null" );
        }
        if( producerId == null || producerId.isBlank() )
        {
            throw new IllegalArgumentException( "producerId must not be blank" );
        }
        if( modelVersion == null || modelVersion.isBlank() )
        {
            throw new IllegalArgumentException( "modelVersion must not be blank" );
        }

        rootOut.set( JsonLdKeys.DATA, dataNode );
        rootOut.set( JsonLdKeys.RAW_DATA, rawDataNode );

        if( mode == JsonLdTaggingMode.ROOT_ONLY || mode == JsonLdTaggingMode.ROOT_AND_DATA )
        {
            String rootType = typeRegistry.payloadTypeId( modelVersion );
            tagNode( rootOut, producerId, modelVersion, rootType );

            if( mode == JsonLdTaggingMode.ROOT_AND_DATA )
            {
                String dataType = typeRegistry.typeIdForClass( dataClass );
                tagNode( dataNode, producerId, modelVersion, dataType );
            }
        }
        return rootOut;
    }

    private void tagNode( ObjectNode node, String producerId, String modelVersion, String typeId )
    {
        node.put( JsonLdKeys.TYPE, typeId );

        // Context is optional for now, if provider returns none, we omit @context
        List<String> contextList = contextProvider.contextsFor( producerId, modelVersion, typeId );
        if( contextList == null || contextList.isEmpty() )
        {
            return;
        }

        if( contextList.size() == 1 )
        {
            node.put( JsonLdKeys.CONTEXT, contextList.get( 0 ) );
        }
        else
        {
            ArrayNode arrayNode = node.arrayNode();
            contextList.forEach( arrayNode::add );
            node.set( JsonLdKeys.CONTEXT, arrayNode );
        }
    }
}
