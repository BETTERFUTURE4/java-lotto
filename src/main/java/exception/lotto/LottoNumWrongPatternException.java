package exception.lotto;

public class LottoNumWrongPatternException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "로또 번호는 1, 2, 3, 4, 5, 6 과 같은 형태로 입력하여야 합니다.";

    public LottoNumWrongPatternException() {
        super(ERROR_MESSAGE);
    }
}