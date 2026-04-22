package se.fk.mimer.codec.v1.api;

import lombok.Value;
import se.fk.mimer.codec.v1.jsonld.extract.PayloadInspector;

@Value
public class CodecComponents
{
    Codec codec;
    PayloadInspector inspector;
}
