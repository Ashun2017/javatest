package Java8Test;

import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.Spliterator;

/**
 * Describe：
 * Author：sunqiushun
 * Date：2018-07-30 15:03:36
 */
public class MySpliterator {

    public static void main(String[] args) {
        String arr = "10o%d3 2sdas s3d dfsdz5";
        Stream<Character> stream = IntStream.range(0, arr.length()).mapToObj(arr::charAt);
        System.out.println("ordered total: " + countNum(stream));

        Spliterator<Character> spliterator = new NumCounterSpliterator(arr);
        // 传入true表示是并行流
        Stream<Character> parallelStream = StreamSupport.stream(spliterator, true);
        System.out.println("parallel total: " + countNum(parallelStream));
    }

    private static int countNum(Stream<Character> stream) {
        NumCounter numCounter = stream.reduce(new NumCounter(0, 0, false), NumCounter::accumulate, NumCounter::combine);
        return numCounter.getSum();
    }

    /**
     * 字符串中的数字计算器实现
     */
    public static class NumCounter {

        private int num;
        private int sum;
        private boolean isWholeNum;   // 是否当前是个完整的数字

        public NumCounter(int num, int sum, boolean isWholeNum) {
            this.num = num;
            this.sum = sum;
            this.isWholeNum = isWholeNum;
        }

        public NumCounter accumulate(Character c) {
            if (Character.isDigit(c)) {
                return isWholeNum ? new NumCounter(Integer.parseInt("" + c), sum + num, false) : new NumCounter(Integer.parseInt("" + num + c), sum, false);
            } else {
                return new NumCounter(0, sum + num, true);
            }
        }

        public NumCounter combine(NumCounter numCounter) {
            return new NumCounter(numCounter.num, this.getSum() + numCounter.getSum(), numCounter.isWholeNum);
        }

        public int getSum() {
            return sum + num;
        }
    }

    public static class NumCounterSpliterator implements Spliterator<Character> {

        private String str;
        private int currentChar = 0;

        public NumCounterSpliterator(String str) {
            this.str = str;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Character> action) {
            action.accept(str.charAt(currentChar++));
            return currentChar < str.length();
        }

        @Override
        public Spliterator<Character> trySplit() {

            int currentSize = str.length() - currentChar;
            if (currentSize < 10) return null;

            for (int pos = currentSize / 2 + currentSize; pos < str.length(); pos++) {
                if (pos + 1 < str.length()) {
                    // 当前Character是数字，且下一个Character不是数字，才需要划分一个新的Spliterator
                    if (Character.isDigit(str.charAt(pos)) && !Character.isDigit(str.charAt(pos + 1))) {
                        Spliterator<Character> spliterator = new NumCounterSpliterator(str.substring(currentChar, pos));
                        currentChar = pos;
                        return spliterator;
                    }
                } else {
                    if (Character.isDigit(str.charAt(pos))) {
                        Spliterator<Character> spliterator = new NumCounterSpliterator(str.substring(currentChar, pos));
                        currentChar = pos;
                        return spliterator;
                    }
                }
            }
            return null;
        }

        @Override
        public long estimateSize() {
            return str.length() - currentChar;
        }

        @Override
        public int characteristics() {
            return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
        }
    }

}