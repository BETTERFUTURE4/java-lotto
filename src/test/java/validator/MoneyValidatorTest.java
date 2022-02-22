package validator;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyValidatorTest {

	@ParameterizedTest
	@ValueSource(ints = {-1, 200, 999})
	void 천_미만_값일_경우_예외처리(int input) {
		assertThatThrownBy(() -> MoneyValidator.isOverThousand(input)).isInstanceOf(Exception.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {1500, 2060, 9999})
	void 천으로_나누어_떨어지지_않는_경우_예외처리(int input) {
		assertThatThrownBy(() -> MoneyValidator.isDivideByThousand(input)).isInstanceOf(Exception.class);
	}
}