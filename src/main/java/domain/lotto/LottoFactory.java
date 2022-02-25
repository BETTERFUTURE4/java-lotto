package domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {
    private static final int END_INDEX = 6;
    private static final int FROM_INDEX = 0;

    public static Lotto createRandomLotto() {
        final List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.LOTTO_NUMBER_CACHE.values());
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.subList(FROM_INDEX, END_INDEX));
    }

    public static Lotto createLotto(final List<Integer> nums) {
        return new Lotto(toBalls(nums));
    }

    public static WinNumbers createWinLotto(final List<Integer> nums, LottoNumber bonus) {
        return new WinNumbers(createLotto(nums), bonus);
    }

    private static List<LottoNumber> toBalls(final List<Integer> lottoNums) {
        return lottoNums.stream()
                .map(LottoNumber::from)
                .sorted()
                .collect(Collectors.toList());
    }
}
