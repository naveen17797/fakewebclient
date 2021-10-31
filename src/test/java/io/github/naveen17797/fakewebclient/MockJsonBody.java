package io.github.naveen17797.fakewebclient;

public class MockJsonBody {

    public String getF1() {
        return f1;
    }

    public String getF2() {
        return f2;
    }

    private final String f1;
    private final String f2;

    MockJsonBody(String f1, String f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

}
