package cc.IOI;

/**
 * Created by nobody on 2016/8/10.
 */
public class Assert {

    public void first(){
        int x = -1;
        assert x >= 0;
        assert x < 0;
    }

    public static void main(String[] args){
        Assert a = new Assert();
        a.first();
    }
}
