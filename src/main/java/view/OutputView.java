package view;

import domain.LottoNumber;
import domain.LottoNumbers;
import domain.Rank;
import domain.Result;
import java.util.List;

public class OutputView {
    public static void printLottoTickets(List<LottoNumbers> lottoTickets) {
        System.out.println(lottoTickets.size()+"개를 구매했습니다.");
        for (LottoNumbers lottoNumbers : lottoTickets) {
            printLottoNumbers(lottoNumbers);
        }
    }

    private static void printLottoNumbers(LottoNumbers lottoNumbers) {
        StringBuilder result = new StringBuilder("[");
        for (LottoNumber lottoNumber : lottoNumbers.get()) {
            result.append(lottoNumber.get()).append(", ");
        }
        result.delete(result.length() - 2, result.length()).append("]");
        System.out.println(result);
    }

    public static void printResult(Result result) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Rank rank : Rank.getWithoutDefault()) {
            System.out.printf("%d개 일치%s(%d원)- %d개" + System.lineSeparator(),
                    rank.getMatchCount(), printIfSecond(rank),
                    rank.getPrizeMoney(), result.getRankCount(rank));
        }
    }

    private static String printIfSecond(Rank rank) {
        if (rank.equals(Rank.SECOND)) {
            return ", 보너스 볼 일치";
        }
        return " ";
    }

    public static void printProfit(float profit) {
        System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해%s라는 의미임)" + System.lineSeparator(),
                profit, printIfLoss(profit));
    }

    private static Object printIfLoss(float profit) {
        if (profit >= 1) {
            return " 아니";
        }
        return "";
    }
}