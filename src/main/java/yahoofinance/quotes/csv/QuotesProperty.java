package yahoofinance.quotes.csv;

/**
 *
 * @author Stijn Strickx
 */
public enum QuotesProperty {
    
    

    AfterHoursChangeRealtime("c8"), // After Hours Change (Realtime)
    AnnualizedGain("g3"), // Annualized Gain
    Ask("a"), // Ask
    AskRealtime("b2"), // Ask (Realtime)
    AskSize("a5"), // Ask Size
    AverageDailyVolume("a2"), // Average Daily Volume
    Bid("b"), // Bid
    BidRealtime("b3"), // Bid (Realtime)
    BidSize("b6"), // Bid Size
    BookValuePerShare("b4"), // Book Value Per Share
    Change("c1"), // Change
    Change_ChangeInPercent("c"), // Change Change In Percent
    ChangeFromFiftydayMovingAverage("m7"), // Change From Fiftyday Moving Average
    ChangeFromTwoHundreddayMovingAverage("m5"), // Change From Two Hundredday Moving Average
    ChangeFromYearHigh("k4"), // Change From Year High
    ChangeFromYearLow("j5"), // Change From Year Low
    ChangeInPercent("p2"), // Change In Percent
    ChangeInPercentRealtime("k2"), // Change In Percent (Realtime)
    ChangeRealtime("c6"), // Change (Realtime)
    Commission("c3"), // Commission
    Currency("c4"), // Currency
    DaysHigh("h"), // Days High
    DaysLow("g"), // Days Low
    DaysRange("m"), // Days Range
    DaysRangeRealtime("m2"), // Days Range (Realtime)
    DaysValueChange("w1"), // Days Value Change
    DaysValueChangeRealtime("w4"), // Days Value Change (Realtime)
    DividendPayDate("r1"), // Dividend Pay Date
    TrailingAnnualDividendYield("d"), // Trailing Annual Dividend Yield
    TrailingAnnualDividendYieldInPercent("y"), // Trailing Annual Dividend Yield In Percent
    DilutedEPS("e"), // Diluted E P S
    EBITDA("j4"), // E B I T D A
    EPSEstimateCurrentYear("e7"), // E P S Estimate Current Year
    EPSEstimateNextQuarter("e9"), // E P S Estimate Next Quarter
    EPSEstimateNextYear("e8"), // E P S Estimate Next Year
    ExDividendDate("q"), // Ex Dividend Date
    FiftydayMovingAverage("m3"), // Fiftyday Moving Average
    SharesFloat("f6"), // Shares Float
    HighLimit("l2"), // High Limit
    HoldingsGain("g4"), // Holdings Gain
    HoldingsGainPercent("g1"), // Holdings Gain Percent
    HoldingsGainPercentRealtime("g5"), // Holdings Gain Percent (Realtime)
    HoldingsGainRealtime("g6"), // Holdings Gain (Realtime)
    HoldingsValue("v1"), // Holdings Value
    HoldingsValueRealtime("v7"), // Holdings Value (Realtime)
    LastTradeDate("d1"), // Last Trade Date
    LastTradePriceOnly("l1"), // Last Trade Price Only
    LastTradeRealtimeWithTime("k1"), // Last Trade (Realtime) With Time
    LastTradeSize("k3"), // Last Trade Size
    LastTradeTime("t1"), // Last Trade Time
    LastTradeWithTime("l"), // Last Trade With Time
    LowLimit("l3"), // Low Limit
    MarketCapitalization("j1"), // Market Capitalization
    MarketCapRealtime("j3"), // Market Cap (Realtime)
    MoreInfo("i"), // More Info
    Name("n"), // Name
    Notes("n4"), // Notes
    OneyrTargetPrice("t8"), // Oneyr Target Price
    Open("o"), // Open
    OrderBookRealtime("i5"), // Order Book (Realtime)
    PEGRatio("r5"), // P E G Ratio
    PERatio("r"), // P E Ratio
    PERatioRealtime("r2"), // P E Ratio (Realtime)
    PercentChangeFromFiftydayMovingAverage("m8"), // Percent Change From Fiftyday Moving Average
    PercentChangeFromTwoHundreddayMovingAverage("m6"), // Percent Change From Two Hundredday Moving Average
    ChangeInPercentFromYearHigh("k5"), // Change In Percent From Year High
    PercentChangeFromYearLow("j6"), // Percent Change From Year Low
    PreviousClose("p"), // Previous Close
    PriceBook("p6"), // Price Book
    PriceEPSEstimateCurrentYear("r6"), // Price E P S Estimate Current Year
    PriceEPSEstimateNextYear("r7"), // Price E P S Estimate Next Year
    PricePaid("p1"), // Price Paid
    PriceSales("p5"), // Price Sales
    Revenue("s6"), // Revenue
    SharesOwned("s1"), // Shares Owned
    SharesOutstanding("j2"), // Shares Outstanding
    ShortRatio("s7"), // Short Ratio
    StockExchange("x"), // Stock Exchange
    Symbol("s"), // Symbol
    TickerTrend("t7"), // Ticker Trend
    TradeDate("d2"), // Trade Date
    TradeLinks("t6"), // Trade Links
    TradeLinksAdditional("f"), // Trade Links Additional
    TwoHundreddayMovingAverage("m4"), // Two Hundredday Moving Average
    Volume("v"), // Volume
    YearHigh("k"), // Year High
    YearLow("j"), // Year Low
    YearRange("w"); // Year Range

    private final String tag;
    

    QuotesProperty(String tag) {
        this.tag = tag;
    }
    
    public String getTag() {
        return this.tag;
    }
}

