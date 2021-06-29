package com.lvdreamer.excel;

import java.math.BigInteger;

public class BigRadixIdGen {

    private BigInteger startBigId;
    private BigInteger currBigId;


    public BigRadixIdGen(String startStrId) {
        this.startBigId = BigRadix36.toInt(startStrId);
        this.currBigId = startBigId;
    }

    public BigRadixIdGen(BigInteger startBigId) {
        this.startBigId = startBigId;
        this.currBigId = startBigId;
    }

    public String getNextStrId() {
        currBigId = currBigId.add(BigInteger.ONE);
        return BigRadix36.toRadixStr(currBigId);
    }

    public String getAndIncre() {
        BigInteger last = currBigId;
        currBigId = currBigId.add(BigInteger.ONE);
        return BigRadix36.toRadixStr(last);
    }

}
