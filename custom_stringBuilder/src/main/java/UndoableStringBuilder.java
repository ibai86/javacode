import java.util.Arrays;
import java.util.Stack;

public class UndoableStringBuilder {

    private CustomStringBuilder builder;
    private final Stack<Snapshot> history = new Stack<>();

    public UndoableStringBuilder() {
        this.builder = new CustomStringBuilder();
    }

    private static class Snapshot {
        private final CustomStringBuilder.StateData stateData;

        private Snapshot(CustomStringBuilder.StateData stateData) {
            this.stateData = stateData;
        }

        private CustomStringBuilder.StateData getSavedState() {
            return stateData;
        }
    }

    private static final class CustomStringBuilder {
        private char[] value;
        private int length;
        private static final int DEFAULT_LENGTH = 16;

        public static class StateData {
            final char[] value;
            final int length;

            StateData(char[] value, int length) {
                this.value = Arrays.copyOf(value, length);
                this.length = length;
            }
        }

        public CustomStringBuilder() {
            this.value = new char[DEFAULT_LENGTH];
            this.length = 0;
        }

        private CustomStringBuilder(StateData state) {
            this.value = Arrays.copyOf(state.value, state.value.length);
            this.length = state.length;
        }

        public StateData getState() {
            return new StateData(this.value, this.length);
        }

        public CustomStringBuilder restoreState(StateData state) {
            return new CustomStringBuilder(state);
        }

        public CustomStringBuilder append(String str) {
            if (str == null) {
                str = "null";
            }
            int len = str.length();
            ensureCapacity(length + len);
            str.getChars(0, len, value, length);
            length += len;
            return this;
        }

        private void ensureCapacity(int minimumCapacity) {
            if (minimumCapacity > value.length) {
                int newCapacity = value.length * 2 + 2;
                value = Arrays.copyOf(value, newCapacity);
            }
        }

        @Override
        public String toString() {
            return new String(value, 0, length);
        }
    }
}
