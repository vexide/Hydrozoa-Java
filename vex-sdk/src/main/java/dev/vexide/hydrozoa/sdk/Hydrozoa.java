package dev.vexide.hydrozoa.sdk;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.teavm.interop.Address;
import org.teavm.interop.Import;

import java.nio.ByteBuffer;

/**
 * A utility class for operations specific to the Hydrozoa runtime.
 */
public final class Hydrozoa {
    private Hydrozoa() {
    }

    /**
     * Causes the program to abnormally terminate with the given message.
     *
     * @param message a message describing the cause of the panic
     */
    @Import(module = "hydrozoa", name = "panic")
    @Contract("_ -> fail")
    public static native void panic(@NotNull String message);

    @Import(module = "hydrozoa", name = "getByteArrayPointer")
    private static native int _getByteArrayPointer(byte[] array, int size);

    /**
     * Returns a pointer to the address of the provided array for use with VexSdk.
     */
    public static VexSdk.RawPointer getByteArrayPointer(byte[] array) {
        return new VexSdk.RawPointer(_getByteArrayPointer(array, array.length));
    }

//    /**
//     * @return
//     */
//    @Import(module = "hydrozoa", name = "alloc")
//    public static native int alloc(int size);
//
//    /**
//     * @return
//     */
//    @Import(module = "hydrozoa", name = "readByte")
//    public static native int readByte(int address, int offset);
//
//    public static byte[] readBytes(int address, int offset, int bytes) {
//        var result = new byte[bytes];
//        for (int i = 0; i < bytes; i++) {
//            result[i] = (byte) readByte(address, offset + i);
//        }
//        return result;
//    }

    public static final class Util {
        public enum Primitive {
            u8(1), i8(1), u16(2), i16(2), u32(4), i32(4), FLOAT(4), DOUBLE(8);
            public final int size;

            Primitive(int size) {
                this.size = size;
            }
        }

        public static Number[] parseStruct(byte[] array, Primitive[] struct) {
            int offset = 0;
            var result = new Number[struct.length];
            var buffer = ByteBuffer.wrap(array);
            for (int i = 0; i < struct.length; i++) {
                Number num = null;
                Primitive primitive = struct[i];
                switch (primitive) {
                    case u8 -> num = ((short) buffer.get(offset)) & 0xff;
                    case i8 -> num = buffer.get(offset);
                    case u16 -> num = ((int) buffer.getShort(offset)) & 0xffff;
                    case i16 -> num = buffer.getShort(offset);
                    case u32 -> num = ((long) buffer.getInt(offset)) & 0xffffffffL;
                    case i32 -> num = buffer.getInt(offset);
                    case FLOAT -> num = buffer.getFloat(offset);
                    case DOUBLE -> num = buffer.getDouble(offset);
                }
                offset += primitive.size;
                result[i] = num;
            }
            return result;
        }
    }
}
