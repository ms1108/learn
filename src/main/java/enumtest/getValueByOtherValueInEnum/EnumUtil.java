package enumtest.getValueByOtherValueInEnum;

public class EnumUtil {
    public static void main(String[] args) {
        EnumTest statusEnum = EnumTest.get(1);
        System.out.println(statusEnum.getDes());
    }
}
