package lottogame.utils;

import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ManualLottoGenerator implements LottoGenerator {
    private static final String DELIMITER = ", ";
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^(\\d{1,2},\\s){5}\\d{1,2}$");

    private List<String> ticketStrings;

    public ManualLottoGenerator() {
    }

    public ManualLottoGenerator(String ticketString) {
        ticketStrings = new ArrayList<>(Arrays.asList(ticketString));
    }

    @Override
    public List<Lotto> generateLottos() {
        return ticketStrings.stream()
                .map(this::makeLotto)
                .collect(Collectors.toList());
    }

    public Lotto generateLotto() {
        return makeLotto(ticketStrings.get(0));
    }

    private Lotto makeLotto(String ticketString) {
        String[] numberStrings = ticketString.split(DELIMITER);
        List<LottoNumber> lottoNumbers = Arrays.stream(numberStrings)
                .map(LottoNumber::of)
                .sorted()
                .collect(Collectors.toList());
        return new Lotto(lottoNumbers);
    }

    public void addResources(List<String> ticketStrings) {
        ticketStrings.forEach(this::validate);
        this.ticketStrings = ticketStrings;
    }

    private void validate(String numbers) {
        if (!NUMBER_PATTERN.matcher(numbers).matches()) {
            throw new IllegalArgumentException("로또번호를 잘못 입력했습니다.");
        }
    }
}