package enumtest.getEnumByValue;

/**
 * 枚举工具类
 */
public class EnumUtil {

    /**
     * 返回指定编码的'枚举'
     *
     * @param code
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends CommonEnum> T getEnumBycode(Class<T> clazz, int code) {
        for (T _enum : clazz.getEnumConstants())
            if (code == _enum.getCode())
                return _enum;
        return null;
    }

    /**
     * 返回指定名称的'枚举'
     *
     * @param name
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends CommonEnum> T getEnumByName(Class<T> clazz, String name) {
        for (T _enum : clazz.getEnumConstants())
            if (_enum.getName().equals(name))
                return _enum;
        return null;
    }

    /**
     * 返回指定描述的'枚举'
     *
     * @param desc
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends CommonEnum> T getEnumByDesc(Class<T> clazz, String desc) {
        for (T _enum : clazz.getEnumConstants())
            if (_enum.getDesc().equals(desc))
                return _enum;
        return null;
    }

    public static void main(String[] args) {
        StatusEnum statusEnum = EnumUtil.getEnumBycode(StatusEnum.class, 1);
        System.out.println(statusEnum.getDesc());
        statusEnum = EnumUtil.getEnumByName(StatusEnum.class, "END");
        System.out.println(statusEnum.getDesc());
        statusEnum = EnumUtil.getEnumByDesc(StatusEnum.class, "开始");
        System.out.println(statusEnum.getDesc());
    }
}

