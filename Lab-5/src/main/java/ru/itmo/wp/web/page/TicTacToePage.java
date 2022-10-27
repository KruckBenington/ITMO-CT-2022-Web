package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        if (session.getAttribute("state") == null) {
            State state = State.newBaseState();
            view.put("state", state);
            session.setAttribute("state", state);
        }
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        State state = (State) session.getAttribute("state");
        if (state == null) {
            session.setAttribute("state", State.newBaseState());
        }

        String cellStringNumber = Collections.list(request.getParameterNames()).get(1).substring(5);
        int row = Character.getNumericValue(cellStringNumber.charAt(0));
        int cell = Character.getNumericValue(cellStringNumber.charAt(1));

        assert state != null;
        if (state.crossesMove) {
            state.cells[row][cell] = "X";
        } else {
            state.cells[row][cell] = "O";
        }
        state.crossesMove = !state.crossesMove;
        state.countOfNotEmptyCells++;

        if (state.countOfNotEmptyCells == state.size) {
            state.phase = checkPhaseState(state);
        }


        view.put("state", state);
        session.setAttribute("state", state);
    }


    private String checkPhaseState(State state) {
        boolean isWin = false;
        int size = state.size;
        String[][] table = state.cells;

        for (int i = 0; i < size; i++) {
            isWin = true;
            for (int j = 1; j < size; j++) {
                if (!Objects.equals(table[i][j - 1], table[i][j])) {
                    isWin = false;
                    break;
                }
            }

            if (isWin) {
                return "WIN" + table[i][0];
            }
        }

        for (int j = 0; j < size; j++) {
            isWin = true;
            for (int i = 1; i < size; i++) {
                if (!Objects.equals(table[i - 1][j], table[i][j])) {
                    isWin = false;
                    break;
                }
            }
            if (isWin) {
                return "WIN" + table[0][j];
            }
        }

        for (int j = 1; j < size; j++) {
            isWin = true;
            if (!Objects.equals(table[j - 1][j - 1], table[j][j])) {
                isWin = false;
                break;
            }
        }

        if (isWin) {
            return "WIN" + table[0][0];
        }

        for (int j = 1; j < size; j++) {
            isWin = true;
            if (!Objects.equals(table[j - 1][size - (j - 1)], table[j][size - j])) {
                isWin = false;
                break;
            }
        }

        if (isWin) {
            return "WIN" + table[0][size - 1];
        }

        return "DRAW";
    }


    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        session.setAttribute("state", State.newBaseState());
        view.put("state", State.newBaseState());
    }


    public static class State {
        private final int size;
        private final String[][] cells;
        private String phase;
        private boolean crossesMove;
        private int countOfNotEmptyCells;

        private State(int size) {
            this.size = size;
            this.cells = new String[size][size];
            this.phase = "RUNNING";
            this.crossesMove = true;
            this.countOfNotEmptyCells = 0;
        }

        public static State newBaseState() {
            return new State(3);
        }

        public int getSize() {
            return size;
        }

        public String[][] getCells() {
            return cells;
        }

        public String getPhase() {
            return phase;
        }

        public boolean isCrossesMove() {
            return crossesMove;
        }
    }
}
