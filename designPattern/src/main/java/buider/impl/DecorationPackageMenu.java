package buider.impl;

import buider.Imenu;
import buider.Matter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DecorationPackageMenu implements Imenu {
    private BigDecimal area;

    private String grade;

    private BigDecimal price = BigDecimal.ZERO;

    List<Matter> list = new ArrayList<Matter>();

    private DecorationPackageMenu(){

    }

    public DecorationPackageMenu(String grade ,BigDecimal area){
        this.area = area;
        this.grade = grade;
    }

    public Imenu appendCeiling(Matter matter) {
        price.add(matter.price());
        list.add(matter);
        return this;
    }

    public Imenu appendTile(Matter matter) {
        price.add(matter.price().multiply(area).multiply(new BigDecimal(0.2)));
        list.add(matter);
        return this;
    }

    public Imenu appendCoat(Matter matter) {
        price.add(matter.price().multiply(area).multiply(new BigDecimal(1.4)));
        list.add(matter);
        return this;
    }

    public Imenu appendFloor(Matter matter) {
        price.add(matter.price().multiply(area));
        list.add(matter);
        return this;
    }

    public String detail() {
        StringBuilder stringBuilder = new StringBuilder("\r\n-------------------------------------------------------\r\n" +
                "装修清单" + "\r\n" +
                "套餐等级：" + grade + "\r\n" +
                "套餐价格：" + price.setScale(2, BigDecimal.ROUND_HALF_UP) + " 元\r\n" +
                "房屋面积：" + area.doubleValue() + " 平米\r\n" +
                "材料清单：\r\n");

        for(Matter matter :list){
            stringBuilder.append(matter.scene()).append("：").append(matter.brand()).append("、").append(matter.model()).append("、平米价格：").append(matter.price()).append(" 元。\n");        }
        return  stringBuilder.toString();
    }

}
