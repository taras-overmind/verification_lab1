import org.example.DFA;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class TestDFA {
    @BeforeAll
    static void setUp() {
        System.out.println("Початок тестів");
    }

    @BeforeEach
    void setTest() {
        System.out.println("Початок тесту");
    }

    @Test
    void testConstructor() {

        DFA dfa = new DFA();
        assertNotNull(dfa.alphabet);
        assertNotNull(dfa.states);
        assertNotNull(dfa.finalStates);
        assertTrue(dfa.alphabet.isEmpty());
        assertTrue(dfa.states.isEmpty());
        assertTrue(dfa.finalStates.isEmpty());
    }

    @Test
    void AutomatonTest1() throws Exception {
        DFA dfa = new DFA();
        dfa.setnAlphabet(2);
        dfa.setnStates(3);
        dfa.setInitState(0);

        dfa.setFinalStates(Set.of(2));
        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(0, 'b', 2);
        dfa.addTransition(1, 'b', 2);
        dfa.addTransition(2, 'b', 1);

        assertThat(dfa.alphabet, containsInAnyOrder('a', 'b'));
        assertThat(dfa.finalStates, containsInAnyOrder(2));
        assertEquals(dfa.finalStates.size(), 1);


    }

    @Test
    void AutomatonTest2() throws Exception {
        DFA dfa = new DFA();
        dfa.setnAlphabet(2);
        dfa.setnStates(3);
        dfa.setInitState(0);
        dfa.setFinalStates(Set.of(2));
        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(0, 'b', 0);
        dfa.addTransition(1, 'a', 1);
        dfa.addTransition(1, 'b', 0);
        dfa.addTransition(2, 'a', 2);
        dfa.addTransition(2, 'b', 1);

        var output = dfa.findAllAcceptedWords();

        assertThat(output.toString(), startsWith("[]"));
    }

    @Test
    void AutomatonTest3() throws Exception {
        DFA dfa = new DFA();
        dfa.setnAlphabet(2);
        dfa.setnStates(5);
        dfa.setInitState(0);
        dfa.setFinalStates(Set.of(1, 4));
        dfa.addTransition(0, 'a', 1);
        dfa.addTransition(0, 'b', 1);
        dfa.addTransition(1, 'a', 3);
        dfa.addTransition(1, 'b', 2);
        dfa.addTransition(2, 'a', 4);
        dfa.addTransition(2, 'b', 3);
        dfa.addTransition(3, 'a', 4);
        dfa.addTransition(3, 'b', 4);

        var output = dfa.findAllAcceptedWords();

        assert(output.toString().contains("aaa"));
        assert(output.toString().contains("aba"));
        assert(output.toString().contains("bbba"));
        assert(output.toString().contains("abba"));
        assert(output.toString().contains("bbbb"));
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 5, -1})
    void ExceptionTest(int state) {
        DFA dfa = new DFA();
        dfa.setnStates(3);
        assertThrows(Exception.class, () -> dfa.setInitState(state));
    }

}
