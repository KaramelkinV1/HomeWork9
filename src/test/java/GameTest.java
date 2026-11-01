
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.javaqa.Game;
import ru.netology.javaqa.NotRegisteredException;
import ru.netology.javaqa.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {
    private final Player petya = new Player(1, "Petya", 100);
    private final Player vasya = new Player(2, "Vasya", 50);
    private final Player masha = new Player(3, "Masha", 100);
    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.register(petya);
        game.register(vasya);
        game.register(masha);
    }

    @Test
    void shouldFindRegisteredPlayerByName() {
        assertSame(vasya, game.findByName("Vasya"));
    }

    @Test
    void shouldReturnNullIfPlayerNotFound() {
        assertNull(game.findByName("Unknown"));
    }

    @Test
    void shouldReturnOneIfFirstPlayerIsStronger() {
        assertEquals(1, game.round("Petya", "Vasya"));
    }

    @Test
    void shouldReturnTwoIfSecondPlayerIsStronger() {
        assertEquals(2, game.round("Vasya", "Petya"));
    }

    @Test
    void shouldReturnZeroIfStrengthIsEqual() {
        assertEquals(0, game.round("Petya", "Masha"));
    }

    @Test
    void shouldThrowIfFirstPlayerNotRegistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("Unknown", "Petya"));
    }

    @Test
    void shouldThrowIfSecondPlayerNotRegistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("Petya", "Unknown"));
    }

    @Test
    void shouldThrowIfBothPlayersNotRegistered() {
        assertThrows(NotRegisteredException.class, () -> game.round("A", "B"));
    }
}