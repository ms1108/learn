package testTestNG;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class TestGosnFormat {

    public Lotto lotto;
    @Data
    public static class Lotto {
        public Integer lottoId;
        @JSONField(name = "winning-numbers")
        public List<Integer> winningNumbers;
        public List<Winners> winners;
        @Data
        public static class Winners {
            public Integer winnerId;
            public List<Integer> numbers;
        }
    }
}
