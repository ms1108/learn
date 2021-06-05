package listTest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
    public static void main(String[] args) {
        String value = "a.b";
        String packageName = "e.c";
        List<String> enumPackageNames= Arrays.stream(value.split("\\.")).collect(Collectors.toList());
        List<String> packageNames= Arrays.stream(packageName.split("\\.")).collect(Collectors.toList());
        //求列表的交集，即公共部分
        enumPackageNames.retainAll(packageNames);
        System.out.println(enumPackageNames);
        System.out.println(enumPackageNames.size());
    }
}
