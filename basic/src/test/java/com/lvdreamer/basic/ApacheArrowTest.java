package com.lvdreamer.basic;

import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.Float8Vector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.complex.reader.Float8Reader;
import org.apache.arrow.vector.complex.reader.TimeStampNanoReader;
import org.apache.arrow.vector.ipc.ArrowFileReader;
import org.apache.arrow.vector.util.ByteArrayReadableSeekableByteChannel;
import org.junit.Test;

import java.io.IOException;
import java.util.Base64;

public class ApacheArrowTest {


    @Test
    public void read() throws IOException {
        String sourceStr = "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";
        byte[] compressToBase64 = Base64.getDecoder().decode(sourceStr);
        BufferAllocator allocator = new RootAllocator(Long.MAX_VALUE);

        try (ArrowFileReader reader = new ArrowFileReader(new ByteArrayReadableSeekableByteChannel(compressToBase64), allocator)) {
            // read the 4-th batch
            VectorSchemaRoot readBatch = reader.getVectorSchemaRoot();
            readBatch.getRowCount();
            FieldVector timeVector = readBatch.getVector("time");
            Float8Vector valueVectors = (Float8Vector) readBatch.getVector("B-series");
            while (reader.loadNextBatch()) {
                // Processing …
                // access via reader
                TimeStampNanoReader fieldReader = timeVector.getReader();
                for (int i = 0; i < timeVector.getValueCount(); i++) {
                    fieldReader.setPosition(i);
                    if (fieldReader.isSet()) {
                        System.out.println(fieldReader.readLocalDateTime());
                    }
                }
                Float8Reader float8Reader = valueVectors.getReader();
                for (int i = 0; i < valueVectors.getValueCount(); i++) {
                    float8Reader.setPosition(i);
                    if (fieldReader.isSet()) {
                        System.out.println(float8Reader.readDouble());
                    }
                }
            }
        }
    }
}