package controller;

import domain.LottoNumbers;
import domain.Money;
import java.util.ArrayList;
import java.util.List;
import utils.LottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class MainController {
    public void run() {
        // money
        Money money = new Money(InputView.inputMoney());

        // create lotto
        List<LottoNumbers> lottoTickets = createLottoTickets(money.toLottoCount());

        // print lotto
        OutputView.printLottoTickets(lottoTickets);

        // lotto numbers
        // bonus number
        // result
    }

    private List<LottoNumbers> createLottoTickets(int count) {
        List<LottoNumbers> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(new LottoNumbers(LottoNumberGenerator.generate()));
        }
        return lottoTickets;
    }
}