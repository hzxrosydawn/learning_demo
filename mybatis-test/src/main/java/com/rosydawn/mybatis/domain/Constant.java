package com.rosydawn.mybatis.domain;

/**
 * 各种常量类
 *
 * @author Vincent
 **/
public class Constant {
    private long constantId;
    /**
     * 常量所属系统。目前为“education”。
     */
    private String system;
    /**
     * 常量分组。可以为“grade”（即年级组），“course”（即课程组），“class”（即班级组）。
     */
    private String itemGroup;
    /**
     * 具体的常量值。“grade”（即年级组）可以为“一年级”~“九年级”，“course”（即课程组）可以为“语文”、“数学”、“英语”、“物理”、“化学”、
     * “生物”、“政治”、“地理”、“”
     */
    private int itemName;

    String CHINESE = "语文";
    String MATH = "数学";
    String ENGLISH = "英语";
    String PHYSICS = "物理";
    String CHEMISTRY = "化学";
    String BIOLOGY = "生物";


    String GRADE_SEVEN = "七年级";
    String GRADE_EIGHT = "八年级";
    String GRADE_NINE = "九年级";

    String CLASS_ONE = "一班";
    String CLASS_TWO = "二班";
    String CLASS_THREE = "三班";
    String CLASS_FOUR = "四班";
    String CLASS_FIVE = "五班";
    String CLASS_SIX = "六班";
    String CLASS_SEVEN = "七班";
    String CLASS_EIGHT = "八班";
    String CLASS_NINE = "九班";
    String CLASS_TEN = "十班";
}
