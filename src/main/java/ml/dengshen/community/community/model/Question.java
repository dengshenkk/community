package ml.dengshen.community.community.model;

public class Question {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.id
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.title
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.gmt_create
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.gmt_modify
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private Long gmtModify;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.creator
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private Long creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.comment_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private Integer commentCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.view_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private Integer viewCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.like_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private Integer likeCount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.tag
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private String tag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_question.description
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.id
     *
     * @return the value of t_question.id
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.id
     *
     * @param id the value for t_question.id
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.title
     *
     * @return the value of t_question.title
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.title
     *
     * @param title the value for t_question.title
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.gmt_create
     *
     * @return the value of t_question.gmt_create
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.gmt_create
     *
     * @param gmtCreate the value for t_question.gmt_create
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.gmt_modify
     *
     * @return the value of t_question.gmt_modify
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public Long getGmtModify() {
        return gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.gmt_modify
     *
     * @param gmtModify the value for t_question.gmt_modify
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setGmtModify(Long gmtModify) {
        this.gmtModify = gmtModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.creator
     *
     * @return the value of t_question.creator
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.creator
     *
     * @param creator the value for t_question.creator
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.comment_count
     *
     * @return the value of t_question.comment_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.comment_count
     *
     * @param commentCount the value for t_question.comment_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.view_count
     *
     * @return the value of t_question.view_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public Integer getViewCount() {
        return viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.view_count
     *
     * @param viewCount the value for t_question.view_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.like_count
     *
     * @return the value of t_question.like_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.like_count
     *
     * @param likeCount the value for t_question.like_count
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.tag
     *
     * @return the value of t_question.tag
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public String getTag() {
        return tag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.tag
     *
     * @param tag the value for t_question.tag
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_question.description
     *
     * @return the value of t_question.description
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_question.description
     *
     * @param description the value for t_question.description
     *
     * @mbg.generated Sat Feb 08 18:03:52 CST 2020
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}