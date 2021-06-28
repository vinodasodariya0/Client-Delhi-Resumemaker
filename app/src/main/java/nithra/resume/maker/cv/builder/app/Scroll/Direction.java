package nithra.resume.maker.cv.builder.app.Scroll;


public enum Direction {
    START {
        @Override // nithra.resume.maker.cv.builder.app.Scroll.Direction
        public int applyTo(int i) {
            return i * -1;
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.Direction
        public boolean sameAs(int i) {
            return i < 0;
        }
    },
    END {
        @Override // nithra.resume.maker.cv.builder.app.Scroll.Direction
        public int applyTo(int i) {
            return i;
        }

        @Override // nithra.resume.maker.cv.builder.app.Scroll.Direction
        public boolean sameAs(int i) {
            return i > 0;
        }
    };

    public abstract int applyTo(int i);

    public abstract boolean sameAs(int i);

    public static Direction fromDelta(int i) {
        return i > 0 ? END : START;
    }
}
