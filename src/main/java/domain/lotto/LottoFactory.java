package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {
    private static final int END_INDEX = 6;
    private static final int FROM_INDEX = 0;

    public static Lotto createRandomLotto() {
        List<LottoBall> lottoBalls = new ArrayList<>(LottoBall.BALLS_CACHE.values());
        Collections.shuffle(lottoBalls);
        return new Lotto(lottoBalls.subList(FROM_INDEX, END_INDEX));
    }

    public static Lotto createLotto(List<Integer> nums) {
        return new Lotto(toBalls(nums));
    }

    public static WinLotto createWinLotto(List<Integer> nums, LottoBall bonus) {
        return new WinLotto(toBalls(nums), bonus);
    }

    private static List<LottoBall> toBalls(List<Integer> lottoNums) {
        return lottoNums.stream()
                .map(LottoBall::from)
                .sorted()
                .collect(Collectors.toList());
    }
}
