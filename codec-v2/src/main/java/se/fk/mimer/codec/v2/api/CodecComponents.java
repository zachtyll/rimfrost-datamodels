package se.fk.mimer.codec.v2.api;

import lombok.Value;
import se.fk.mimer.codec.v2.jsonld.PayloadInspector;

@Value
public class CodecComponents
{
    Codec codec;
    PayloadInspector inspector;
}
