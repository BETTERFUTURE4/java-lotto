package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import utils.Util;

public class InputView {
    private static final String NUM_ERROR_MESSAGE = "숫자를 입력해주세요.";
    private static final String LOTTO_NUM_INPUT_PATTERN_ERROR_MESSAGE = "로또 번호는 1, 2, 3, 4, 5, 6 과 같은 형태로 입력하여야 합니다.";

    private static final String MONEY_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_NUMBER_INPUT_MESSAGE = System.lineSeparator() + "지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String MANUAL_TICKET_COUNT_MESSAGE = System.lineSeparator() + "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_TICKET_NUMBS_GROUP_MESSAGE = System.lineSeparator() + "수동으로 구매할 번호를 입력해 주세요.";

    private static final Pattern PATTERN = Pattern.compile("^[\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+, [\\d]+$");

    public static int inputMoney() {
        System.out.println(MONEY_INPUT_MESSAGE);
        return inputNum();
    }

    public static int inputManualTicketCount() {
        System.out.println(MANUAL_TICKET_COUNT_MESSAGE);
        return inputNum();
    }

    public static int inputBonusNumber() {
        System.out.println(BONUS_INPUT_MESSAGE);
        return inputNum();
    }

    public static List<Integer> inputWinLottoNums() {
        System.out.println(LOTTO_NUMBER_INPUT_MESSAGE);
        return inputLottoNums();
    }

    public static List<List<Integer>> inputManualNums(int repeatCount) {
        List<List<Integer>> numsGroup = new ArrayList<>();
        System.out.println(MANUAL_TICKET_NUMBS_GROUP_MESSAGE);
        for (int i = 0; i < repeatCount; i++) {
            numsGroup.add(inputLottoNums());
        }
        return numsGroup;
    }

    private static int inputNum() {
        final Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            throw new IllegalArgumentException(NUM_ERROR_MESSAGE);
        }
    }

    private static List<Integer> inputLottoNums() {
        final Scanner scanner = new Scanner(System.in);
        final String lottoNumbers = scanner.nextLine();
        validateLottoNums(lottoNumbers);
        return Util.separateNumbers(lottoNumbers);
    }

    private static void validateLottoNums(final String lottoNumbers) {
        if (!PATTERN.matcher(lottoNumbers).matches()) {
            throw new IllegalArgumentException(LOTTO_NUM_INPUT_PATTERN_ERROR_MESSAGE);
        }
    }
}