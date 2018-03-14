package domain;

import exception.IncorrectNumberOfRoundsException;
import mockito.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class GameMasterTest {

    @Test
    void given_an_incorrect_number_of_rounds_to_play_throws_an_IncorrectNumberOfRoundsException(
            @Mock Game gameMock) {

        Integer incorrectNumberOfRoundsToPlay = 0;
        GameMaster gameMaster = new GameMaster(gameMock);

        assertThrows(IncorrectNumberOfRoundsException.class, () -> gameMaster.organize(incorrectNumberOfRoundsToPlay));

    }

}