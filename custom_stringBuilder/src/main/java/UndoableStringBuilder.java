import java.util.Arrays;
import java.util.Stack;

public class UndoableStringBuilder {

    private CustomStringBuilder builder;
    private final Stack<Snapshot> history = new Stack<>();

    public UndoableStringBuilder() {
        this.builder = new CustomStringBuilder();
    }

    public UndoableStringBuilder(String str) {
        this.builder = new CustomStringBuilder(str);
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
        private int count;
        private static final int DEFAULT_CAPACITY = 16;

        public CustomStringBuilder() {
            this.value = new char[DEFAULT_CAPACITY];
            this.count = 0;
        }

        public CustomStringBuilder(String str) {
            this();
            append(str);
        }

        private CustomStringBuilder(StateData state) {
            this.value = Arrays.copyOf(state.value, state.value.length);
            this.count = state.count;
        }

        public static class StateData {
            final char[] value;
            final int count;

            StateData(char[] value, int count) {
                this.value = Arrays.copyOf(value, count);
                this.count = count;
            }

        }

        public StateData getState() {
            return new StateData(this.value, this.count);
        }

        public CustomStringBuilder restoreState(StateData state) {
            return new CustomStringBuilder(state);
        }

        public CustomStringBuilder append(String str) {
            if (str == null) {
                str = "null";
            }
            int len = str.length();
            ensureCapacity(count + len);
            str.getChars(0, len, value, count);
            count += len;
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
            return new String(value, 0, count);
        }
    }

    public UndoableStringBuilder append(String str) {
        history.push(new Snapshot(builder.getState()));
        builder.append(str);
        return this;
    }

    public void undo() {
        if (!history.isEmpty()) {
            Snapshot lastState = history.pop();
            this.builder = builder.restoreState(lastState.getSavedState());
        }
    }

    @Override
    public String toString() {
        return builder.toString();
    }

    public static void main(String[] args) {
        UndoableStringBuilder uBuilder = new UndoableStringBuilder("Hello, ");

        uBuilder.append("World!");
        System.out.println(uBuilder); //Hello, World!
        uBuilder.undo();
        System.out.println(uBuilder); //Hello,
    }
}
