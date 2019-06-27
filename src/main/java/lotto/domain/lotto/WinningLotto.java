package lotto.domain.lotto;

import lotto.domain.Rank;
import lotto.domain.creator.ManualLottoCreator;
import lotto.domain.util.CustomStringUtils;
import lotto.exception.InvalidLottoNumbersException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WinningLotto {
    private final Lotto lotto;
    private final LottoNumber bonusNumber;

    public WinningLotto(String inputLottoNumbers, String inputBonusNumber) {
        CustomStringUtils.checkIsBlank(inputLottoNumbers);
        CustomStringUtils.checkIsBlank(inputBonusNumber);

        List<Integer> lottoNumbers = CustomStringUtils.parseInts(inputLottoNumbers);
        int bonusNumber = CustomStringUtils.parseInt(inputBonusNumber);

        checkIsBonusNumberOverlap(lottoNumbers, bonusNumber);

        ManualLottoCreator creator = new ManualLottoCreator(lottoNumbers);
        this.lotto = creator.createLotto();
        this.bonusNumber = LottoNumber.valueOf(bonusNumber);
    }

    private void checkIsBonusNumberOverlap(List<Integer> lottoNumbers, int bonusNumber) {
        if (lottoNumbers.contains(bonusNumber)) {
            throw new InvalidLottoNumbersException("보너스 숫자가 이전에 입력한 숫자와 중복됩니다.");
        }
    }

    public Rank matchRank(Lotto lotto) {
        List<LottoNumber> winningNumbers = this.lotto.getNumbers();
        List<LottoNumber> lottoNumbers = new ArrayList<>(lotto.getNumbers());
        boolean isBonusMatch = isBonusMatch(lottoNumbers);

        lottoNumbers.retainAll(winningNumbers);
        int countOfMatch = lottoNumbers.size();

        return Rank.valueOf(countOfMatch, isBonusMatch);
    }

    private boolean isBonusMatch(List<LottoNumber> lottoNumbers) {
        return lottoNumbers.contains(bonusNumber);
    }

    public Lotto getLotto() {
        return lotto;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(lotto, that.lotto) &&
                Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto, bonusNumber);
    }
}