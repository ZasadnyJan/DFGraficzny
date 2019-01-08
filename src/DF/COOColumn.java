package DF;

import java.util.ArrayList;

public class COOColumn extends Column {

    ArrayList<COOValue> col;

    public COOColumn(String name, Class<? extends Value> type)
    {
        super(name, type);
        col = new ArrayList<COOValue>();
    }

}