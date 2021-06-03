package com.liwinli.app.xiaolingent.customer.analysis.enums;

public class EnumCity {
    public enum Level {
        ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

        private int mLevel = 1;

        private Level(int value) {
            mLevel = value;
        }

        public int getmLevel() {
            return mLevel;
        }
    }
}
