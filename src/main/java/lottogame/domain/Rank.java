package lottogame.domain;

import lottogame.domain.ticket.LottoTicketResult;

import java.util.Arrays;

public enum Rank {
    FAIL(0, false, 0, "0개 일치 (0원)- %d개"),
    RANK5(3, false, 5_000, "3개 일치 (5000원)- %d개"),
    RANK4(4, false, 50_000, "4개 일치 (50000원)- %d개"),
    RANK3(5, false, 1_500_000, "5개 일치 (1500000원)- %d개"),
    RANK2(5, true, 30_000_000, "5개 일치 보너스 볼 일치(30000000원)- %d개"),
    RANK1(6, false, 2_000_000_000, "6개 일치 (2000000000원)- %d개");

    private final int matchCount;
    private final boolean isBonusMatch;
    private final int prizeMoney;
    private final String messageFormat;

    Rank(final int matchCount, final boolean isBonusMatch, final int prizeMoney, final String messageFormat) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.prizeMoney = prizeMoney;
        this.messageFormat = messageFormat;
    }

    public static Rank getRank(LottoTicketResult lottoTicketResult) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == lottoTicketResult.getMatchCount())
                .filter(rank -> rank.isBonusMatch == lottoTicketResult.isBonusMatched())
                .findAny()
                .orElse(FAIL);
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public String getMessageFormat() {
        return messageFormat;
    }
}