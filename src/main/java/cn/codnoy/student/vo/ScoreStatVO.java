package cn.codnoy.student.vo;

import lombok.Data;

/**
 * 成绩统计视图对象
 * 用于展示某门课程的整体成绩统计信息
 */
@Data
public class ScoreStatVO {
    /**
     * 平均分
     */
    private Double avgScore;

    /**
     * 最高分
     */
    private Double maxScore;

    /**
     * 最低分
     */
    private Double minScore;

    /**
     * 及格人数(通常为60分及以上)
     */
    private Integer passCount;

    /**
     * 不及格人数(通常为60分以下)
     */
    private Integer failCount;

    /**
     * A等级人数(通常为90-100分)
     */
    private Integer aCount;

    /**
     * B等级人数(通常为80-89分)
     */
    private Integer bCount;

    /**
     * C等级人数(通常为70-79分)
     */
    private Integer cCount;

    /**
     * D等级人数(通常为60-69分)
     */
    private Integer dCount;

    /**
     * F等级人数(通常为60分以下)
     */
    private Integer fCount;
}
