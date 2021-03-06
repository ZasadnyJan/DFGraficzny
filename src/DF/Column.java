package DF;

import java.util.ArrayList;

public class Column implements Cloneable
{
    public String columnName;
    Class<? extends Value> columnType;
    public ArrayList<Value> col;
    public Column(String name, Class<? extends Value> type)
    {

        columnName = name;
        columnType = type;


        col = new ArrayList<>();
    }

    public COOColumn ColumnToCOOLColumn(Object hide)
    {
        ArrayList COOLcol = new ArrayList<COOValue>();
        int j=0, originalSize = col.size(), current;
        while(j<originalSize)
        {
            current = col.indexOf(hide);
            if(current != 0)
                COOLcol.add(new COOValue(j,col.get(0)));
            col.remove(0);
            j++;
        }
        COOColumn result = new COOColumn(columnName,columnType);
        result.col = COOLcol;
        return result;
    }

    public void Add(Value v)
    {
        for(Value tmp:col)
            tmp.add(v);
    }

    public void Sub(Value v)
    {
        for(Value tmp:col)
            tmp.sub(v);
    }

    public void Mul(Value v)
    {
        for(Value tmp:col)
            tmp.mul(v);
    }

    public void Div(Value v)
    {
        for(Value tmp:col)
            tmp.div(v);
    }

    public void Pow(Value v)
    {
        for(Value tmp:col)
            tmp.pow(v);
    }

    public void Add(Column c)
    {
        if(col.size()!=c.col.size())
            throw new IllegalArgumentException("Columns: "+columnName+" and "+c.columnName+" have different length.");
        for(int i=0;i<col.size();i++)
            col.get(i).add(c.col.get(i));
    }

    public void Sub(Column c)
    {
        if(col.size()!=c.col.size())
            throw new IllegalArgumentException("Columns: "+columnName+" and "+c.columnName+" have different length.");
        for(int i=0;i<col.size();i++)
            col.get(i).sub(c.col.get(i));
    }

    public void Mul(Column c)
    {
        if(col.size()!=c.col.size())
            throw new IllegalArgumentException("Columns: "+columnName+" and "+c.columnName+" have different length.");
        for(int i=0;i<col.size();i++)
            col.get(i).mul(c.col.get(i));
    }

    public void Div(Column c)
    {
        if(col.size()!=c.col.size())
            throw new IllegalArgumentException("Columns: "+columnName+" and "+c.columnName+" have different length.");
        for(int i=0;i<col.size();i++)
            col.get(i).div(c.col.get(i));
    }

    public void Pow(Column c)
    {
        if(col.size()!=c.col.size())
            throw new IllegalArgumentException("Columns: "+columnName+" and "+c.columnName+" have different length.");
        for(int i=0;i<col.size();i++)
            col.get(i).pow(c.col.get(i));
    }

    @Override
    protected Column clone() {
        try {
            return (Column) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Cloneable nie jest zaimplementowane dla tego typu");
            return null;
        }
    }
}