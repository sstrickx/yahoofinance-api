package yahoofinance.quotes;

/**
 *
 * @author Stijn Strickx
 */
public enum QuotesProperty {
    
    

    AfterHoursChangeRealtime("c8"), // After Hours Change (Realtime)
    AnnualizedGain("g3"), // Annualized Gain
    Ask("a0"), // Ask
    AskRealtime("b2"), // Ask (Realtime)
    AskSize("a5"), // Ask Size
    AverageDailyVolume("a2"), // Average Daily Volume
    Bid("b0"), // Bid
    BidRealtime("b3"), // Bid (Realtime)
    BidSize("b6"), // Bid Size
    BookValuePerShare("b4"), // Book Value Per Share
    Change("c1"), // Change
    Change_ChangeInPercent("c0"), // Change Change In Percent
    ChangeFromFiftydayMovingAverage("m7"), // Change From Fiftyday Moving Average
    ChangeFromTwoHundreddayMovingAverage("m5"), // Change From Two Hundredday Moving Average
    ChangeFromYearHigh("k4"), // Change From Year High
    ChangeFromYearLow("j5"), // Change From Year Low
    ChangeInPercent("p2"), // Change In Percent
    ChangeInPercentRealtime("k2"), // Change In Percent (Realtime)
    ChangeRealtime("c6"), // Change (Realtime)
    Commission("c3"), // Commission
    Currency("c4"), // Currency
    DaysHigh("h0"), // Days High
    DaysLow("g0"), // Days Low
    DaysRange("m0"), // Days Range
    DaysRangeRealtime("m2"), // Days Range (Realtime)
    DaysValueChange("w1"), // Days Value Change
    DaysValueChangeRealtime("w4"), // Days Value Change (Realtime)
    DividendPayDate("r1"), // Dividend Pay Date
    TrailingAnnualDividendYield("d0"), // Trailing Annual Dividend Yield
    TrailingAnnualDividendYieldInPercent("y0"), // Trailing Annual Dividend Yield In Percent
    DilutedEPS("e0"), // Diluted E P S
    EBITDA("j4"), // E B I T D A
    EPSEstimateCurrentYear("e7"), // E P S Estimate Current Year
    EPSEstimateNextQuarter("e9"), // E P S Estimate Next Quarter
    EPSEstimateNextYear("e8"), // E P S Estimate Next Year
    ExDividendDate("q0"), // Ex Dividend Date
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
    LastTradeWithTime("l0"), // Last Trade With Time
    LowLimit("l3"), // Low Limit
    MarketCapitalization("j1"), // Market Capitalization
    MarketCapRealtime("j3"), // Market Cap (Realtime)
    MoreInfo("i0"), // More Info
    Name("n0"), // Name
    Notes("n4"), // Notes
    OneyrTargetPrice("t8"), // Oneyr Target Price
    Open("o0"), // Open
    OrderBookRealtime("i5"), // Order Book (Realtime)
    PEGRatio("r5"), // P E G Ratio
    PERatio("r0"), // P E Ratio
    PERatioRealtime("r2"), // P E Ratio (Realtime)
    PercentChangeFromFiftydayMovingAverage("m8"), // Percent Change From Fiftyday Moving Average
    PercentChangeFromTwoHundreddayMovingAverage("m6"), // Percent Change From Two Hundredday Moving Average
    ChangeInPercentFromYearHigh("k5"), // Change In Percent From Year High
    PercentChangeFromYearLow("j6"), // Percent Change From Year Low
    PreviousClose("p0"), // Previous Close
    PriceBook("p6"), // Price Book
    PriceEPSEstimateCurrentYear("r6"), // Price E P S Estimate Current Year
    PriceEPSEstimateNextYear("r7"), // Price E P S Estimate Next Year
    PricePaid("p1"), // Price Paid
    PriceSales("p5"), // Price Sales
    Revenue("s6"), // Revenue
    SharesOwned("s1"), // Shares Owned
    SharesOutstanding("j2"), // Shares Outstanding
    ShortRatio("s7"), // Short Ratio
    StockExchange("x0"), // Stock Exchange
    Symbol("s"), // Symbol
    TickerTrend("t7"), // Ticker Trend
    TradeDate("d2"), // Trade Date
    TradeLinks("t6"), // Trade Links
    TradeLinksAdditional("f0"), // Trade Links Additional
    TwoHundreddayMovingAverage("m4"), // Two Hundredday Moving Average
    Volume("v0"), // Volume
    YearHigh("k0"), // Year High
    YearLow("j0"), // Year Low
    YearRange("w0"); // Year Range

    private final String tag;
    

    QuotesProperty(String tag) {
        this.tag = tag;
    }
    
    public String getTag() {
        return this.tag;
    }
}

