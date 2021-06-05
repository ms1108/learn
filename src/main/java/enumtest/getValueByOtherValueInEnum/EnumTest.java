package enumtest.getValueByOtherValueInEnum;

import lombok.Getter;
//根据枚举内容获取其他内容
@Getter
public enum EnumTest {
    SUCCESS(1, "成功"),
    FAILED(2, "失败");
    private final Integer status;
    private final String des;

    EnumTest(Integer status, String des) {
        this.status = status;
        this.des = des;
    }

    public static EnumTest get(Integer no) {
        for (EnumTest value : EnumTest.values()) {
            if (value.getStatus() == no) {
                return value;
            }
        }
        return null;
    }
}
