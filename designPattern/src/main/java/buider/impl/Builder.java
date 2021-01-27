package buider.impl;

import buider.Imenu;
import buider.vo.*;

import java.math.BigDecimal;

public class Builder {
    public  Imenu leverOne(){
        return  new DecorationPackageMenu("一级装修",new BigDecimal(123.4)).appendCeiling(new LevelOneCeiling())
                .appendCoat(new DuluxCoat()).appendFloor(new ShengXiangFloor()).appendTile(new MarcoPoloTile());
    }

    public  Imenu leverTwo(){
        return  new DecorationPackageMenu("二级装修",new BigDecimal(89.7)).appendCeiling(new LevelTwoCeiling())
                .appendCoat(new DuluxCoat()).appendFloor(new ShengXiangFloor()).appendTile(new DongPengTile());
    }

    public  Imenu leverThree(){
        return  new DecorationPackageMenu("三级装修",new BigDecimal(139.7)).appendCeiling(new LevelTwoCeiling())
                .appendCoat(new LiBangCoat()).appendFloor(new DerFloor()).appendTile(new DongPengTile());
    }
}
