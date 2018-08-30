package JavaTest;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * 测试 使用反射获取 Unsafe 对象
 */
public class MyUnsafe {

    public static int byteArrayBaseOffset;

    public static void main(String[] args) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field theightUnsafe = Unsafe.class.getDeclaredField("theightUnsafe");
        theightUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theightUnsafe.get(null);
        byte[] data = new byte[10];
        byteArrayBaseOffset = unsafe.arrayBaseOffset(byte[].class);
        System.out.println("byteArrayBaseOffset= " + byteArrayBaseOffset);
        int arrayIndexScale = unsafe.arrayIndexScale(byte[].class);
        System.out.println("arrayIndexScale= " + arrayIndexScale);
        unsafe.putByte(data, byteArrayBaseOffset, (byte) 1);
        unsafe.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
        System.out.println(Arrays.toString(data));
    }
}
