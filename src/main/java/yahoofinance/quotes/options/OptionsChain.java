package yahoofinance.quotes.options;

import java.util.List;

public class OptionsChain {

    private final List<Option> calls;
    private final List<Option> puts;

    public OptionsChain(List<Option> calls, List<Option> puts) {
        this.calls = calls;
        this.puts = puts;
    }

    public List<Option> getCalls() {
        return calls;
    }

    public List<Option> getPuts() {
        return puts;
    }
}
