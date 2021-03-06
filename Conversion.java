package Datosp;

/**
 *
 * @author moonrain
 */
public class Conversion {

    public static int unsignedByteAInt(byte b) {
        return b & 0xFF;
    }

    public static byte[] deLongA3Bytes(long l) {
        byte[] resultado = new byte[3];
        resultado[0] = (byte) (l >> 16);
        resultado[1] = (byte) (l >> 8);
        resultado[2] = (byte) (l);

        return resultado;
    }

    public static int de3BytesAInt(byte[] b) throws ArrayIndexOutOfBoundsException {
        if (b.length < 3) {
            throw new ArrayIndexOutOfBoundsException("El número de dígitos debe ser igual a 3");
        }
        int resultado;
        resultado = unsignedByteAInt(b[0]) << 16 | unsignedByteAInt(b[1]) << 8 | unsignedByteAInt(b[2]);
        return resultado;
    }
}
