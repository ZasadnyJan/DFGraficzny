package DF;

import java.util.Objects;

public class FloatValue extends Value
{
    private float body;

    public FloatValue(float x)
    {
        body=x;
    }

    public String toString()
    {
        return String.valueOf(body);
    }

    public Object Get()
    {
        return body;
    }

    public Value add(Value v)
    {
        if(v instanceof IntegerValue)
            return (new FloatValue(body+(float)((int)v.Get())));
        if(v instanceof FloatValue)
            return (new FloatValue(body+(float)v.Get()));
        if(v instanceof DoubleValue)
            return (new FloatValue(body+(float)((double)v.Get())));
        throw (new IllegalArgumentException("Addition unavailable for given type"));
    }

    public Value sub(Value v)
    {
        if(v instanceof IntegerValue)
            return (new FloatValue(body-(float)((int)v.Get())));
        if(v instanceof FloatValue)
            return (new FloatValue(body-(float)v.Get()));
        if(v instanceof DoubleValue)
            return (new FloatValue(body-(float)((double)v.Get())));
        throw (new IllegalArgumentException("Subtraction unavailable for given type"));
    }

    public  Value mul(Value v)
    {
        if(v instanceof IntegerValue)
            return (new FloatValue(body*(float)((int)v.Get())));
        if(v instanceof FloatValue)
            return (new FloatValue(body*(float)v.Get()));
        if(v instanceof DoubleValue)
            return (new FloatValue(body*(float)((double)v.Get())));
        throw (new IllegalArgumentException("Multiplication unavailable for given type"));
    }

    public  Value div(Value v)
    {
        if(v instanceof IntegerValue)
        {
            if(((float)((int)(v.Get())))!=0.0f)
                return(new FloatValue(body/(float)((int)v.Get())));
            else
                throw (new IllegalArgumentException("Division by zero"));
        }
        if(v instanceof FloatValue)
        {
            if(((float)(v.Get()))!=0.0f)
                return(new FloatValue(body/(float)v.Get()));
            else
                throw (new IllegalArgumentException("Division by zero"));
        }
        if(v instanceof DoubleValue)
        {
            if(((float)((double)(v.Get())))!=0.0f)
                return(new FloatValue(body/(float)((double)v.Get())));
            else
                throw (new IllegalArgumentException("Division by zero"));
        }
        else
            throw (new IllegalArgumentException("Division unavailable for given type"));

    }

    public  Value pow(Value v)
    {
        if(v instanceof IntegerValue)
            return(new FloatValue((float) Math.pow(body, (float)((int)v.Get()))));
        if(v instanceof FloatValue)
            return(new FloatValue((float) Math.pow(body, (float)(v.Get()))));
        if(v instanceof DoubleValue)
            return(new FloatValue((float) Math.pow(body, (float)((double)(v.Get())))));
        else
            throw (new IllegalArgumentException("Exponentiation unavailable for given type"));
    }

    public boolean eq(Value v)
    {
        if(v instanceof IntegerValue)
            return (float)((int)v.Get())==body;
        if(v instanceof FloatValue)
            return (float)(v.Get())==body;
        if(v instanceof DoubleValue)
            return (float)((double)(v.Get()))==body;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean lte(Value v)
    {
        if(v instanceof IntegerValue)
            return (float)((int)v.Get())>=body;
        if(v instanceof FloatValue)
            return (float)(v.Get())>=body;
        if(v instanceof DoubleValue)
            return (float)((double)(v.Get()))>=body;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean lt(Value v)
    {
        if(v instanceof IntegerValue)
            return (float)((int)v.Get())>body;
        if(v instanceof FloatValue)
            return (float)(v.Get())>body;
        if(v instanceof DoubleValue)
            return (float)((double)(v.Get()))>body;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean gte(Value v)
    {
        if(v instanceof IntegerValue)
            return (float)((int)v.Get())<=body;
        if(v instanceof FloatValue)
            return (float)(v.Get())<=body;
        if(v instanceof DoubleValue)
            return (float)((double)(v.Get()))<=body;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean gt(Value v)
    {
        if(v instanceof IntegerValue)
            return (float)((int)v.Get())<body;
        if(v instanceof FloatValue)
            return (float)(v.Get())<body;
        if(v instanceof DoubleValue)
            return (float)((double)(v.Get()))<body;
        else
            throw (new IllegalArgumentException("Incomparable types"));
    }

    public boolean neq(Value v)
    {
        return !this.eq(v);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FloatValue that = (FloatValue) o;
        return Float.compare(that.body, body) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(body);
    }

    public Value create(String s)
    {
        float tmp = Float.parseFloat(s);
        return (new FloatValue(tmp));
    }
}
